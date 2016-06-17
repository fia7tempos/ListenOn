package net.musicrecommend.www.interCeptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class LoginCheck extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		System.out.println("★★ 왔어염 ★★");
		try{
			if(request.getParameter("mobile")!=null){
				return true;
			}
			if(request.getSession().getAttribute("user_id") == null){
				response.sendRedirect("/www/user/login");
				return false;
			} 
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return true;
		
		
		
	}
	
	

}
