package com.jackoak.base;

public class FrameRate implements Runnable{

    private int frameRate;//帧率

    public FrameRate(int frameRate){
        this.frameRate = frameRate;
    }

    @Override
    public void run() {
        try{
            while(true){
                if(Context.getjFrame() != null){
                    Context.getjFrame().repaint();
                }
                Thread.sleep(frameRate);
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
