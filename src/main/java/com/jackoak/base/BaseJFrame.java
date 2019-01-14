package com.jackoak.base;

import com.jackoak.test.DrawCircle;

import javax.swing.*;

public class BaseJFrame extends JFrame{

    private  BasePanel basePanel;

    public  BaseJFrame(BasePanel basePanel){
        this.basePanel = basePanel;
        initialize();//调用初始化方法
    }


    /**
     *  初始化方法
     */
    private void initialize(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置窗体的关闭方式
        setContentPane(basePanel);//设置窗体面板为绘图面板对象
        this.setTitle("我的世界");//设置窗体标题
    }
}
