package com.jackoak.shape;

import java.awt.*;

public class Line extends  BaseShapeAb{

    private Point startPoint; //起点
    private Point endPoint;   //终点

    public Line(Point startPoint,Point endPoint){
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }



    @Override
    public void paint(Graphics g) throws Exception {
        Point2 startPoint2 = startPoint.getPoint2();
        Point2 endPoint2 = endPoint.getPoint2();

        if(startPoint2.isInView() && endPoint2.isInView()){
            //两个点 都在视野内时，才渲染
            int x1 = startPoint2.getX().intValue();
            int y1 = (int)startPoint2.getY().intValue();
            int x2 = (int)endPoint2.getX().intValue();
            int y2 = (int)endPoint2.getY().intValue();
            g.drawLine(x1,y1,x2,y2);
        }
    }


}
