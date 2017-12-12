package 飞机大战;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import java.io.*;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.*;

import 数据.DBOperation;
import 数据.MyDBConnection;
public class window {
	public JFrame frame = new JFrame("飞机大战");
	private JLabel label1=new JLabel("游戏id:");
	private JTextField txt1=new JTextField();
	private JLabel label2=new JLabel("密   码:");
	private JTextField txt2=new JTextField();
	private JButton btn1=new JButton("登陆");
	private JButton btn2=new JButton("注册");
	private JButton btn3=new JButton("退出");
	private MyMouseListener myAction=new MyMouseListener();
	private MyKeyListener mykey=new MyKeyListener();
	private String t1;
	private String t2;
	String DBDriver="com.mysql.jdbc.Driver";
	String DBURL="jdbc:mysql://localhost:3306/gd";
	String DBUser="root";
	String DBPass="473721601";
	MyDBConnection myDB=new MyDBConnection(DBDriver,DBURL,DBUser,DBPass);
	DBOperation myOpr=new DBOperation(myDB);
	beigingamewindow wind2=new beigingamewindow();
	AudioClip audio = Applet.newAudioClip(gamePanel.c4);
	public void mywindow() {
		frame.setLayout(null);
		frame.setSize(470, 470);
		//setDefauitCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(400, 200);
		Font font=new Font("华文行楷",Font.BOLD,20);
		label1.setFont(font);
		label1.setForeground(Color.gray);
		label2.setFont(font);
		label2.setForeground(Color.gray);
		txt1.setOpaque(false);
		txt2.setOpaque(false);
		btn1.setContentAreaFilled(false);
		btn1.setFont(font);
	    btn1.setForeground(Color.gray);
	    btn1.setBorder(BorderFactory.createRaisedBevelBorder());
	    btn2.setContentAreaFilled(false);
		btn2.setFont(font);
		btn2.setBorder(BorderFactory.createRaisedBevelBorder());
	    btn2.setForeground(Color.gray);
	    btn3.setContentAreaFilled(false);
		btn3.setFont(font);
		btn3.setBorder(BorderFactory.createRaisedBevelBorder());
	    btn3.setForeground(Color.gray);
		@SuppressWarnings("serial")
		JPanel bj = new JPanel() {
			protected void paintComponent(Graphics g) {
				Image bg;
				try {
					bg = ImageIO.read(new File("src/images/开始背景.PNG"));
					g.drawImage(bg, 0, 0, getWidth(), getHeight(), null);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		label1.setBounds(100,150,100,100);
		txt1.setBounds(180,190, 150, 20);
		label2.setBounds(100,200,100,100);
		txt2.setBounds(180,240, 150, 20);
		btn1.setBounds(100,300,80,20);
		btn2.setBounds(190,300,80,20);
		btn3.setBounds(280,300,80,20);
		frame.setContentPane(bj);
		frame.add(label1);
		frame.add(txt1);
		frame.add(label2);
		frame.add(txt2);
		frame.add(btn1);
		frame.add(btn2);
		frame.add(btn3);
		txt1.addKeyListener(mykey);
		txt2.addKeyListener(mykey);
		btn1.addMouseListener(myAction);
		btn2.addMouseListener(myAction);
		btn3.addMouseListener(myAction);
		frame.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				int n = JOptionPane.showConfirmDialog(null, "是否退出?", "游戏结束",JOptionPane.YES_NO_OPTION);
				System.out.println(JOptionPane.NO_OPTION);
				if(n==JOptionPane.YES_OPTION){
				System.exit(1);
				}
				else{
					frame.setVisible(true);
				}
			}
		});
		frame.setLayout(null);
		frame.setVisible(true);
	}
	/*class MyActionListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			if(arg0.getSource()==btn1){
				//btn1.setForeground(Color.BLUE);
				//btn1.setBorder(BorderFactory.createLoweredBevelBorder());
				myOpr.selecttype(t1,Double.parseDouble(t2));
				if(myOpr.getflag()==0){
					JOptionPane.showMessageDialog(null, "此id不存在，请注册","提示",2);
					txt1.setText("");
					txt2.setText("");
				}
				else if(myOpr.getflag()==1){
					myOpr.selectamount(t1,Double.parseDouble(t2));
					if(myOpr.getflag1()==1){
					JOptionPane.showMessageDialog(null, "登陆成功","提示",2);
					txt1.setText("");
					txt2.setText("");
					frame.setVisible(false);
					wind2.beginwindow();
					wind2.frame1.setVisible(true);
					}
					if(myOpr.getflag1()==0){
						JOptionPane.showMessageDialog(null, "密码错误","提示",2);
					}
				}
				
			}
			if(arg0.getSource()==btn2){
				//btn2.setForeground(Color.BLUE);
				//btn2.setBorder(BorderFactory.createLoweredBevelBorder());
				String logi=(String) JOptionPane.showInputDialog(null,"请输入你的id：\n","注册",JOptionPane.PLAIN_MESSAGE,null,null,"在这输入"); 
				String pas=(String) JOptionPane.showInputDialog(null,"请输入你的密码：\n","注册",JOptionPane.PLAIN_MESSAGE,null,null,"在这输入");
				myOpr.insertData(logi,Double.parseDouble(pas));
				JOptionPane.showMessageDialog(null, "注册成功","提示",2);
				
			}
			if(arg0.getSource()==btn3){
				//btn3.setForeground(Color.BLUE);
				//btn3.setBorder(BorderFactory.createLoweredBevelBorder());
				int n = JOptionPane.showConfirmDialog(null, "是否退出?", "游戏结束",JOptionPane.YES_NO_OPTION);
				System.out.println(JOptionPane.NO_OPTION);
				if(n==JOptionPane.YES_OPTION){
				System.exit(1);
				}
			}
		}
		
	}*/
	class MyMouseListener implements MouseListener {
		public void mouseClicked(MouseEvent arg0) {
		}

		public void mouseEntered(MouseEvent arg0) {
			audio.play();
			if (arg0.getSource() == btn1) {
				btn1.setForeground(Color.red);
				btn1.setBorder(BorderFactory.createLoweredBevelBorder());
				btn2.setForeground(Color.gray);
				btn2.setBorder(BorderFactory.createRaisedBevelBorder());
				btn3.setForeground(Color.gray);
				btn3.setBorder(BorderFactory.createRaisedBevelBorder());
			}
			if (arg0.getSource() == btn2) {
				btn1.setForeground(Color.gray);
				btn1.setBorder(BorderFactory.createRaisedBevelBorder());
				btn2.setForeground(Color.red);
				btn2.setBorder(BorderFactory.createLoweredBevelBorder());
				btn3.setForeground(Color.gray);
				btn3.setBorder(BorderFactory.createRaisedBevelBorder());
			}
			if (arg0.getSource() == btn3) {
				btn1.setForeground(Color.gray);
				btn1.setBorder(BorderFactory.createRaisedBevelBorder());
				btn2.setForeground(Color.gray);
				btn2.setBorder(BorderFactory.createRaisedBevelBorder());
				btn3.setForeground(Color.red);
				btn3.setBorder(BorderFactory.createLoweredBevelBorder());
				}
			}
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		public void mouseExited(MouseEvent arg0) {
			btn1.setForeground(Color.gray);
			btn1.setBorder(BorderFactory.createRaisedBevelBorder());
			btn2.setForeground(Color.gray);
			btn2.setBorder(BorderFactory.createRaisedBevelBorder());
			btn3.setForeground(Color.gray);
			btn3.setBorder(BorderFactory.createRaisedBevelBorder());
		}

		public void mousePressed(MouseEvent arg0) {
			if (arg0.getSource() == btn1) {
				myOpr.selecttype(t1,Double.parseDouble(t2));
				if(myOpr.getflag()==0){
					JOptionPane.showMessageDialog(null, "此id不存在，请注册","提示",2);
					txt1.setText("");
					txt2.setText("");
				}
				else if(myOpr.getflag()==1){
					myOpr.selectamount(t1,Double.parseDouble(t2));
					if(myOpr.getflag1()==1){
					JOptionPane.showMessageDialog(null, "登陆成功","提示",2);
					txt1.setText("");
					txt2.setText("");
					frame.setVisible(false);
					wind2.beginwindow();
					wind2.frame1.setVisible(true);
					}
					if(myOpr.getflag1()==0){
						JOptionPane.showMessageDialog(null, "密码错误","提示",2);
					}
				}
			}
			if (arg0.getSource() == btn2) {
				String logi=(String) JOptionPane.showInputDialog(null,"请输入你的id：\n","注册",JOptionPane.PLAIN_MESSAGE,null,null,"在这输入"); 
				String pas=(String) JOptionPane.showInputDialog(null,"请输入你的密码：\n","注册",JOptionPane.PLAIN_MESSAGE,null,null,"在这输入");
				myOpr.insertData(logi,Double.parseDouble(pas));
				JOptionPane.showMessageDialog(null, "注册成功","提示",2);
			}
			if (arg0.getSource() == btn3) {
				int n = JOptionPane.showConfirmDialog(null, "是否退出?", "游戏结束",JOptionPane.YES_NO_OPTION);
				System.out.println(JOptionPane.NO_OPTION);
				if(n==JOptionPane.YES_OPTION){
				System.exit(1);
			}
		}
	}
	}
	class MyKeyListener implements KeyListener{
		public void keyPressed(KeyEvent arg0) {
		}
		public void keyReleased(KeyEvent arg0) {
		}
		public void keyTyped(KeyEvent arg0) {
			t1=txt1.getText();
			t2=txt2.getText();
			System.out.println(t1);
			System.out.println(t2);
		}
	}
	
}
