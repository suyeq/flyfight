package �ɻ���ս;

import java.util.Random;

public class Bigplane extends fly{
	private static double speed = 5; // �л�ÿ������2����λ����
	private int score = 50; // �л������Ľ�������
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
