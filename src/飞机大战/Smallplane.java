package 飞机大战;

import java.util.Random;

public class Smallplane extends fly {
	private static double speed = 2; // 敌机每次下落2个单位长度
	private int score = 5; // 敌机包含的奖励分数

	// 对外提供的读取敌机奖励分数的方法
	public int getScore() {
		return score;
	}

	public Smallplane() {
		image = gamePanel.smallplane;
		width = image.getWidth();
		height = image.getHeight();
		y = -height;
		blood = 2;
		Random r = new Random();
		x = r.nextInt(gamePanel.width - width);
	}

	public bullet2[] shoot() {
		bullet2[] bullets = null;
		Random r = new Random();
		//int x = r.nextInt(2);
		//if (x == 1) {
			bullets = new bullet2[1];
			bullets[0] = new bullet2(x + width / 2 - gamePanel.bullet.getWidth() / 2, y - gamePanel.bullet.getHeight());
		//}
		return bullets;
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