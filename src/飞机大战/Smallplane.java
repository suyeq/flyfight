package �ɻ���ս;

import java.util.Random;

public class Smallplane extends fly {
	private static double speed = 2; // �л�ÿ������2����λ����
	private int score = 5; // �л������Ľ�������

	// �����ṩ�Ķ�ȡ�л����������ķ���
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