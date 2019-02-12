package site.yueyiqiu.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import site.yueyiqiu.bean.UserBean;
import site.yueyiqiu.dao.UserDao;

/**
 * Servlet implementation class LoginXServlet
 */
@WebServlet("/LoginXServlet")
public class LoginXServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginXServlet() {
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
		
		String name=request.getParameter("username");
		String pwd=request.getParameter("userpwd");
		System.out.println(name);
		System.out.println(pwd);
		String forward="";
		String message="";
		
		UserBean user=new UserDao().login(name.trim(), pwd.trim());
		
		if(user==null){
			
			forward="message.jsp";
			message="<li>用戶名或密碼錯誤</li>";
			message+="<a href='javascript:window.history.go(-1)'>返回重試</a>";
			
		}
		else{
			
			forward="/showshopcart";
			request.getSession().setAttribute("user", user);
			
		}
		
		request.setAttribute("systemmessage", message);
		request.getRequestDispatcher(forward).forward(request, response);
		
		
	}

}
