package 飞机大战;

import java.util.Random;

public class Middleplane extends fly {  
    
    public static final int FIRE0 = 0; //奖励类型是0，说明奖励双倍火力  
    public static final int FIRE1 = 1; //奖励类型是1，说明奖励一次生命  
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
        //大飞机的y坐标>游戏界面，越界  
        return y > gamePanel.heigth;  
    }  
}  