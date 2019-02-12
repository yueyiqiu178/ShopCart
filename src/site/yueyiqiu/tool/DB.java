package site.yueyiqiu.tool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class DB {
	
	private Connection conn=null;
	private PreparedStatement stmt=null;
	
	
	public Connection connect(){
		
		try{
			
			Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/shopcart";
			String user="root";
			String password="2agilrdl";
			conn=DriverManager.getConnection(url, user, password);
			
			if(conn!=null)
			System.out.println("成功連接資料庫");
			
			
		}
		catch(Exception e){
			
			e.printStackTrace();
		}
		return conn;
	}
	
	
	public void doPstm(String sql,Object[] params){
		
		if(sql!=null&&!sql.equals("")){
			
			if(params==null)
				params=new Object[0];
			
			this.connect();
			
			if(conn!=null){
				
				
				try{
				
				stmt=conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);	
				
				for(int i=0;i<params.length;i++)
					stmt.setObject(i+1, params[i]);
				
				stmt.execute();
				
				}
				catch(Exception e){
					
					System.out.println("doPstm方法出錯!");
					e.printStackTrace();
				}
				
				
			}
			
			
		}
		
	}
	
	
	public ResultSet getRs() throws SQLException{
		return stmt.getResultSet();
	}
	
	public int getCount() throws SQLException{
		return stmt.getUpdateCount();
	}
	
	public void close(){
		
		if(stmt!=null)
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		if(conn!=null)
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
	}
	
}
