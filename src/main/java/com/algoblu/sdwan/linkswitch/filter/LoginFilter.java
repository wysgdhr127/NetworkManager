package com.algoblu.sdwan.linkswitch.filter;

import com.algoblu.sdwan.linkswitch.basic.po.Admin;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static com.algoblu.sdwan.linkswitch.basic.Constants.USER_INFO;

/**
 * 
 * 业务部分登陆拦截器
 * 
 */
public class LoginFilter implements Filter {

	private static Logger logger = Logger.getLogger(LoginFilter.class);
	
	/**
	 * 允许访问的路径
	 */
	@SuppressWarnings("unused")
	private String[] urls;
	
	/**
	 * 不允许访问的路径
	 */
	private String[] notFilterUrls;
	
	
	@Override
	public void init(FilterConfig config) throws ServletException {
		
		String initParameter = config.getInitParameter("filterUrl");
		initParameter = initParameter.replaceAll("\\n", "").replaceAll("\\t", "").replaceAll(" ", "");
		urls = initParameter.split(",");
		
		String notFilterUrlConfig = config.getInitParameter("notFilterUrl");
		notFilterUrlConfig = notFilterUrlConfig.replaceAll("\\n", "").replaceAll("\\t", "").replaceAll(" ", "");
		notFilterUrls = notFilterUrlConfig.split(",");
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException,
			ServletException {
		
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		
		//请求url
		String requestURI = request.getRequestURI();
		
		//根地址
		String contextPath = request.getContextPath();

		boolean isNeedFilter = true;
		for(String notFilterUrl : notFilterUrls){
			
			if(requestURI.contains(contextPath + notFilterUrl)){
				isNeedFilter = false;
				break;
			}
		}

		
		//不需要拦截的放行
		if(!isNeedFilter){
			
			chain.doFilter(servletRequest, servletResponse);
		}else{
			Admin bossUser = (Admin)request.getSession().getAttribute(USER_INFO);
			if (bossUser == null) {
				logger.info("非法请求：["+requestURI+"]");
				response.setCharacterEncoding("UTF-8");
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter writer = response.getWriter();
				if ("XMLHttpRequest".equalsIgnoreCase(request.getHeader("x-requested-with"))) {
					response.setHeader("sessionStatus", "sessionTimeOut");
					writer.write("sessionTimeOut");
				} else {
					writer.write("<script>parent.location.href='" + request.getContextPath() + "/jsp/LoginPage/login.jsp';</script>");
				}
				writer.flush();
				writer.close();
			} else{
				chain.doFilter(servletRequest, servletResponse);
			}
		}
		
	}

	@Override
	public void destroy() {
	}
	

}
