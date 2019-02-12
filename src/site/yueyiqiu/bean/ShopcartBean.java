package site.yueyiqiu.bean;

import java.util.*;

public class ShopcartBean {
	
	private int id;
	private String shopcartId;
	private List shopcartbuyGoods;
	
	public ShopcartBean(){
		this.shopcartbuyGoods=new ArrayList();
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the shopcartId
	 */
	public String getShopcartId() {
		return shopcartId;
	}

	/**
	 * @param shopcartId the shopcartId to set
	 */
	public void setShopcartId(String shopcartId) {
		this.shopcartId = shopcartId;
	}

	/**
	 * @return the shopcartbuyGoods
	 */
	public List getShopcartbuyGoods() {
		return shopcartbuyGoods;
	}

	/**
	 * @param shopcartbuyGoods the shopcartbuyGoods to set
	 */
	public void setShopcartbuyGoods(GoodBean good) {
		this.shopcartbuyGoods.add(good);
	}
	
}
