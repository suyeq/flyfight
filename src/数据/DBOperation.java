package 数据;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class DBOperation{
	private MyDBConnection myDB=null;
	private int flag;
	private int flag1;
	private Connection conn=null;
	private Statement stmt=null;
	public DBOperation(MyDBConnection myDB){
		conn=myDB.getMyConnection();
		stmt=myDB.getMyStatement();
	}
	public void insertData(String type,double amount){
		try{
			String newType=new String(type.getBytes(),"GBK");
			String sql="INSERT INTO txt(type,amount)VALUES('"+newType+"',"+amount+")";
			stmt.executeUpdate(sql);
		}catch(Exception e1){
			e1.printStackTrace();
		}
	}
	public void deleteData(String delType){
		String sql="DELETE FROM txt WHERE type='"+delType+"'";
		System.out.print(sql);
		try{
			stmt.executeUpdate(sql);
			System.out.println("一条记录被删除");
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	public void updateData(String srcType,String detType,double amount){//修改
		String sql="UPDATE txt SET type='"+detType+"',amount="+amount+"where type='"+srcType+"'";
		System.out.println(sql);
		try{
			stmt.executeUpdate(sql);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	public void selecttype(String k,double j){//查询所有数据
		String sql="SELECT type FROM txt";
		try{
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next()){
				String type=rs.getString("type");
				if(type.equals(k)){//登陆
					flag=1;
					break;
				}
				if(!type.equals(k)){//不存在
					flag=0;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void selectamount(String k,double j){//查询所有数据
		String sql="SELECT type,amount FROM txt";
		try{
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next()){
				String type=rs.getString("type");
				double amount=rs.getShort("amount");
				if(type.equals(k)&&amount==j){//登陆
					flag1=1;
					break;
				}
				else{
					flag1=0;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void selectWhere(double amount){//查询字符串
		String sql="SELECT type,amount FROM txt where amount>="+amount;
		System.out.println("大于"+amount+"的排放类型有:");
		try{
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next()){
				String type=rs.getString("type");
				double thisAmount=rs.getShort("amount");
				System.out.println("排放类型:"+type+",排放数量:"+thisAmount);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public int getflag(){
		return flag;
	}
	public int getflag1(){
		return flag1;
	}
}