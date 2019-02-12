package site.yueyiqiu.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import site.yueyiqiu.tool.DB;

public class TempDao {
	
	public boolean isexist(String shopcartid) throws SQLException{
		
		boolean flag=false;
		String sql="select * from tb_shopcart where shopcart_id=?";
		Object[] params={shopcartid};
		DB mydb=new DB();
		
		mydb.doPstm(sql, params);
		ResultSet rs=mydb.getRs();
		
		if(rs!=null&&rs.next()){
			
			flag=true;
			rs.close();
		}
		
		mydb.close();
		return flag;
	}
	
	
	public void saveShopcartCreatetime(String shopcartid,String date){
		
		
		String sql="insert into tb_temp values(?,?)";
		Object[] params={shopcartid,date};
		this.getUpdate(sql, params);
	}
	
	
	private void getUpdate(String sql,Object[] params){
		
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.close();
	}
	
	
	public void deleteShopcart(String id){
		String sql="delete from tb_temp where shopcart_id=?";
		Object[] params={id};
		this.getUpdate(sql, params);
	}
	
}
