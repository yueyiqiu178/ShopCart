package site.yueyiqiu.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import site.yueyiqiu.bean.OrderformBean;
import site.yueyiqiu.bean.ShopcartBean;
import site.yueyiqiu.bean.UserBean;
import site.yueyiqiu.dao.GoodDao;
import site.yueyiqiu.dao.OrderDao;
import site.yueyiqiu.dao.ShopcartDao;
import site.yueyiqiu.dao.TempDao;
import site.yueyiqiu.tool.StringHandler;

/**
 * Servlet implementation class ShopCartServlet
 */
@WebServlet("/ShopCartServlet")
public class ShopCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShopCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String path=request.getServletPath();
		
		if(path.equals("/showshopcart"))
			this.showshopcart(request, response);
		else if(path.equals("/buy"))
			this.buy(request, response);
		else if(path.equals("/clearshopcart"))
			this.clearshopcart(request, response);
		else if(path.equals("/submitshopcart"))
			this.submitDispatcher(request, response);
		else if(path.equals("/remove"))
			this.remove(request, response);
		else if(path.equals("/createorderform"))
			this.createorderform(request, response);
	}
	
	
	protected void showshopcart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String shopcartid=this.seeshopcartcookie(request, response);
		
		if(shopcartid!=null&&!shopcartid.equals("")){
			
			ShopcartBean bean=new ShopcartDao().getShopcart(shopcartid);
			request.setAttribute("shopcart", bean);
		}
		
		request.getRequestDispatcher("/showshopcart.jsp").forward(request, response);
		
	}
	
	
	private String seeshopcartcookie(HttpServletRequest request, HttpServletResponse response){
		
		String webname=request.getContextPath();
		webname=webname.substring(1);
		
		Cookie[] cookies=request.getCookies();
		String shopcartid="";
		
		int i;
		
		for(i=0;i<cookies.length;i++){
			
			Cookie ick=cookies[i];
			
			if(ick.getName().equals(webname+".usershopcart")){
				shopcartid=ick.getValue();
				break;
			}
			
		}
		
		
		return shopcartid;
		
		
	}
	
	protected void buy(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String message="";
		String buygoodidstr=request.getParameter("buygoodid");
		int id=Integer.parseInt(buygoodidstr);
		
		try{
		Date now=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strdate=sdf.format(now);
		
		TempDao tempdao=new TempDao();
		String shopcartid=this.seeshopcartcookie(request, response);	
		System.out.println(shopcartid);
		System.out.println(tempdao.isexist(shopcartid));
		
		if(shopcartid==null||shopcartid.equals("")||!tempdao.isexist(shopcartid)){
			
			shopcartid=this.addshopcartcookie(request, response, now);
			tempdao.saveShopcartCreatetime(shopcartid, strdate);
		}
		
		int i=-1;
		
		ShopcartDao dao=new ShopcartDao();
		Object[] params={shopcartid,id};
		
		if(dao.isBuy(params)){
			i=dao.addBuyNum(params);}
		else{
			params=new Object[]{shopcartid,id,1};
			i=dao.addBuyGood(params);
		}
			
		dao.close();	
		
		if(i<=0){
			
			message="<li>添加商品到購物車失敗!!</li><br/>";
		}
		else{
			
			message="<li>添加商品到購物車成功!!</li><br/>";
		}
		
		
		message+="<a href='javascript:window.history.go(-1)'>返回</a>";
		message+="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
		message+="<a href='showshopcart'>查看購物車</a>";
		}
		catch(SQLException e){
			e.printStackTrace();
			message+="出錯了!!";
		}
		
		System.out.println("go message!");
		request.setAttribute("message", message);
		request.getRequestDispatcher("/message.jsp").forward(request, response);
		
	}
	
	
	private String addshopcartcookie(HttpServletRequest request, HttpServletResponse response,Date date){
		
		String webname=request.getContextPath().substring(1);
		
		SimpleDateFormat sdf=new SimpleDateFormat("MMddyyyyHHmmssSS");		
		String serial=	sdf.format(date);	
		
		String shopcartid="cart"+serial;
		
		Cookie cartcookie=new Cookie(webname+".usershopcart",shopcartid);
		
		cartcookie.setPath("/");
		int maxage=3*24*60*60;
		cartcookie.setMaxAge(maxage);
		response.addCookie(cartcookie);
		
		return shopcartid;
	}
	
	protected void clearshopcart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String shopcartid=this.seeshopcartcookie(request, response);
		
		if(!shopcartid.equals("")){
			
			int i=new ShopcartDao().clearShopcart(shopcartid);
			if(i<=0){
				request.setAttribute("message", "清空購物車失敗!");
			}
			else{
				request.setAttribute("message", "清空購物車成功!");
			}
		}
		
		request.getRequestDispatcher("showshopcart.jsp").forward(request, response);
		
	}
	
	protected void submitDispatcher(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String ptr=request.getParameter("whichsubmit");
		//System.out.println(ptr);
		
		if(ptr.trim().equals("修改數量"))
			this.updateBuynum(request, response);
		else
			this.payforMoney(request, response);
		
	}
	
	protected void payforMoney(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(this.validateBuynum(request, response)){
			String goodids=StringHandler.arrayToString(request.getParameterValues("buygoodid"));
			String goodnums=StringHandler.arrayToString(request.getParameterValues("buygoodnum"));
			
			request.setAttribute("buygoodids", goodids);
			request.setAttribute("buygoodnums", goodnums);
			
			request.getRequestDispatcher("fillorderform.jsp").forward(request, response);
			
		}
		else
			this.showshopcart(request, response);
	}
	
	private boolean validateBuynum(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		boolean mark=true;
		String[] ids=request.getParameterValues("buygoodid");
		String[] nums=request.getParameterValues("buygoodnum");
		String[] stores=request.getParameterValues("buygoodstorenum");
		String shopcartid=this.seeshopcartcookie(request, response);
		
		if(ids!=null&&ids.length>0&&shopcartid!=null){
			Map message=new HashMap();
			Object[] params=new Object[3];
			ShopcartDao dao=new ShopcartDao();
			
			for(int i=0;i<ids.length;i++){
				
				int buynum=Integer.parseInt(nums[i]);
				int storenum=Integer.parseInt(stores[i]);
				
				if(buynum>storenum){
					mark=false;
					message.put(i, "庫存不足");
				}
				else if(buynum<=0)
					dao.deleteGood(shopcartid, Integer.parseInt(ids[i]));
				else{
					params[0]=buynum;
					params[1]=ids[i];
					params[2]=shopcartid;
					dao.updateBuynum(params);
					message.put(i, "修改成功");
				}
			}
			
			request.setAttribute("message", message);
			dao.close();
			
		}
		else
			mark=false;
		
		return mark;
	}
	
	
	protected void updateBuynum(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		this.validateBuynum(request, response);
		this.showshopcart(request, response);
		
	}
	
	protected void remove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		int goodid=StringHandler.strToint(request.getParameter("goodid"));
		String cartid=this.seeshopcartcookie(request, response);
		
		if(!cartid.equals("")&&goodid!=-1){
			int i=new ShopcartDao().deleteGood(cartid, goodid);
			new ShopcartDao().close();
			if(i<=0){
				request.setAttribute("message", "刪除失敗");
				request.getRequestDispatcher("message.jsp").forward(request, response);
			}
			
		}
		
		this.showshopcart(request, response);
	}
	
	protected void createorderform(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String buygoodids=request.getParameter("buygoodids");
		
		if(buygoodids!=null&&!buygoodids.equals("")){
			if(!this.createorderform_validateBuynum(request)){
				this.showshopcart(request, response);
			}
			else{
				
				String forward="";
				String buygoodnum=request.getParameter("buygoodnums");
				int userid=((UserBean) request.getSession().getAttribute("user")).getId();
				String getter=request.getParameter("getter");
				String address=request.getParameter("address");
				System.out.println("address="+address);
				String postalcode=request.getParameter("postalcode");
				String mobile=request.getParameter("mobile");
				String tel=request.getParameter("tel");
				String phone=mobile+","+tel;
				String shipment=request.getParameter("shipment");
				String shipmenttime=request.getParameter("shipmenttime");
				if(shipmenttime.equals(""))
					shipmenttime="-1";
				String payment=request.getParameter("payment");
				String networkpayment=request.getParameter("networkpayment");
				if(networkpayment==null)
					networkpayment="-1";
				double totalPrice=Double.parseDouble(request.getParameter("goodprices"));
				String time=StringHandler.timeTostr(new Date());
				String status="1";
				
				
				double total=0;
				
				if(shipment.equals("1"))
					total=totalPrice+20;
				else if(shipment.equals("2"))
					total=totalPrice+30;
				else if(shipment.equals("3"))
					total=totalPrice+40;
				
				request.setAttribute("goodprice", totalPrice);
				request.setAttribute("totalprice", total);
				
				Object[] params={userid,getter,address,postalcode,phone,shipment,shipmenttime,payment,networkpayment,total,time,status,buygoodids,buygoodnum};
			
				OrderDao dao=new OrderDao();
				int i=dao.addOrder(params);
				
				if(i<=0){
					forward="/message.jsp";
					String message="<li>生成訂單失敗!!</li><br/>";
					message+="<a href='javascript:window.history.go(-1)'>返回</a>";
					request.setAttribute("message", message);
				}
				else{
					forward="/showorderform.jsp";
					int ordernumber=dao.getOrderNumber(userid, time);
					System.out.println("ordernumber="+ordernumber);
					
					List list=dao.getBuygoodFromOrder(ordernumber);
					
					OrderformBean bean=new OrderformBean();
					bean.setOrderNumber(ordernumber);
					bean.setOrderAddress(address);
					bean.setOrderBuygoods(list);
					bean.setOrderGetter(getter);
					bean.setOrderNetworkpayment(networkpayment);
					bean.setOrderPayment(payment);
					bean.setOrderPhone(phone);
					bean.setOrderPostalcode(postalcode);
					bean.setOrderShipment(shipment);
					bean.setOrderShipmenttime(shipmenttime);
					bean.setOrderStatus(status);
					bean.setOrderTime(time);
					bean.setOrderWhoid(userid);
					bean.setTotalPrice(total);
					
					request.setAttribute("orderform", bean);
					
					String[] id_AR=buygoodids.split(",");
					String[] num_AR=buygoodnum.split(",");
					
					GoodDao daoG=new GoodDao();
					
					for(int k=0;k<id_AR.length;k++){
						daoG.updateStoreNum(Integer.parseInt(num_AR[k]), Integer.parseInt(id_AR[k]));
					}
					
					try {
						daoG.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					this.deleteshopcartcookie(request, response);
					
					String shopcartid=this.seeshopcartcookie(request, response);
					
					TempDao daoT=new TempDao();
					daoT.deleteShopcart(shopcartid);
					
				}
				request.getRequestDispatcher(forward).forward(request, response);
			}
			
			
		}
		else{
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		}
		
	}
	
	private boolean createorderform_validateBuynum(HttpServletRequest request){
		boolean mark=true;
		
		String ids=request.getParameter("buygoodids");
		String nums=request.getParameter("buygoodnums");
		
		String[] goodids=ids.split(",");
		String[] goodnums=nums.split(",");
		
		HashMap message=new HashMap();
		GoodDao dao=new GoodDao();
		
		for(int i=0;i<goodids.length;i++){
			
			int buyNum=Integer.parseInt(goodnums[i]);
			int storeNum=dao.getGoodStoreNum(Integer.parseInt(goodids[i]));
			if(buyNum>storeNum){
				mark=false;
				message.put(i, "庫存不足");
			}
		}
		
		request.setAttribute("message", message);
		return mark;
	}
	
	private void deleteshopcartcookie(HttpServletRequest request, HttpServletResponse response){
		
		String webname=request.getContextPath();
		webname=webname.substring(1);
		
		Cookie cookie=new Cookie(webname+".usershopcart",null);
		int maxage=0;
		cookie.setMaxAge(maxage);
		response.addCookie(cookie);
	}

}
