package site.yueyiqiu.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CharacterEncodingFilter implements Filter{
	
	private FilterConfig config=null;
	private String code=null;
	
	@Override
	public void destroy() {
		this.config=null;
		this.code=null;
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		
		request.setCharacterEncoding(this.code);
		response.setContentType("text/html; charset="+this.code);
		
		
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		
		this.config=config;
		this.code=config.getInitParameter("encoding");
	}

}
