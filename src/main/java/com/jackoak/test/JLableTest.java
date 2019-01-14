package com.jackoak.test;


import com.sun.xml.internal.ws.api.pipe.Tube;

import javax.swing.*;
import java.awt.*;

public class JLableTest {
    public static void main(String[] args)throws Exception{
        JFrame jFrame = new JFrame("jLable测试");
        JLabel jLabel = new JLabel("这是一个标签",JLabel.CENTER);

        Font font = new Font("楷体", Font.BOLD + Font.PLAIN,35);
        jFrame.setFont(font);
        jLabel.setFont(font);
        jLabel.setForeground(Color.blue);
        //jFrame.add(jLabel);
        jFrame.setSize(800,600);
        jFrame.setLocation(250,350);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);

        Graphics graphics = jFrame.getGraphics();
        graphics.drawLine(0,0,100,200);

    }

}
