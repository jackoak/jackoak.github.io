package com.jackoak.test;

import com.jackoak.Adapter.JackoakMouseWheelListener;
import com.jackoak.Adapter.KeyAdapterListener;
import com.jackoak.Adapter.WindowComponentAdapter;
import com.jackoak.Device.Camera;
import com.jackoak.base.BaseJFrame;
import com.jackoak.base.BasePanel;
import com.jackoak.base.Context;
import com.jackoak.base.FrameRate;
import com.jackoak.shape.Cube;
import com.jackoak.shape.Line;
import com.jackoak.shape.Point;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseWheelListener;

public class Bootstrap {
    public static void main(String[] args)throws Exception{

        final double d = 700;
        int width = 800;
        int height = 800;
        final int frameRate = 50;//50毫秒重绘一次界面

        final  double stepDistance = 1000;  //步进距离
        final double rotateStep = 2; //旋转步进

        final double mouseWhile = 10;  //步进距离



        //基本面板
        BasePanel basePanel = new BasePanel();

        //键盘监听器
        KeyAdapterListener keyAdapterListener = new KeyAdapterListener(stepDistance,rotateStep);

        //窗口监听器
        WindowComponentAdapter windowComponentAdapter = new WindowComponentAdapter();

        MouseWheelListener mouseWheelListener = new JackoakMouseWheelListener(mouseWhile);

        Camera camera = new Camera(d,width,height);
        Context.setCamera(camera); //摄像头 存入 context上下文

        BaseJFrame frame = new BaseJFrame(basePanel);
        frame.addKeyListener(keyAdapterListener);
        frame.addComponentListener(windowComponentAdapter);
        frame.addMouseWheelListener(mouseWheelListener);

        frame.setSize(width,height);
        frame.setVisible(true);
        Context.setjFrame(frame);        // jframe 存入context 上下文

        Line line0 = new Line(new Point(100,0,-300),new Point(100,0,300));

        Line line1 = new Line(new Point(20,0,-300),new Point(100,0,-300));
        Line line2 = new Line(new Point(20,0,0),new Point(100,0,0));
        Line line3 = new Line(new Point(20,0,300),new Point(100,0,300));
        Line line4 = new Line(new Point(20,0,-300),new Point(20,0,300));


       /* basePanel.add(line0);
        basePanel.add(line1);
        basePanel.add(line2);
        basePanel.add(line3);
        basePanel.add(line4);*/

        Point pointO = new Point(8*25000,1,1);
        Cube cube = new Cube(pointO,4*25000,4*25000,4*25000);
        basePanel.add(cube);


        FrameRate frameRateThread = new FrameRate(frameRate);
        new Thread(frameRateThread).start();
    }

}
