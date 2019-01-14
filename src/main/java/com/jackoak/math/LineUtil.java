package com.jackoak.math;

import com.jackoak.shape.Point2;

import java.math.BigDecimal;

public class LineUtil {

    /**
     * 两条直线是否有交点
     * @param line1
     * @param line2
     * @param xMin Double.NEGATIVE_INFINITY;  //x最小值 负无穷大
     * @param xMax Double.POSITIVE_INFINITY;  //x最大值 正无穷大
     * @param yMin Double.NEGATIVE_INFINITY;  //y最小值 负无穷大
     * @param yMax Double.POSITIVE_INFINITY;  //y最大值 正无穷大
     * @return
     */
    public static Point2 intersectionLine(LineFormula line1, LineFormula line2, double xMin, double xMax, double yMin, double yMax){

        Point2 result = new Point2();
        BigDecimal xB = null;
        BigDecimal yB = null;

        double k1 = line1.getK();
        double b1 = line1.getB();
        double k2 = line2.getK();
        double b2 = line2.getB();
        if(line1.getCode() == 0 && line2.getCode() == 0){//正常函数 y = kx + b
            //都是正常函数
            if(k1 == k2){
                //斜率相等  无交点
                result.setInView(false);
                return result;
            }else{
                double x = (b2 - b1)/(k1 - k2);
                double y = k1 * x + b1;
                //x 取值范围
                if(NumberUtil.rangeInDefined(x,xMin,xMax)){
                    //在 min 和 max之间 有值，则有交点
                    result.setInView(true);
                    xB = new BigDecimal(Double.toString(x));
                    yB = new BigDecimal(Double.toString(y));

                    result.setX(xB);
                    result.setY(yB);
                    return result;
                }else{
                    //无交点
                    result.setInView(false);
                    return result;
                }
            }

        }else if(line1.getCode() == 0 && line2.getCode() != 0){ //k 为无穷大 x = b
            //line1 正常函数  line2 非正常函数
            double y = k1 * b2 + b1;
            double x = b2;
            //y 取值范围
            if(NumberUtil.rangeInDefined(y,yMin,yMax)){
                //在 min 和 max之间 有值，则有交点
                result.setInView(true);
                xB = new BigDecimal(Double.toString(x));
                yB = new BigDecimal(Double.toString(y));

                result.setX(xB);
                result.setY(yB);
                return result;
            }else{
                //无交点
                result.setInView(false);
                return result;
            }
        }else if(line1.getCode() == 0 && line2.getCode() != 0){ //k 为无穷大 x = b
            //line1 非正常函数  line2 正常函数
            double y = k2 * b1 + b2;
            double x = b1;
            //y 取值范围
            if(NumberUtil.rangeInDefined(y,yMin,yMax)){
                //在 min 和 max之间 有值，则有交点
                result.setInView(true);
                xB = new BigDecimal(Double.toString(x));
                yB = new BigDecimal(Double.toString(y));

                result.setX(xB);
                result.setY(yB);
                return result;
            }else{
                //无交点
                result.setInView(false);
                return result;
            }
        }else{
            //line1 非正常函数  line2 非正常函数
            result.setInView(false);
            return result;
        }


    }



    /**
     * 两条直线是否有交点
     * @param line1
     * @param line2
     * @return
     */
    public static Point2 intersectionLine(LineFormula line1,LineFormula line2){

        double lineMinX1 = Point2.getMinByx(line1); // 线1最小 x范围
        double lineMaxX1 = Point2.getMaxByX(line1); // 线1最大 x范围
        double lineMinY1 = Point2.getMinByY(line1); // 线1最小 y范围
        double lineMaxY1 = Point2.getMaxByY(line1); // 线1最小 y范围

        Point2 startPoint = line1.getStartPoint();
        Point2 endPoint = line1.getEndPoint();

        if(startPoint.getX().compareTo(endPoint.getX())  > 0 ){
            lineMinX1 = Double.NEGATIVE_INFINITY;
            lineMaxX1 = startPoint.getX().doubleValue();
        }else{
            lineMinX1 = startPoint.getX().doubleValue();
            lineMaxX1 = Double.POSITIVE_INFINITY;
        }

        if(startPoint.getY().compareTo(endPoint.getY()) > 0 ){
            lineMinY1 = Double.NEGATIVE_INFINITY;
            lineMaxY1 = startPoint.getY().doubleValue();
        }else{
            lineMinY1 = startPoint.getY().doubleValue();
            lineMaxY1 = Double.POSITIVE_INFINITY;
        }



        double lineMinX2 = Point2.getMinByx(line2); // 线2最小 x范围
        double lineMaxX2 = Point2.getMaxByX(line2); // 线2最大 x范围
        double lineMinY2 = Point2.getMinByY(line2); // 线2最小 y范围
        double lineMaxY2 = Point2.getMaxByY(line2); // 线2最小 y范围


        double xMin = Math.max(lineMinX1,lineMinX2); //最小 x: 最小中选最大
        double xMax = Math.min(lineMaxX1,lineMaxX2); //最大 x： 最大中选最小
        double yMin = Math.max(lineMinY1,lineMinY2); //最小 y: 最小中选最大
        double yMax = Math.min(lineMaxY1,lineMaxY2); //最大 y： 最大中选最小


        return intersectionLine(line1,line2,xMin,xMax,yMin,yMax);
    }


}
