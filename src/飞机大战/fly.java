package 飞机大战;

import java.awt.image.BufferedImage;

import javax.swing.*;

public abstract class fly {  //抽象飞机类
    protected int x;
    protected int y;  
    protected int height; //飞的高度  
    protected int width; //飞的宽度  
    protected int blood;
    protected BufferedImage image; //飞行物的图片  
    public abstract void step();  //移动抽象方法
    public abstract boolean outOfBounds();  //越界抽象方法
    public static boolean boom(fly f1,fly f2){  //检测碰撞
        int f1x = f1.x + f1.width/2;  
        int f1y = f1.y + f1.height/2;  
        int f2x = f2.x + f2.width/2;  
        int f2y = f2.y + f2.height/2;  
        boolean H = Math.abs(f1x - f2x) < (f1.width + f2.width)/2;  
        boolean V = Math.abs(f1y -f2y) < (f1.height + f2.height)/2;  
        return H&V;  
    }  
}