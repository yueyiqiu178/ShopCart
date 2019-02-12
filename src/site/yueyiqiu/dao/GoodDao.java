package site.yueyiqiu.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import site.yueyiqiu.bean.GoodBean;
import site.yueyiqiu.tool.DB;

public class GoodDao extends SuperDao{
	
	DB mydb=null;
	
	public GoodDao(){mydb=new DB();}
	
	
	public List getAllGoods(String p,String g,String gowhich) throws SQLException{
		
		String sql="select * from tb_good order by good_stocktime desc";
		System.out.println("here p="+p);
		System.out.println("here g="+g);
		//System.out.println("come here");
		this.setDaoPage(sql, null, p, g, gowhich);
		System.out.println("come here");
		int currentP=this.getDaoPage().getCurrentP();
		System.out.println("current p="+currentP);
		int top1=this.getDaoPage().getPerR();
		System.out.println("top1="+top1);
		int top2=(currentP-1)*top1;
		
		String sqlsub="";
		
		if(currentP==1)
			sqlsub="select * from tb_good order by good_stocktime desc limit "+top1;
		else
			sqlsub="select top "+top1+" from tb_good where (good_stocktime<(select min(good_stocktime) from (select top "+top2+"good_stocktime order by good_stocktime desc))) order by good_stocktime desc";
		
		List alllist=this.getList(sqlsub, null);
		List goodlist=this.divide(alllist, top1);
		return goodlist;
	}
	
	
	private List getList(String sql,Object[] params) throws SQLException{
		System.out.println("sql="+sql);
		List list=null;
		mydb.doPstm(sql, params);
		ResultSet set=mydb.getRs();
		
		if(set!=null){
			
			list=new ArrayList();
			while(set.next()){
				
				GoodBean bean=new GoodBean();
				bean.setId(set.getInt(1));
				bean.setGoodViewpic(set.getString(2));
				bean.setGoodName(set.getString(3));
				bean.setGoodPrice(set.getDouble(4));
				bean.setGoodStore(set.getInt(5));
				bean.setGoodStocktime(set.getString(6));
				bean.setGoodInfo(set.getString(7));
				bean.setGoodMaker(set.getString(8));
				list.add(bean);
			}
			set.close();
		}
		
		return list;
	}
	
	
	private List divide(List list,int perR){
		
		List goodlist=null;
		
		if(list!=null){
			
			goodlist=new ArrayList();
			int blank=perR-list.size();
			if(blank>0){
				for(int i=1;i<=blank;i++)
				list.add(null);
			}
			
			for(int i=0;i<3;i++){
				
				List temp=new ArrayList();
				for(int j=0;j<4;j++){
					temp.add(list.get(4*i+j));
				}
				
				goodlist.add(temp);
			}
			
		}
		
		return goodlist;
		
	}
	
	public GoodBean getSingleGood(int goodid) throws SQLException{
		
	
		GoodBean bean=null;
		List list=null;
		String sql="select * from tb_good where id=?";
		Object[] params={goodid};
		
		list=this.getList(sql, params);
		
		if(list!=null&&list.size()>0)
		bean=(GoodBean) list.get(0);
		
		return bean;
	}
	
	
	public int getGoodStoreNum(int id){
		int num=0;
		String sql="select good_store from tb_good where id=?";
		Object[] params={id};
		
		mydb.doPstm(sql, params);
		
		try {
			ResultSet set=mydb.getRs();
			if(set!=null&&set.next()){
				num=set.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return num;
	}
	
	
	public void updateStoreNum(int buynum,int id){
		String sql="update tb_good set good_store=good_store-? where id=?";
		Object[] params={buynum,id};
		mydb.doPstm(sql, params);
	}
	
	
	
	public void close() throws SQLException{mydb.close();}
	
}
