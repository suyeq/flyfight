package �ɻ���ս;

public class bullet extends fly{//�ӵ���
	 private int speed = 3; //�ӵ��������ٶ� 
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
	        //�ӵ���y����+�ӵ��ĸ߶�<0��Խ��  
	        return (y + height) < 0;  
	    }  
}
