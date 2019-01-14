package com.jackoak.shape;

public class PointTemp {

    private double value; //坐标  x 或 y,屏幕上

    private boolean  inView;    //是否在视野内，0:视野内，-1视野外

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public boolean isInView() {
        return inView;
    }

    public void setInView(boolean inView) {
        this.inView = inView;
    }
}
