package �ɻ���ս;

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
        //�ӵ���y����+�ӵ��ĸ߶�<0��Խ��  
        return (y + height) > 670;  
    }  
}
