package ·É»ú´óÕ½;

import java.util.Random;

public class Items2 extends fly {
	private static double speed = 2;
	private int awardType = 1;

	public Items2() {
		image = gamePanel.items2;
		width = image.getWidth();
		height = image.getHeight();
		y = -height;
		blood = -1;
		Random r = new Random();
		x = r.nextInt(gamePanel.width - width);
	}

	public int getAwardType() {
		return awardType;
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
