package �ɻ���ս;

import java.awt.image.BufferedImage;

import javax.swing.*;

public abstract class fly {  //����ɻ���
    protected int x;
    protected int y;  
    protected int height; //�ɵĸ߶�  
    protected int width; //�ɵĿ��  
    protected int blood;
    protected BufferedImage image; //�������ͼƬ  
    public abstract void step();  //�ƶ����󷽷�
    public abstract boolean outOfBounds();  //Խ����󷽷�
    public static boolean boom(fly f1,fly f2){  //�����ײ
        int f1x = f1.x + f1.width/2;  
        int f1y = f1.y + f1.height/2;  
        int f2x = f2.x + f2.width/2;  
        int f2y = f2.y + f2.height/2;  
        boolean H = Math.abs(f1x - f2x) < (f1.width + f2.width)/2;  
        boolean V = Math.abs(f1y -f2y) < (f1.height + f2.height)/2;  
        return H&V;  
    }  
}