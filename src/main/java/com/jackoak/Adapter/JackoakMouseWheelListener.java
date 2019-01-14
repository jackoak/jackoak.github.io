package com.jackoak.Adapter;

import com.jackoak.Device.Camera;
import com.jackoak.base.Context;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class JackoakMouseWheelListener implements MouseWheelListener {
    private  double mouseWheel = 2;  //步进距离

    public JackoakMouseWheelListener(double mouseWheel){
        this.mouseWheel = mouseWheel;
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        Camera camera = Context.getCamera();

        if(e.getWheelRotation()==1){//向前滑动
            camera.dMove( 1 * mouseWheel * 1);
        }else if(e.getWheelRotation()== -1){//向后滑动
            camera.dMove( 1 * mouseWheel * -1);
        }
        System.out.println("camera.getD() : " + camera.getD());
    }
}
