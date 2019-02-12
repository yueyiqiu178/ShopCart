package site.yueyiqiu.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import site.yueyiqiu.tool.DB;
import site.yueyiqiu.tool.PageBar;

public class SuperDao {
	
	private PageBar daoPage=new PageBar();
	private int daoPerR=daoPage.getPerR();
	private int daoPerP=daoPage.getPerP();
	public int getDaoPerR() {
		return daoPerR;
	}
	public void setDaoPerR(int daoPerR) {
		this.daoPerR = daoPerR;
	}
	public int getDaoPerP() {
		return daoPerP;
	}
	public void setDaoPerP(int daoPerP) {
		this.daoPerP = daoPerP;
	}
	
	public PageBar getDaoPage(){
		return this.daoPage;
	}
	
	
	public void setDaoPage(String sql,Object[] params,String p,String g,String gowhich) throws SQLException{
		this.daoPage.setAllR(this.getAllR(sql, params));
		//this.daoPage.setPerP(this.daoPerP);
		//this.daoPage.setPerR(this.daoPerR);
		this.daoPage.setPageBar(p, g, gowhich);
		System.out.println("in p="+p);
		System.out.println("in g="+g);
		System.out.println("this p="+this.daoPage.getCurrentP());
		System.out.println("this g="+this.daoPage.getCurrentG());
	}
	
	
	private int getAllR(String sql,Object[] params) throws SQLException{
		int allR=0;
		DB db=new DB();
		db.doPstm(sql, params);
		ResultSet set=db.getRs();
		
		if(set!=null&&set.next()){
			set.last();
			allR=set.getRow();
			set.close();
		}
		
		db.close();
		return allR;
	}
	
	
}
