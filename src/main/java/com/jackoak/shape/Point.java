package com.jackoak.shape;

import com.jackoak.Device.Camera;
import com.jackoak.base.Context;
import com.jackoak.math.ArithUtil;
import com.jackoak.math.LineFormula;
import com.jackoak.math.LineUtil;
import com.jackoak.math.NumberUtil;

import java.math.BigDecimal;
import java.util.*;


public class Point {
    private double x;
    private double y;
    private double z;

    private Point2 point2 = new Point2();

    public Point(double x,double y,double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }


    public Point2 getPoint2() throws Exception {
        Camera camera = Context.getCamera();
        if( camera == null ){
           throw new NullPointerException("Context.getCamera() is null");
        }

        PointTemp xyScreen = xyCalculate(new Point2(x,y),camera);
        PointTemp xzScreen = yzCalculate(new Point2(x,z),camera);

        if(!xyScreen.isInView() || !xzScreen.isInView() ){
            //视野外
            point2.setInView(false);
        }else{
            //视野内
            point2.setInView(true);
        }

        point2.setX(BigDecimal.valueOf(xyScreen.getValue()));
        point2.setY(BigDecimal.valueOf(xzScreen.getValue()));


         return point2;

    }

    /**
     * 一个平面中 物体到 平面的投影坐标
     * x  y 的 值 都是在一个平面的投影
     *
     * @param pointObj 物体在一个平面的 坐标
     * @param d    视点到屏幕距离
     * @param h    屏幕高度 或 宽度
     * @param pointCamera   摄像头坐标点
     * @param citaChange 摄像头绕 某 轴旋转角度
     * @return
     */
    private PointTemp treeToTwoDimensional(Point2 pointObj,double d,double h,Point2 pointCamera,double citaChange){
        PointTemp tempPoint = new PointTemp();

        double distanceH = 0;
        double r = Math.sqrt(d*d + (h/2)*(h/2));    //视点到屏幕角距离（半径）
        double cita = Math.atan(h/(2*d));           // cita角度值

        //double x1 = pointCamera.getX() + r * Math.cos(-1 * cita - citaChange); //过程数据
        //double y1 = pointCamera.getY() + r * Math.sin(-1 * cita - citaChange); //过程数据
        //double x2 = pointCamera.getX() + r * Math.cos(cita + citaChange); //过程数据
        //double y2 = pointCamera.getY() +r * Math.sin(cita + citaChange); //过程数据


        double mathcos1 = ArithUtil.mul(r,Math.cos(-1 * cita - citaChange));
        double mathsin1 = ArithUtil.mul(r,Math.sin(-1 * cita - citaChange));
        double x1 = ArithUtil.add(pointCamera.getX().doubleValue(),mathcos1); //过程数据
        double y1 = ArithUtil.add(pointCamera.getY().doubleValue(),mathsin1); //过程数据

        double mathcos2 = ArithUtil.mul(r,Math.cos( cita + citaChange));
        double mathsin2 = ArithUtil.mul(r,Math.sin( cita + citaChange));
        double x2 = ArithUtil.add(pointCamera.getX().doubleValue(),mathcos2); //过程数据
        double y2 = ArithUtil.add(pointCamera.getY().doubleValue(),mathsin2); //过程数据


        //物体到摄像头 直线方程
        LineFormula lineFormula2 = LineFormula.claclulate(pointCamera,pointObj);
        double k2 = lineFormula2.getK();  //物体点 斜率
        double b2 = lineFormula2.getB();

        //镜面下 的点坐标
        Point2 pDown = new Point2(x1,y1);
        //镜面上 的点坐标
        Point2 pUp = new Point2(x2,y2);

        // 镜面 直线方程
        LineFormula lineFormula1 = LineFormula.claclulate(pDown,pUp);
        double k1 =  lineFormula1.getK();  //摄像头 法线 斜率
        double b1 = lineFormula1.getB();


        //摄像头 下边界直线方程
        LineFormula lineFormula4 = LineFormula.claclulate(pDown,pointCamera);
        double k4 = lineFormula4.getK();
        double b4 = lineFormula4.getB();


        //摄像头 上边界直线方程
        LineFormula lineFormula3 = LineFormula.claclulate(pUp,pointCamera);
        double k3 = lineFormula3.getK();
        double b3 = lineFormula3.getB();


        boolean isInView = false;
        //视野内外 判断 斜率判断 条件
        if(!NumberUtil.rangeInDefined(k2,k3,k4)){
            // 视野外
            isInView = false;
        }else{
            /**
             *  视野内  四个点的 x,y 排序，
             *  以 pointObj 为起始点，发出一条射线，
             *  和 图形有
             *          偶数个交点 ，图形外
             *          奇数个交点 ，图形内
             *
             */
             isInView = !isInShape(pDown,pUp,pointCamera,pointObj);
        }

        if(isInView){
            //在视野内
            double xJiao = 0;      //两条线 交点x坐标
            double yJiao = 0;      //两条线 交点y坐标
            if(x1 == x2){
                xJiao = x1;                   //两条线 交点x坐标
                yJiao = k2 * xJiao + b2;      //两条线 交点y坐标
            }else if(pointCamera.getX() == pointObj.getX()){
                xJiao = x2;                   //两条线 交点x坐标
                yJiao = k1 * xJiao + b1;      //两条线 交点y坐标
            }else{
                xJiao = (b2- b1)/(k1 - k2);      //两条线 交点x坐标
                yJiao = k1 * (b2 - b1)/(k1 - k2) + b1;      //两条线 交点y坐标
            }
            distanceH = Math.sqrt( (yJiao - y2)*(yJiao - y2) + (xJiao - x2)*(xJiao - x2) );
            tempPoint.setValue(distanceH);
        }


        tempPoint.setInView(isInView);
        return tempPoint;
    }


    /**
     * x y 平面的投影值
     * @param xyPoint
     * @param camera
     * @return
     * @throws Exception
     */
    private PointTemp xyCalculate(Point2 xyPoint,Camera camera)throws Exception{
        return treeToTwoDimensional(xyPoint,camera.getD(),camera.getjWidth(),new Point2(camera.getxP(),camera.getyP()),camera.getCtaZ());
    }

    /**
     * x z 平面的投影值
     * @param xzPoint
     * @param camera
     * @return
     * @throws Exception
     */
    private PointTemp yzCalculate(Point2 xzPoint,Camera camera)throws Exception{
        return treeToTwoDimensional(xzPoint,camera.getD(),camera.getjHeight(),new Point2(camera.getxP(),camera.getzP()),camera.getCtaX());
    }



    /**
     *点 否在 一个区域
     * @param point1  摄像头 下顶点坐标
     * @param point2  摄像头 上顶点坐标
     * @param point3  摄像头 原点坐标
     * @param target  物体坐标
     * @return
     */
    private boolean isInShape(Point2 point1,Point2 point2,Point2 point3,Point2 target){

        //物体 到原点的 直线坐标 x < xObj
        Point2 end = null;
        if(target.getX().compareTo(BigDecimal.valueOf(0)) == 0 || target.getY().compareTo(BigDecimal.valueOf(0)) == 0){
            end = new Point2(1,1);
        }else{
            end = new Point2(0,0);
        }

        LineFormula lineObjO = LineFormula.claclulate(target,end);
        LineFormula line1 = LineFormula.claclulate(point1,point2);
        LineFormula line2 = LineFormula.claclulate(point2,point3);
        LineFormula line3 = LineFormula.claclulate(point3,point1);



        Point2 result1 = LineUtil.intersectionLine(lineObjO,line1);  //和线1 交点
        Point2 result2 = LineUtil.intersectionLine(lineObjO,line2);  //和线2 交点
        Point2 result3 = LineUtil.intersectionLine(lineObjO,line3);  //和线3 交点



        int sum = 0;
        Set<Point2> set = new HashSet<>(); //去重，多个解去重
        if(result1.isInView()){
            set.add(result1);
        }
        if(result2.isInView()){
            set.add(result2);
        }
        if(result3.isInView()){
            set.add(result3);
        }

        for(Point2 pFor : set){
            sum++;
        }


        if(sum % 2==0){
            //偶数个交点 在 图形外
            return false;
        }else{
            //在图形内
            return true;
        }

    }





    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public static void main(String[] args)throws Exception{
        Point2 p1 = new Point2(1,1,false);

        Point2 p2 = new Point2(2,3,false);

        System.out.println(p1==p2);

    }


}
