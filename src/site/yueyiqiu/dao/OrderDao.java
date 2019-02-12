package site.yueyiqiu.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import site.yueyiqiu.bean.GoodBean;
import site.yueyiqiu.tool.DB;
import site.yueyiqiu.tool.StringHandler;

public class OrderDao {
	
	DB mydb=null;
	
	public OrderDao(){mydb=new DB();}
	
	public int getOrderNumber(int whoid,String time){
		
		String sql="select id from tb_orderform where loginerid=? and time=?";
		int number = 0;
		Object[] params={whoid,time};
		this.mydb.doPstm(sql, params);
		
		try {
			ResultSet rs=mydb.getRs();
			if(rs!=null&&rs.next()){
				number=rs.getInt(1);
				rs.close();
			}
		} catch (SQLException e) {
			number=-1;
			e.printStackTrace();
		}
		
		return number;
	}
	
	
	public int addOrder(Object[] params){
		
		int i=-1;
		
		String sql="insert into tb_orderform (loginerid,getter,address,postalcode,linkphone,shipment,shipmenttime,payment,networkpayment,totalprices,time,status,buygoodsids,buygoodsnum) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)"; 
		
		mydb.doPstm(sql, params);
		
		
		try {
			i=mydb.getCount();
		} catch (SQLException e) {
			i=-1;
			e.printStackTrace();
		}
		return i;
	} 
	
	
	public List getBuygoodFromOrder(int orderid){
		
		List list=null;
		
		String sql="select buygoodsids,buygoodsnum from tb_orderform where id=?";
		Object[] params={orderid};
		
		
		
		try {
			
			mydb.doPstm(sql, params);
			
			String ids = null;
			String nums = null;
			
			ResultSet rs=mydb.getRs();
			if(rs!=null&&rs.next()){
			ids=rs.getString(1);
			nums=rs.getString(2);}
			
			
			list=this.getListForOrder(ids,nums);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	
	public List getListForOrder(String ids,String nums){
		
		List list=new ArrayList();
		String[] nums_AR_Str=nums.split(",");
		int[] nums_AR_Int=StringHandler.changeToIntArray(nums_AR_Str);
		
		String[] ids_AR_Str=ids.split(",");
		String id_in="";
		Object[] params=new Object[ids_AR_Str.length];
		
		for(int i=0;i<ids_AR_Str.length;i++){
			params[i]=ids_AR_Str[i];
			id_in=id_in+"?,";
		}
		
		id_in=id_in.substring(0, id_in.length()-1);
		System.out.println(id_in);
		
		String sql="select * from tb_good where id in (ptr)";
		
		sql=sql.replace("ptr", id_in);
		
		System.out.println(sql);
		
		try {
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			int k=0;
			if(rs!=null){
				while(rs.next()){
				GoodBean single=new GoodBean();
				single.setId(rs.getInt(1));
				single.setGoodViewpic(rs.getString(2));
				single.setGoodName(rs.getString(3));
				single.setGoodPrice(rs.getDouble(4));
				single.setGoodStore(rs.getInt(5));
				single.setGoodStocktime(StringHandler.timeTostr(rs.getTimestamp(6)));
				single.setGoodInfo(rs.getString(7));
				single.setGoodMaker(rs.getString(8));
				single.setGoodBuy(nums_AR_Int[k]);
				list.add(single);
				k++;
				}
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
		
	}
	
}
