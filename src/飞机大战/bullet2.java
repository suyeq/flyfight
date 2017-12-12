package 飞机大战;

public class bullet2 extends fly{
	private static double speed = 3; 
    public bullet2(int x,int y){  
        image = gamePanel.bullet2;  
        width = image.getWidth();  
        height = image.getHeight();  
        this.x = x;  
        this.y = y+30;  
    }   
    public void step() {  
        y += speed;  
    }
    public void setspeed(double speed){
    	this.speed=speed;
    }
    public double getspeed(){
    	return this.speed;
    }
    public boolean outOfBounds() {  
        //子弹的y坐标+子弹的高度<0，越界  
        return (y + height) > 670;  
    }  
}
