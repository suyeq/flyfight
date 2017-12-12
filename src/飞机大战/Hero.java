package 飞机大战;

import java.util.Random;

public class Hero extends fly { // 英雄机

	private int hotFire; // 双倍火力子弹数
	// private int life; // 生命值
	private int score; // 得分

	// 对外提供读取生命值的方法
	public int getLife() {
		return blood;
	}

	// 对外提供的获取得分的方法
	public int getScore() {
		return score;
	}

	/**
	 * 英雄机对象的无参构造方法
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

	public void move(int x, int y) { // 坐标传入
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
		// 何时开启双倍火力：
		if (hotFire != 0) { // 创建双倍火力
			bullets = new bullet[2];
			bullet b1 = new bullet(x + width / 4 - gamePanel.bullet.getWidth() / 2, y + gamePanel.bullet.getWidth());
			bullet b2 = new bullet(x + width * 3 / 4 - gamePanel.bullet.getWidth() / 2,
					y + gamePanel.bullet.getWidth());
			bullets[0] = b1;
			bullets[1] = b2;
			hotFire -= 1;
		} else {
			// 单倍火力：
			// 子弹x坐标：x+英雄机宽度/2-子弹宽度/2
			// 子弹y坐标：y-子弹高度
			bullets = new bullet[1];
			bullets[0] = new bullet(x + width / 2 - gamePanel.bullet.getWidth() / 2, y - gamePanel.bullet.getHeight());
		}
		return bullets;
	}

	public boolean hit(fly f) {
		// 调用碰撞检测方法，检测是否碰撞
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
