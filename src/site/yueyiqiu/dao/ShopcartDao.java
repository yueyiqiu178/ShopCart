package site.yueyiqiu.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import site.yueyiqiu.bean.GoodBean;
import site.yueyiqiu.bean.ShopcartBean;
import site.yueyiqiu.tool.DB;

public class ShopcartDao {
	
	DB mydb=null;
	
	public ShopcartDao(){
		
		mydb=new DB();
	}
	
	public ShopcartBean getShopcart(String shopcartid){
		
		ShopcartBean cartbean=null;
		
		String sql="select * from tb_shopcart where shopcart_id=? and shopcart_buygoodcount<>'0' order by id desc";
		Object[] params={shopcartid};
		
		mydb.doPstm(sql, params);
		try{
		ResultSet rs=mydb.getRs();
		
		if(rs!=null){
			
			cartbean=new ShopcartBean();
			GoodDao gooddao=new GoodDao();
			cartbean.setShopcartId(shopcartid);
			
			while(rs.next())	
				cartbean.setShopcartbuyGoods(this.getBuyGoodToShopcart(gooddao, rs.getInt(3), rs.getInt(4)));
			
			gooddao.close();
			
		}
		
		
		}
		catch(SQLException e){e.printStackTrace();}
		
		return cartbean;
	}
	
	
	private GoodBean getBuyGoodToShopcart(GoodDao dao,int buygoodid,int buygoodcount) throws SQLException{
		
		GoodBean bean=dao.getSingleGood(buygoodid);
		if(bean!=null)
			bean.setGoodBuy(buygoodcount);
		return bean;
	}
	
	public boolean isBuy(Object[] params) throws SQLException{
		
		boolean flag=false;
		String sql="select * from tb_shopcart where shopcart_id=? and shopcart_buygoodid=?";
		mydb.doPstm(sql, params);
		ResultSet rs=mydb.getRs();
		
		if(rs!=null&&rs.next()){
			flag=true;
			rs.close();
		}
		return flag;
	}
	
	
	public int addBuyNum(Object[] params){
		
		String sql="update tb_shopcart set shopcart_buygoodcount=shopcart_buygoodcount+1 where shopcart_id=? and shopcart_buygoodid=?";
		return this.getUpdate(sql, params);
	}
	
	
	public int addBuyGood(Object[] params){
		
		String sql="insert into tb_shopcart (shopcart_id,shopcart_buygoodid,shopcart_buygoodcount) values(?,?,?)";
		return this.getUpdate(sql, params);
	}
	
	public int clearShopcart(String id){
		
		String sql="delete from tb_shopcart where shopcart_id=?";
		Object[] params={id};
		return this.getUpdate(sql, params);
		
	}
	
	public int deleteGood(String cartid,int goodid){
		
		String sql="delete from tb_shopcart where shopcart_buygoodid=? and shopcart_id=?";
		Object[] params={goodid,cartid};
		return this.getUpdate(sql, params);
	}
	
	public int updateBuynum(Object[] params){
		
		String sql="update tb_shopcart set shopcart_buygoodcount=? where shopcart_buygoodid=? and shopcart_id=?";
		
		return this.getUpdate(sql, params);
	}
	
	
	private int getUpdate(String sql,Object[] params){
		
		mydb.doPstm(sql, params);
		int i=-1;
		
		try {
			i=mydb.getCount();
		} catch (SQLException e) {
			i=-1;
			e.printStackTrace();
		}		
		
		return i;
	}
	
	public void close(){
		mydb.close();
	}
	
}
