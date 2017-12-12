package 飞机大战;

public class bullet extends fly{//子弹类
	 private int speed = 3; //子弹上升的速度 
	    public bullet(int x,int y){  
	        image = gamePanel.bullet;  
	        width = image.getWidth();  
	        height = image.getHeight();  
	        this.x = x;  
	        this.y = y;  
	    }   
	    public void step() {  
	        y -= speed;  
	    }  
	    public boolean outOfBounds() {  
	        //子弹的y坐标+子弹的高度<0，越界  
	        return (y + height) < 0;  
	    }  
}
