package 飞机大战;

import java.util.Random;

public class Bigplane extends fly{
	private static double speed = 5; // 敌机每次下落2个单位长度
	private int score = 50; // 敌机包含的奖励分数
	public int getScore() {
		return score;
	}

	public Bigplane() {
		image = gamePanel.bigplane;
		width = image.getWidth();
		height = image.getHeight();
		y = -height;
		blood = 5;
		Random r = new Random();
		x = r.nextInt(gamePanel.width - width);
	}
	public void step() {
		y += speed;
	}

	public void setspeed(double speed) {
		this.speed = speed;
	}

	public double getspeed() {
		return speed;
	}

	public boolean outOfBounds() {
		return y > gamePanel.heigth;
	}
}
