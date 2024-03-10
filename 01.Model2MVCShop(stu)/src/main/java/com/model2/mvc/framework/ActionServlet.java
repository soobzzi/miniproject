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
		// HttpServlet �� init() �޼ҵ� ȣ��
		//init param overideing
		
		String resources = getServletConfig().getInitParameter("resources");
		// Config : ���� ������ ���������� ������
		// resources��� �̸��� �ʱ�ȭ�Ű������� ������ web.xml�� ���ǵǾ�����
		
		mapper = RequestMapping.getInstance(resources);
		// getInstance�޼ҵ�� �̱������ϻ���Ͽ�  RequestMapping Ŭ������ ������ �ν��Ͻ���ȯ
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
			//action�̶�� bean���� �������ؼ� ������ getServletcontext(app)�� was�� �ù޾ƿ�
			
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