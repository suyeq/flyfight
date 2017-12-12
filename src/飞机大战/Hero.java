package �ɻ���ս;

import java.util.Random;

public class Hero extends fly { // Ӣ�ۻ�

	private int hotFire; // ˫�������ӵ���
	// private int life; // ����ֵ
	private int score; // �÷�

	// �����ṩ��ȡ����ֵ�ķ���
	public int getLife() {
		return blood;
	}

	// �����ṩ�Ļ�ȡ�÷ֵķ���
	public int getScore() {
		return score;
	}

	/**
	 * Ӣ�ۻ�������޲ι��췽��
	 */
	public Hero() {
		image = gamePanel.hero0;
		height = image.getHeight();
		width = image.getTileWidth();
		x = 180;
		y = 500;
		hotFire = 0;
		blood = 100;
		score = 0;
	}

	public void step() {
		Random de = new Random();
		if (de.nextInt(2) == 0) {
			image = gamePanel.hero0;
		} else {
			image = gamePanel.hero1;
		}
	}

	public boolean outOfBounds() {
		return false;
	}

	public void move(int x, int y) { // ���괫��
		this.x = x - width / 2;
		this.y = y - height / 2;
	}

	public void getScore(fly f) {
		if (f instanceof Smallplane) {
			score += ((Smallplane) f).getScore();
		} else if (f instanceof Middleplane) {
			score += ((Middleplane) f).getscore();
		} else if (f instanceof Bigplane) {
			score += ((Bigplane) f).getScore();
		}
	}

	public void getaward(fly f) {
		if (f instanceof Items1) {
			blood += 1;
		} else if (f instanceof Items2) {
			hotFire += 100;
		}
	}

	public bullet[] shoot() {
		bullet[] bullets = null;
		// ��ʱ����˫��������
		if (hotFire != 0) { // ����˫������
			bullets = new bullet[2];
			bullet b1 = new bullet(x + width / 4 - gamePanel.bullet.getWidth() / 2, y + gamePanel.bullet.getWidth());
			bullet b2 = new bullet(x + width * 3 / 4 - gamePanel.bullet.getWidth() / 2,
					y + gamePanel.bullet.getWidth());
			bullets[0] = b1;
			bullets[1] = b2;
			hotFire -= 1;
		} else {
			// ����������
			// �ӵ�x���꣺x+Ӣ�ۻ����/2-�ӵ����/2
			// �ӵ�y���꣺y-�ӵ��߶�
			bullets = new bullet[1];
			bullets[0] = new bullet(x + width / 2 - gamePanel.bullet.getWidth() / 2, y - gamePanel.bullet.getHeight());
		}
		return bullets;
	}

	public boolean hit(fly f) {
		// ������ײ��ⷽ��������Ƿ���ײ
		boolean r = fly.boom(this, f);
		if (r) {
			if (f instanceof Items1 || f instanceof Items2) {

			} else {
				blood--;
				hotFire = 0;
			}
		}
		return r;
	}

}
