package site.yueyiqiu.servlet;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import site.yueyiqiu.bean.GoodBean;
import site.yueyiqiu.dao.GoodDao;
import site.yueyiqiu.tool.StringHandler;


/**
 * Servlet implementation class GoodServlet
 */
@WebServlet("/GoodServlet")
public class GoodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoodServlet() {
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
		
		if(path.equals("/listgoods"))
			this.listgoods(request, response);
		else if(path.equals("/listbrowsegoods"))
			this.listbrowsegoods(request, response);
		else if(path.equals("/viewgood"))
			this.viewgood(request, response);
		else if(path.equals("/clearbrowse"))
			this.clearbrowse(request, response);
	}
	
	protected void listgoods(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		try{
			
			GoodDao dao=new GoodDao();
			String p=request.getParameter("currentP");
			String g=request.getParameter("currentG");

			String gowhich="listgoods";
			System.out.println("p="+p+" and g="+g);
			List goodlist=dao.getAllGoods(p, g, gowhich);
			List ggg=(List) goodlist.get(0);
			GoodBean kkkk=(GoodBean) ggg.get(0);
			System.out.println(kkkk.getGoodName());
			request.setAttribute("goodlist", goodlist);
			request.setAttribute("pagebar", dao.getDaoPage());
			dao.close();
			
		}
		catch(Exception e){
			
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("listgoods.jsp").forward(request, response);
		
	}
	
	
	protected void listbrowsegoods(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map ckmap=getCookie(request);
		List browsegoodlist = new ArrayList();
		if(ckmap.size()==4){
			
			browsegoodlist=new ArrayList();
			int[] ids=StringHandler.changeToIntArray(((String)ckmap.get("ckid")).split("I"));
			String[] names=((String) ckmap.get("ckname")).split("I");
			String[] viewpics=((String) ckmap.get("ckviewpic")).split("I");
			String[] times=((String) ckmap.get("cktime")).split("I");
			
			for(int i=0;i<names.length;i++){
				names[i]=StringHandler.cookieDecode(names[i]);
			}
			
			for(int i=0;i<ids.length;i++){
				
				Map browsesingle=new HashMap();
				browsesingle.put("browseid", ids[i]);
				browsesingle.put("browsename", names[i]);
				browsesingle.put("browseviewpic", viewpics[i]);
				browsesingle.put("browsetime", times[i]);
				
				browsegoodlist.add(browsesingle);
			}
			
			request.setAttribute("browsegoodlist", browsegoodlist);
			
		}
		
		for(int i=0;i<browsegoodlist.size();i++){
			Map map=(Map) browsegoodlist.get(i);
			System.out.println(map.get("browseid"));
			System.out.println(map.get("browsename"));
			System.out.println(map.get("browseviewpic"));
			System.out.println(map.get("browsetime"));
		}
		
		request.getRequestDispatcher("/listbrowsegoods.jsp").forward(request, response);
		
	}
	
	protected void viewgood(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		int goodid=Integer.parseInt(request.getParameter("goodid"));
		
		try{
		GoodDao dao=new GoodDao();
		GoodBean bean=dao.getSingleGood(goodid);
		dao.close();
		if(bean!=null){
			
			this.seebrowsegoodcookie(request, response, bean);
			request.setAttribute("goodsingle", bean);
		}
		
		}
		catch(Exception e){e.printStackTrace();}
	
		request.getRequestDispatcher("/viewgood.jsp").forward(request, response);	
	}
	
	protected Map getCookie(HttpServletRequest request){
		
		Map ckmap=new HashMap();
		String webname=request.getContextPath();
		webname=webname.substring(1);
		
		Cookie[] cookies=request.getCookies();
		
		int i;
		
		for(i=0;i<cookies.length;i++){
			
			Cookie ick=cookies[i];
			
			if(ick.getName().equals(webname+".browsegoodid"))
				ckmap.put("ckid", ick.getValue());
			else if(ick.getName().equals(webname+".browsegoodname"))
				ckmap.put("ckname", ick.getValue());
			else if(ick.getName().equals(webname+".browsegoodviewpic"))
				ckmap.put("ckviewpic", ick.getValue());
			else if(ick.getName().equals(webname+".browsegoodtime"))
				ckmap.put("cktime", ick.getValue());
			if(ckmap.size()==4)
				break;
		}
		
		return ckmap;
	}
	
	
	private void seebrowsegoodcookie(HttpServletRequest request, HttpServletResponse response,GoodBean single) throws ServletException, IOException {
		
		String webname=request.getContextPath();
		webname=webname.substring(1);
		
		Date now=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String datestr=sdf.format(now);
		
		int goodid=single.getId();
		String goodname=single.getGoodName();
		String goodviewpic=single.getGoodViewpic();
		
		goodname=StringHandler.cookieEncode(goodname);
		
		
		System.out.println(goodid);
		System.out.println(goodname);
		System.out.println(goodviewpic);
		System.out.println(datestr);
		
		Cookie[] cookies=request.getCookies();
		Cookie browsegoodid=null;
		Cookie browsegoodname=null;
		Cookie browsegoodviewpic=null;
		Cookie browsegoodtime=null;
		System.out.println("cookie length="+cookies.length);
	
		String ckid="";
		String ckname="";
		String ckviewpic="";
		String cktime="";
		
		int i;
		
		for(i=0;i<cookies.length;i++){
			Cookie icookie=cookies[i];
			if(icookie.getName().equals(webname+".browsegoodid"))
				browsegoodid=icookie;
			else if(icookie.getName().equals(webname+".browsegoodname"))
				browsegoodname=icookie;
			else if(icookie.getName().equals(webname+".browsegoodviewpic"))
				browsegoodviewpic=icookie;
			else if(icookie.getName().equals(webname+".browsegoodtime"))
				browsegoodtime=icookie;
			
			if(browsegoodid!=null&&browsegoodname!=null&&browsegoodviewpic!=null&&browsegoodtime!=null)
				break;
				
		}
		
		System.out.println("i="+i);
		System.out.println("cookie length="+cookies.length);
		
		if(i<cookies.length){
			System.out.println("I am here!!");
			ckid=goodid+"I"+browsegoodid.getValue();
			ckname=goodname+"I"+browsegoodname.getValue();
			ckviewpic=goodviewpic+"I"+browsegoodviewpic.getValue();
			cktime=datestr+"I"+browsegoodtime.getValue();
			
		}
		else{
			System.out.println("No!!");
			ckid=goodid+"";
			ckname=goodname;
			ckviewpic=goodviewpic;
			cktime=datestr;	
			
		}
		
		browsegoodid=new Cookie(webname+".browsegoodid",ckid);
		browsegoodname=new Cookie(webname+".browsegoodname",ckname);
		browsegoodviewpic=new Cookie(webname+".browsegoodviewpic",ckviewpic);
		browsegoodtime=new Cookie(webname+".browsegoodtime",cktime);
		
		this.addcookie(browsegoodtime, response);
		this.addcookie(browsegoodviewpic, response);
		this.addcookie(browsegoodname, response);
		this.addcookie(browsegoodid, response);
		
	}
	
	private void addcookie(Cookie cookie,HttpServletResponse response){
		
		cookie.setPath("/");
		int maxage=3*24*60*60;
		cookie.setMaxAge(maxage);
		response.addCookie(cookie);
	}
	
	protected void clearbrowse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String webname=request.getContextPath();
		webname=webname.substring(1);
		
		Cookie id=new Cookie(webname+".browsegoodid",null);
		Cookie name=new Cookie(webname+".browsegoodname",null);
		Cookie viewpic=new Cookie(webname+".browsegoodviewpic",null);
		Cookie time=new Cookie(webname+".browsegoodtime",null);
		
		this.delCookie(response, id);
		this.delCookie(response, name);
		this.delCookie(response, viewpic);
		this.delCookie(response, time);
		
		request.getRequestDispatcher("/listbrowsegoods.jsp").forward(request, response);
		
	}
	
	
	private void delCookie(HttpServletResponse response,Cookie cookie){
		
		cookie.setPath("/");
		cookie.setMaxAge(0);
		response.addCookie(cookie);
	}
	
}
