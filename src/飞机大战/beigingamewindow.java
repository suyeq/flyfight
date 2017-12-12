package 飞机大战;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
public class beigingamewindow {
	Icon icon = new ImageIcon("src/images/logo.PNG");
	Icon icon1 = new ImageIcon("src/images/button.PNG");
	public JFrame frame1 = new JFrame("游戏开始");
	private JButton alabel1 = new JButton("New game", icon1);
	private JButton alabel2 = new JButton("Top 10 Score", icon1);
	private JButton alabel3 = new JButton("Help", icon1);
	private JButton alabel4 = new JButton("Exit", icon1);
	private JLabel alabel5 = new JLabel(icon);
	private MyMouseListener myAction1 = new MyMouseListener();
    //private GameFrame tx=new GameFrame();
	AudioClip audio = Applet.newAudioClip(gamePanel.c4);
	public void beginwindow() {
		frame1.setLayout(null);
		frame1.setSize(450, 670);
		frame1.setLocation(400, 50);
		JPanel bj = new JPanel() {
			protected void paintComponent(Graphics g) {
				Image bg;
				try {
					bg = ImageIO.read(new File("src/images/游戏背景.PNG"));
					g.drawImage(bg, 0, 0, getWidth(), getHeight(), null);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		Font font1 = new Font("华文行楷", Font.BOLD, 20);
		alabel5.setBounds(160, 80, 96, 122);
		alabel1.setBounds(135, 250, 170, 50);
		alabel2.setBounds(135, 320, 170, 50);
		alabel3.setBounds(135, 390, 170, 50);
		alabel4.setBounds(135, 460, 170, 50);
		alabel1.setHorizontalTextPosition(SwingConstants.CENTER);
		alabel1.setContentAreaFilled(false);
		alabel1.setFont(font1);
		alabel1.setForeground(Color.gray);
		alabel1.setBorderPainted(false);
		alabel2.setHorizontalTextPosition(SwingConstants.CENTER);
		alabel2.setContentAreaFilled(false);
		alabel2.setFont(font1);
		alabel2.setForeground(Color.gray);
		alabel2.setBorderPainted(false);
		alabel3.setHorizontalTextPosition(SwingConstants.CENTER);
		alabel3.setContentAreaFilled(false);
		alabel3.setFont(font1);
		alabel3.setForeground(Color.gray);
		alabel3.setBorderPainted(false);
		alabel4.setHorizontalTextPosition(SwingConstants.CENTER);
		alabel4.setContentAreaFilled(false);
		alabel4.setFont(font1);
		alabel4.setForeground(Color.gray);
		alabel4.setBorderPainted(false);
		frame1.setContentPane(bj);
		frame1.setLayout(null);
		frame1.add(alabel5);
		frame1.add(alabel1);
		frame1.add(alabel2);
		frame1.add(alabel3);
		frame1.add(alabel4);
		alabel1.addMouseListener(myAction1);
		alabel2.addMouseListener(myAction1);
		alabel3.addMouseListener(myAction1);
		alabel4.addMouseListener(myAction1);
		frame1.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				int n = JOptionPane.showConfirmDialog(null, "是否退出?", "游戏结束", JOptionPane.YES_NO_OPTION);
				if (n == JOptionPane.YES_OPTION) {
					/*window q=new window();
					q.mywindow();
					q.frame.setVisible(true);
					frame1.setVisible(false);*/
					System.exit(1);
				} else {
					beginwindow();
					frame1.setVisible(true);
				}
			}
		});
	}

	class MyMouseListener implements MouseListener {
		public void mouseClicked(MouseEvent arg0) {
		}

		public void mouseEntered(MouseEvent arg0) {
			audio.play();
			if (arg0.getSource() == alabel1) {
				alabel1.setForeground(Color.red);
				alabel2.setForeground(Color.gray);
				alabel3.setForeground(Color.gray);
				alabel4.setForeground(Color.gray);
			}
			if (arg0.getSource() == alabel2) {
				alabel2.setForeground(Color.red);
				alabel1.setForeground(Color.gray);
				alabel3.setForeground(Color.gray);
				alabel4.setForeground(Color.gray);
			}
			if (arg0.getSource() == alabel3) {
				alabel3.setForeground(Color.red);
				alabel2.setForeground(Color.gray);
				alabel1.setForeground(Color.gray);
				alabel4.setForeground(Color.gray);
			}
			if (arg0.getSource() == alabel4) {
				alabel4.setForeground(Color.red);
				alabel2.setForeground(Color.gray);
				alabel3.setForeground(Color.gray);
				alabel1.setForeground(Color.gray);
			}
		}

		public void mouseExited(MouseEvent arg0) {
			alabel1.setForeground(Color.gray);
			alabel2.setForeground(Color.gray);
			alabel3.setForeground(Color.gray);
			alabel4.setForeground(Color.gray);
		}

		public void mousePressed(MouseEvent arg0) {
			if (arg0.getSource() == alabel1) {
				frame1.setVisible(false);
				
				
				
				new gamePanel().main();
				System.out.println("1");
			}
			if (arg0.getSource() == alabel2) {
				System.out.println("2");
			}
			if (arg0.getSource() == alabel3) {
				System.out.println("3");
			}
			if (arg0.getSource() == alabel4) {
				int n = JOptionPane.showConfirmDialog(null, "是否退出?", "游戏结束", JOptionPane.YES_NO_OPTION);
				if (n == JOptionPane.YES_OPTION) {
					window q=new window();
					q.mywindow();
					q.frame.setVisible(true);
					frame1.setVisible(false);
				} else {
					frame1.setVisible(true);
				}
			}
		}

		public void mouseReleased(MouseEvent arg0) {
		}

	}
}
