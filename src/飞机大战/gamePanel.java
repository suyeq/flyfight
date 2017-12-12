package 飞机大战;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.Timer;

import javax.imageio.ImageIO;
import javax.swing.*;

public class gamePanel extends JPanel {
	public static int width = 470; // 面板宽度
	public static int heigth = 670; // 面板高度
	public static BufferedImage smallplane; // 敌机图片
	public static BufferedImage middleplane; // 大飞机
	public static BufferedImage hero0; // 英雄机状态0
	public static BufferedImage hero1; // 英雄机状态1
	public static BufferedImage bullet; // 子弹
	public static BufferedImage pause; // 暂停图片
	public static BufferedImage gameover; // 游戏结束
	public static BufferedImage bullet2; // 游戏结束
	public static BufferedImage bigplane;
	public static BufferedImage items1;
	public static BufferedImage items2;
	public static URL c1;
	public static URL c2;
	public static URL c3;
	public static URL c4;
	static {
		try {
			smallplane = ImageIO.read(new File("src/images/s.PNG"));
			middleplane = ImageIO.read(new File("src/images/m.PNG"));
			bullet = ImageIO.read(new File("src/images/子弹.PNG"));
			pause = ImageIO.read(new File("src/images/暂停.PNG"));
			hero0 = ImageIO.read(new File("src/images/hero0.PNG"));
			hero1 = ImageIO.read(new File("src/images/hero1.PNG"));
			gameover = ImageIO.read(new File("src/images/游戏结束.PNG"));
			bullet2 = ImageIO.read(new File("src/images/子弹2.PNG"));
			bigplane = ImageIO.read(new File("src/images/b.PNG"));
			items1 = ImageIO.read(new File("src/images/道具1.PNG"));
			items2 = ImageIO.read(new File("src/images/道具2.PNG"));
			c1 = new URL("file:src/sound/game_music.wav");
			c2 = new URL("file:src/sound/fire_bullet.wav");
			c3 = new URL("file:src/sound/small_plane_killed.wav");
			c4 = new URL("file:src/sound/button.wav");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public Hero hero = new Hero();
	public fly[] flyers = {}; // 存储所有敌人对象的数组
	public bullet[] bullets = {}; // 存储所有子弹对象的数组态
	public bullet2[] dbullets = {};
	private int state = START;
	public static final int START = 0;
	public static final int RUNNING = 1;
	public static final int PAUSE = 2;
	public static final int GAME_OVER = 3;
	music1 t1 = new music1();
	music2 t2 = new music2();
	JFrame app = new JFrame("GameFrame");

	public void main() {
		t1.start();
		t2.start();
		gamePanel game = new gamePanel();
		JPanel bj = new JPanel() {
			protected void paintComponent(Graphics g) {
				Image bg;
				try {
					bg = ImageIO.read(new File("src/images//游戏背景.PNG"));
					g.drawImage(bg, 0, 0, getWidth(), getHeight(), null);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		app.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				int n = JOptionPane.showConfirmDialog(null, "是否退出?", "游戏结束", JOptionPane.YES_NO_OPTION);
				if (n == JOptionPane.YES_OPTION) {
					app.setVisible(false);
					beigingamewindow q = new beigingamewindow();
					q.beginwindow();
					q.frame1.setVisible(true);
					app.setVisible(false);
					// t1.stop();
				} else {
					app.setVisible(true);
				}
			}
		});
		game.setPreferredSize(new Dimension(470, 670));
		game.setOpaque(false);
		app.setSize(470, 670);
		app.setLocation(400, 50);
		app.setContentPane(bj);
		app.add(game);
		game.setForeground(Color.white);
		app.setVisible(true);
		game.start();
	}

	public void start() {
		this.addMouseMotionListener(new MyMouseMotionListener());
		this.addMouseListener(new MyMouseListener());
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			private int runTimes = 0;

			public void run() {
				if (state == RUNNING) {
					runTimes++;
					if (runTimes % 50 == 0) {
						nextOne();
					}
					if (runTimes % 1000 == 0) {
						new Smallplane().setspeed(new Smallplane().getspeed() + 0.1);
						new Middleplane().setspeed(new Middleplane().getspeed() + 0.1);
						new bullet2(0, 0).setspeed(new bullet2(0, 0).getspeed() + 0.1);
						System.out.println(new Smallplane().getspeed());
					}
					for (int i = 0; i < flyers.length; i++) {
						flyers[i].step();
					}
					if (runTimes % 20 == 0) {
						shoot();

					}
					if (runTimes % 150 == 0) {
						shoot2();

					}
					for (int i = 0; i < bullets.length; i++) {
						bullets[i].step();
					}
					for (int i = 0; i < dbullets.length; i++) {
						dbullets[i].step();
					}
					hero.step();
					boom();
					hit();
					hit2();
					outOfBounds();
				}
				repaint();
			}
		}, 10, 10);
	}

	class MyMouseMotionListener implements MouseMotionListener {

		public void mouseDragged(MouseEvent arg0) {
		}

		public void mouseMoved(MouseEvent e) {
			if (state == RUNNING) {
				int x = e.getX();
				int y = e.getY();
				hero.move(x, y);
			}
		}
	}

	class MyMouseListener implements MouseListener {
		public void mouseClicked(MouseEvent arg0) {
			if (arg0.getButton() == MouseEvent.BUTTON1) {
				shoot();
			}
			if (arg0.getButton() == MouseEvent.BUTTON3) {
				if (state == START || state == PAUSE) { // START或者PAUSE状态，单击才会改改为RUNNING状态
					state = RUNNING;
				} else if (state == RUNNING) { // 游戏点击暂停
					state = PAUSE;
					
				} else if (state == GAME_OVER) { // 游戏结束后单击，游戏初始化
					int n = JOptionPane.showConfirmDialog(null, "是否继续游戏?", "游戏结束", JOptionPane.YES_NO_OPTION);
					if (n == JOptionPane.YES_OPTION) {
						state = START;
						flyers = new fly[0];
						bullets = new bullet[0];
						dbullets = new bullet2[0];
						hero = new Hero();
						new Smallplane().setspeed(2);
						new Middleplane().setspeed(2);
						new bullet2(0, 0).setspeed(3);
						repaint();
					} else {
						app.setVisible(false);
					}
				}
			}

		}

		public void mouseEntered(MouseEvent e) {
			if (state == PAUSE) {
				state = RUNNING;
			}
		}

		public void mouseExited(MouseEvent arg0) {
			if (state == RUNNING) {
				// 处于RUNNING状态下，鼠标移出才暂停
				state = PAUSE;
			}
		}

		public void mousePressed(MouseEvent arg0) {
		}

		public void mouseReleased(MouseEvent arg0) {
		}
	}

	public void paint(Graphics g) {
		paintHero(g);
		paintFlyers(g);
		paintBullets(g);
		paintScore1(g);
		paintdBullets(g);
		if (state == PAUSE) {
			g.drawImage(pause, 70, 50, null);
		} else if (state == GAME_OVER) {
			g.drawImage(gameover, 70, 50, null);
		}

	}

	public void paintHero(Graphics g) {
		g.drawImage(hero.image, hero.x, hero.y, null);
	}

	public void paintFlyers(Graphics g) {
		for (int i = 0; i < flyers.length; i++) {
			g.drawImage(flyers[i].image, flyers[i].x, flyers[i].y, null);
		}
	}

	public void paintBullets(Graphics g) {
		for (int i = 0; i < bullets.length; i++) {
			g.drawImage(bullets[i].image, bullets[i].x, bullets[i].y, null);
		}
	}

	public void paintdBullets(Graphics g) {
		for (int i = 0; i < dbullets.length; i++) {
			g.drawImage(dbullets[i].image, dbullets[i].x, dbullets[i].y, null);
		}
	}

	public void nextOne() {
		Random r = new Random();
		fly f = null;
		int t = r.nextInt(20);
		if (t == 0) { // 只有随机数取0时才创建大飞机
			f = new Middleplane();
		} else if (t >= 18 && t <= 19) { // 其余全部生成敌机
			f = new Bigplane();
		} else {
			f = new Smallplane();
		}
		flyers = Arrays.copyOf(flyers, flyers.length + 1);
		flyers[flyers.length - 1] = f;
	}

	public void shoot() {
		bullet[] newBullets = hero.shoot();
		bullets = Arrays.copyOf(bullets, bullets.length + newBullets.length);
		System.arraycopy(newBullets, 0, bullets, bullets.length - newBullets.length, newBullets.length);

	}

	public void shoot2() {
		for (int i = 0; i < flyers.length; i++) {
			if (flyers[i] instanceof Smallplane) {
				if (((Smallplane) flyers[i]).shoot() != null) {
					bullet2[] newBullets = ((Smallplane) flyers[i]).shoot();
					dbullets = Arrays.copyOf(dbullets, dbullets.length + newBullets.length);
					System.arraycopy(newBullets, 0, dbullets, dbullets.length - newBullets.length, newBullets.length);
				}
			}
		}

	}

	public void boom() {
		for (int i = 0; i < bullets.length; i++) {
			for (int j = 0; j < flyers.length; j++) {
				if (fly.boom(bullets[i], flyers[j])) {
					if (flyers[j].blood > 1) {
						flyers[j].blood--;
						bullets[i] = bullets[bullets.length - 1];
						bullets = Arrays.copyOf(bullets, bullets.length - 1);
						i--;
						break;
					} else if (flyers[j].blood <= 1 && flyers[j].blood >= 0) {
						//music3 t3 = new music3();
						// t3.start();
						if (flyers[j] instanceof Middleplane) {
							fly f = null;
							if (((Middleplane) flyers[j]).getAwardType() == 0) {
								f = new Items1();
							} else {
								f = new Items2();
							}
							flyers = Arrays.copyOf(flyers, flyers.length + 1);
							flyers[flyers.length - 1] = f;
						}
						hero.getScore(flyers[j]);
						flyers[j] = flyers[flyers.length - 1];
						flyers = Arrays.copyOf(flyers, flyers.length - 1);
						bullets[i] = bullets[bullets.length - 1];
						bullets = Arrays.copyOf(bullets, bullets.length - 1);
						i--;
						break;
					}
				}
			}
		}
	}

	public void paintScore1(Graphics g) {
		int x = 10;
		int y = 15;
		Font font = new Font("华文行楷", Font.ITALIC, 20);
		g.setFont(font);
		g.drawString("SCORE: " + hero.getScore(), x, y);
		y += 20;
		g.drawString("LIFE: " + hero.getLife(), x, y);
	}

	public void outOfBounds() {
		fly[] Flives = new fly[flyers.length];
		int index = 0;
		for (int i = 0; i < flyers.length; i++) {
			if (!flyers[i].outOfBounds()) {
				Flives[index] = flyers[i];
				index++;
			}
		}
		flyers = Arrays.copyOf(Flives, index);
		bullet[] Blives = new bullet[bullets.length];
		index = 0;
		for (int i = 0; i < bullets.length; i++) {
			if (!bullets[i].outOfBounds()) {
				Blives[index] = bullets[i];
				index++;
			}
		}
		bullets = Arrays.copyOf(Blives, index);
		bullet2[] aBlives = new bullet2[dbullets.length];
		index = 0;
		for (int i = 0; i < dbullets.length; i++) {
			if (!dbullets[i].outOfBounds()) {
				aBlives[index] = dbullets[i];
				index++;
			}
		}
		dbullets = Arrays.copyOf(aBlives, index);

	}

	public void hit() {
		fly[] lives = new fly[flyers.length];
		int index = 0;
		for (int i = 0; i < flyers.length; i++) {
			if (!hero.hit(flyers[i])) {
				lives[index] = flyers[i];
				index++;
			} else {
				if (flyers[i] instanceof Items1) {
					hero.getaward(flyers[i]);
				}
				if (flyers[i] instanceof Items2) {
					hero.getaward(flyers[i]);
				}
			}
		}
		if (hero.getLife() <= 0) {
			state = GAME_OVER;
		}
		flyers = Arrays.copyOf(lives, index);

	}

	public void hit2() {
		bullet2[] lives = new bullet2[dbullets.length];
		// 记录存活的敌人
		int index = 0;
		for (int i = 0; i < dbullets.length; i++) {
			if (!hero.hit(dbullets[i])) {
				lives[index] = dbullets[i];
				index++;
			}
		}
		if (hero.getLife() <= 0) {
			state = GAME_OVER;

		}
		dbullets = Arrays.copyOf(lives, index);
	}

	class music1 extends Thread {
		public void run() {
			AudioClip audio = Applet.newAudioClip(c1);
			while (true) {
				audio.play();
			}
		}
	}

	class music2 extends Thread {
		public void run() {
			AudioClip audio = Applet.newAudioClip(c2);
			while (true) {
				audio.play();
				try{
					Thread.sleep(300);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
	}

	class music3 implements Runnable {
		public void run() {
			AudioClip audio = Applet.newAudioClip(c3);
			audio.play();
		}
	}
}
