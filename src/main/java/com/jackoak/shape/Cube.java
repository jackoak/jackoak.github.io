package com.jackoak.shape;


import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Cube extends  BaseShapeAb{

    private Point pointO;//圆角坐标

    private double len;     // 长
    private double width;  //宽
    private double height; //高

    private List<Line> list = new ArrayList<Line>(); //12 条线段

    public Cube(Point pointO,double len,double width,double height){
        this.pointO = pointO;
        this.len = len;
        this.width = width;
        this.height = height;
        calculete();
    }

    private void calculete(){
        double xO = pointO.getX();
        double yO = pointO.getY();
        double zO = pointO.getZ();

        Point p1 = new Point(xO + len,yO,zO);
        Point p2 = new Point(xO + len,yO ,zO + height);
        Point p3 = new Point(xO,yO,zO + height);

        Point p4 = new Point(xO,yO + width,zO);
        Point p5 = new Point(xO + len,yO + width,zO);
        Point p6 = new Point(xO + len,yO + width,zO + height);
        Point p7 = new Point(xO,yO + width,zO + height);

        Line line1 = new Line(pointO,p1);
        Line line2 = new Line(p1,p2);
        Line line3 = new Line(p2,p3);
        Line line4 = new Line(p3,pointO);

        Line line5 = new Line(p4,p5);
        Line line6 = new Line(p5,p6);
        Line line7 = new Line(p6,p7);
        Line line8 = new Line(p7,p4);

        Line line9 = new Line(pointO,p4);
        Line line10 = new Line(p1,p5);
        Line line11 = new Line(p2,p6);
        Line line12 = new Line(p3,p7);

        list.add(line1);
        list.add(line2);
        list.add(line3);
        list.add(line4);
        list.add(line5);
        list.add(line6);
        list.add(line7);
        list.add(line8);
        list.add(line9);
        list.add(line10);
        list.add(line11);
        list.add(line12);

    }

    @Override
    public void paint(Graphics g) throws Exception {
        for(Line listFor : list){
            listFor.paint(g);
        }
    }
}
