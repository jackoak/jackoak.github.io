package com.jackoak.math;

import com.jackoak.shape.Point;
import com.jackoak.shape.Point2;

import java.math.BigDecimal;

/**
 * 直线方程
 * 两点 确定一条直线
 */
public class LineFormula {
    private double k;
    private double b;
    private int code;  //0 正常 函数，-1：k为无穷大

    private Point2 startPoint;  //直线中的一点
    private Point2 endPoint;    //直线中的另一点




    private LineFormula(Point2 startPoint,Point2 endPoint){
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    public static LineFormula claclulate(Point2 startPoint,Point2 endPoint){
        LineFormula lineFormula = new LineFormula(startPoint,endPoint);
        double xDis = ArithUtil.sub(startPoint.getX(),endPoint.getX());
        if(xDis == 0){
            /**
             * k 为 无穷大，垂直于 x轴
             * 方程为 x = b
             */
            lineFormula.setCode(-1);
            lineFormula.setB(startPoint.getX().doubleValue());
            return lineFormula;
        }else{
            double yDis = ArithUtil.sub(startPoint.getY(),endPoint.getY());
            double k = 0;  //摄像头 法线 斜率
            try {
                k = ArithUtil.div(yDis,xDis);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            double b = ArithUtil.sub(endPoint.getY().doubleValue(),ArithUtil.mul(k,endPoint.getX().doubleValue())) ;
            lineFormula.setK(k);
            lineFormula.setB(b);
        }

        return lineFormula;
    }


    public double getK() {
        return k;
    }

    public void setK(double k) {
        this.k = k;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public Point2 getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(Point2 startPoint) {
        this.startPoint = startPoint;
    }

    public Point2 getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(Point2 endPoint) {
        this.endPoint = endPoint;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }


}
