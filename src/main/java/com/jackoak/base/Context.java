package com.jackoak.base;

import com.jackoak.Device.Camera;

import javax.swing.*;

public class Context {
    private static Camera Camera;

    private static JFrame jFrame;


    public static Camera getCamera() {
        return Camera;
    }

    public static void setCamera(Camera camera) {
        Camera = camera;
    }

    public static JFrame getjFrame() {
        return jFrame;
    }

    public static void setjFrame(JFrame jFrame) {
        Context.jFrame = jFrame;
    }
}
