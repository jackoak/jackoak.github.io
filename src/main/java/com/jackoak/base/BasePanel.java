package com.jackoak.base;

import com.jackoak.shape.BaseShape;

import javax.swing.JPanel;
import java.awt.*;
import java.util.List;
import java.util.LinkedList;

public class BasePanel extends JPanel {

    private List<BaseShape> list = new LinkedList<BaseShape>();

    public void add(BaseShape shape){
        list.add(shape);
    }

    public void remove(BaseShape shape){
        list.remove(shape);
    }

    @Override
    public void paint(Graphics g){
        for(BaseShape shapeFor : list){
            try {
                shapeFor.paint(g);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
