package com.model2.mvc.framework;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.common.util.HttpUtil;


public class ActionServlet extends HttpServlet {
	
	private RequestMapping mapper;

	@Override
	public void init() throws ServletException {
		super.init();
		// HttpServlet 의 init() 메소드 호출
		//init param overideing
		
		String resources = getServletConfig().getInitParameter("resources");
		// Config : 현재 서블릿의 설정정보를 가져옴
		// resources라는 이름의 초기화매개변수값 가져옴 web.xml에 정의되어있음
		
		mapper = RequestMapping.getInstance(resources);
		// getInstance메소드는 싱글톤패턴사용하여  RequestMapping 클래스의 유일한 인스턴스반환
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
																									throws ServletException, IOException {
		
		String url = request.getRequestURI();
		String contextPath = request.getContextPath();
		String path = url.substring(contextPath.length());
		System.out.println(path);
		
		try{
			Action action = mapper.getAction(path);
			action.setServletContext(getServletContext());
			//action이라는 bean에서 쓰기위해서 가져옴 getServletcontext(app)는 was엣 ㅓ받아와
			
			String resultPage = action.execute(request, response);
			String result = resultPage.substring(resultPage.indexOf(":")+1);
			
			if(resultPage.startsWith("forward:"))
				HttpUtil.forward(request, response, result);
			else
				HttpUtil.redirect(response, result);
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}