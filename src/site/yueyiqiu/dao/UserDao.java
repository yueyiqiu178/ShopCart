package site.yueyiqiu.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import site.yueyiqiu.bean.UserBean;
import site.yueyiqiu.tool.DB;

public class UserDao {
	
	public UserBean login(String name,String pwd){
		
		UserBean loginer=null;
		String sql="select * from tb_user where username=? and userpassword=?";
		Object[] params={name,pwd};
		DB db=new DB();
		db.doPstm(sql, params);
		
		try {
			ResultSet rs=db.getRs();
			if(rs!=null&&rs.next()){
				loginer=new UserBean();
				loginer.setId(rs.getInt(1));
				loginer.setUsername(rs.getString(3));
				loginer.setUserpwd(rs.getString(2));
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		db.close();
		return loginer;
	}
	
}
