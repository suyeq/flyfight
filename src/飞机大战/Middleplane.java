package �ɻ���ս;

import java.util.Random;

public class Middleplane extends fly {  
    
    public static final int FIRE0 = 0; //����������0��˵������˫������  
    public static final int FIRE1 = 1; //����������1��˵������һ������  
    private static int xspeed = 3; 
    private static double yspeed = 2; 
    private int awardType; 
    private int score=10;
    public Middleplane(){  
        image = gamePanel.middleplane;  
        width = image.getWidth();  
        height= image.getHeight();  
        y = -height; 
        blood=3;
        Random r = new Random();  
        x = r.nextInt(gamePanel.width - width);  
        awardType = r.nextInt(2);  
    }  
    public int getAwardType() {
		return awardType;
	}
    public void setspeed(double yspeed) {
		this.yspeed=yspeed ;
	}

	public double getspeed() {
		return yspeed;
	}
    public int getscore(){
    	return score;
    }
    public void step() {    
        x += xspeed;  
        y += yspeed;  
        if(x < 0 || x > gamePanel.width - width){  
            xspeed *= -1;  
        }  
    }  
    public boolean outOfBounds() {  
        //��ɻ���y����>��Ϸ���棬Խ��  
        return y > gamePanel.heigth;  
    }  
}  