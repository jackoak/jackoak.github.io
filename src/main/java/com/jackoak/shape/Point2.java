package com.jackoak.shape;

import com.jackoak.math.LineFormula;

import java.math.BigDecimal;

public class Point2 {
    private BigDecimal y = new BigDecimal(0);
    private BigDecimal x = new BigDecimal(0);

    private boolean  inView;    //是否在视野内，0:视野内，-1视野外。   1：有意义，-1无意义

    public Point2(){}

    public Point2(double x,double y){
        this.x = new BigDecimal(Double.toString(x));
        this.y = new BigDecimal(Double.toString(y));
    }

    public Point2(double x,double y,boolean inView){
        this.x = new BigDecimal(Double.toString(x));
        this.y = new BigDecimal(Double.toString(y));
        this.inView = inView;
    }

    public BigDecimal getY() {
        return y;
    }

    public void setY(BigDecimal y) {
        this.y = y;
    }

    public BigDecimal getX() {
        return x;
    }

    public void setX(BigDecimal x) {
        this.x = x;
    }

    public boolean isInView() {
        return inView;
    }

    public void setInView(boolean inView) {
        this.inView = inView;
    }

    public static double getMinByx(LineFormula line){
        return getMinByX(line.getStartPoint(),line.getEndPoint());
    }

    public static double getMinByY(LineFormula line){
        return getMinByY(line.getStartPoint(),line.getEndPoint());
    }

    public static double getMaxByX(LineFormula line){
        return getMaxByX(line.getStartPoint(),line.getEndPoint());
    }

    public static double getMaxByY(LineFormula line){
        return getMaxByY(line.getStartPoint(),line.getEndPoint());
    }


    public static double getMinByX(Point2 point1,Point2 point2){
        return Math.min(point1.getX().doubleValue(),point2.getX().doubleValue());
    }
    public static double getMinByY(Point2 point1,Point2 point2){
        return Math.min(point1.getY().doubleValue(),point2.getY().doubleValue());
    }

    public static double getMaxByX(Point2 point1,Point2 point2){
        return Math.max(point1.getX().doubleValue(),point2.getX().doubleValue());
    }
    public static double getMaxByY(Point2 point1,Point2 point2){
        return Math.max(point1.getY().doubleValue(),point2.getY().doubleValue());
    }


    @Override
    public int hashCode() {
        String codeStr = x + "_" + y;
        return codeStr.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(!(obj instanceof Point2))
            return false;

        Point2 other = (Point2)obj;

        return ((other.getX().doubleValue() == x.doubleValue() && other.getY().doubleValue() == y.doubleValue()));

    }
}
