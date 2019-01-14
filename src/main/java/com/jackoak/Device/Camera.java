package com.jackoak.Device;

public class Camera {


    private double d;       //视距
    private double jWidth;  //屏幕宽
    private double jHeight;  //屏幕高


    private double xP = 0;       //摄像头O x坐标
    private double yP = 0;       //摄像头O y坐标
    private double zP = 0;       //摄像头O z坐标

    private double ctaX = 0;     //摄像头 绕x 轴旋转角度
    private double ctaZ = 0;// Math.PI / 4;     //摄像头 绕z 轴旋转角度


    public Camera(double d,double jWidth,double jHeight){
        this.d = d;
        this.jWidth = jWidth;
        this.jHeight = jHeight;
    }

    public double xpMove(double moveX){
        this.xP += moveX;
        return this.xP;
    }

    public double ypMove(double moveY){
        this.yP += moveY;
        return this.yP;
    }

    public double zpMove(double moveZ){
        this.zP += moveZ;
        return this.zP;
    }


    /**
     * 移动视距
     * @param moved
     * @return
     */
    public double dMove(double moved){
        this.d += moved;
        return this.d;
    }


    /**
     * 绕 z轴旋转
     * @param moveCtaZ
     * @return
     */
    public double ctaZMove(double moveCtaZ){
        double temp = Math.PI * moveCtaZ / 180;
        this.ctaZ += temp;
        return this.ctaZ;
    }

    public double getD() {
        return d;
    }

    public void setD(double d) {
        this.d = d;
    }

    public double getjWidth() {
        return jWidth;
    }

    public void setjWidth(double jWidth) {
        this.jWidth = jWidth;
    }

    public double getjHeight() {
        return jHeight;
    }

    public void setjHeight(double jHeight) {
        this.jHeight = jHeight;
    }

    public double getxP() {
        return xP;
    }

    public void setxP(double xP) {
        this.xP = xP;
    }

    public double getyP() {
        return yP;
    }

    public void setyP(double yP) {
        this.yP = yP;
    }

    public double getCtaX() {
        return ctaX;
    }

    public void setCtaX(double ctaX) {
        this.ctaX = ctaX;
    }

    public double getCtaZ() {
        return ctaZ;
    }

    public void setCtaZ(double ctaZ) {
        this.ctaZ = ctaZ;
    }

    public double getzP() {
        return zP;
    }

    public void setzP(double zP) {
        this.zP = zP;
    }
}
