package com.jackoak.Adapter;

import com.jackoak.Device.Camera;
import com.jackoak.base.Context;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyAdapterListener extends KeyAdapter{


    private  double stepDistance = 10;  //步进距离
    private  double rotateStep = 2; //旋转步进

   public KeyAdapterListener(double stepDistance,double rotateStep ){
       this.stepDistance = stepDistance;
       this.rotateStep = rotateStep;
   }


    @Override
    public void keyPressed(KeyEvent e) {
        Camera camera = Context.getCamera();

        //方向键
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {//下
            camera.zpMove(1 * stepDistance * -10);
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {//上
            camera.zpMove(1 * stepDistance * 10);
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {//左
            camera.ctaZMove(1 * rotateStep * 1);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {//右
            camera.ctaZMove(1 * rotateStep * -1);
        } else if (e.getKeyChar() == 'a' || e.getKeyChar() == 'A') {
            camera.ypMove(1 * stepDistance * 1);
        } else if (e.getKeyChar() == 'w' || e.getKeyChar() == 'W') {
            camera.xpMove(1 * stepDistance * 1);
        } else if (e.getKeyChar() == 'd' || e.getKeyChar() == 'D') {
            camera.ypMove(1 * stepDistance * -1);
        } else if (e.getKeyChar() == 's' || e.getKeyChar() == 'S') {
            camera.xpMove(1 * stepDistance * -1);
        }

    }
}
