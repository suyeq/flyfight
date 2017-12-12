package ����;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MyDBConnection{
	private String DBDriver;
	private String DBURL;
	private String DBUser;
	private String DBPass;
	private Connection conn=null;
	private Statement stmt=null;
	public MyDBConnection(String driver,String dburl,String user,String pass){
		DBDriver=driver;
		DBURL=dburl;
		DBUser=user;
		DBPass=pass;
		try{
			Class.forName(DBDriver);
			System.out.println("���ݿ�����������سɹ�");
		}catch(Exception e){
			e.printStackTrace();
		}
		try{
			conn=DriverManager.getConnection(DBURL,DBUser,DBPass);
			stmt=conn.createStatement();
			System.out.print("�������ݿ�ɹ�");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public Connection getMyConnection(){
		return conn;
	}
	public Statement getMyStatement(){
		return stmt;
	}
	public void closeMyConnection(){
		try{
			stmt.close();
			conn.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	public String toString(){
		return "���ݿ���������"+DBDriver+"�����ӵ�ַ"+DBURL+"���û���"+DBUser+"������"+DBPass;
	}
}