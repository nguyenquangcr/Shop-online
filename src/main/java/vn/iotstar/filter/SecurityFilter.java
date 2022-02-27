package vn.iotstar.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vn.iotstar.model.Users;

@WebFilter(urlPatterns = "/admin/*")
public class SecurityFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		Object obj = session.getAttribute("account");
		Users user = (Users) obj;
		
		if(user != null && user.getRoles().getRoleid() == 2) {
			chain.doFilter(request, response);
			return;
		}else {
			resp.sendRedirect(req.getContextPath()+"/logout");
		}
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
}
