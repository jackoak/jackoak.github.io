package com.jackoak.Adapter;

import com.jackoak.Device.Camera;
import com.jackoak.base.Context;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class WindowComponentAdapter extends ComponentAdapter {

    @Override
    public void componentResized(ComponentEvent e){
        Camera camera = Context.getCamera();
        JFrame jFrame = Context.getjFrame();


        if(camera != null && jFrame != null){
            camera.setjHeight(jFrame.getHeight());
            camera.setjWidth(jFrame.getWidth());
        }
    }

}
