package com.model2.mvc.view.product;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;


public class GetProductAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int prodNo = Integer.parseInt(request.getParameter("prodNo"));
		
		ProductService service = new ProductServiceImpl();
		Product product = service.getProduct(prodNo);
		
		request.setAttribute("product", product);
		
		String menu = request.getParameter("menu");
		
		if(request.getParameter("menu").equals("manage")) {
			return "forward:/product/updateProductView.jsp";
			
		}else if(request.getParameter("menu").equals("update")){
			return "forward:/product/updateProduct.jsp";	
		}	
		
//		Cookie cookies = new Cookie("history","prodNo");
//		response.addCookie(cookies);
//		 쿠키만듬 -> 쿠키이름을 "history"로 지어줌 가져오는 벨류는 prodno (int라서 string으로 바꿔줘야됨)
//		 add.cookie(string,string)
//		 if(쿠키있으면) 스플릿해주기 
		
		Cookie[] cookies = request.getCookies();
		//리퀘스트에 존재하는 모든 쿠키 가져옴
		String history = null;
		
		boolean coo = true;
		
		int index = 0;
		
		for (int i = 0; i < cookies.length; i++) {
				Cookie cookie = cookies[i];
				if (cookie.getName().equals("history")) {
					//히스토리란 이름을 가진걸 찾음
					coo = false;
					index = i;
					break;				
				}				
			}
		
		if (coo) {		
			Integer itg = new Integer(prodNo);
			Cookie cookie = new Cookie("history",itg.toString());
			//쿠키가 없을때 만들어줌
			response.addCookie(cookie);	
			
		}else {	
			history = cookies[index].getValue();
					//업데이트된 벨류값을 히스토리에저장
					history += ":"+ product.getProdNo();
					cookies[index].setValue(history);
					//업데이트된 히스토리를 담아서 보내줌
					response.addCookie(cookies[index]);
					//브라우저에 보내기위해 리스펀스에 실어보냄	
		}
					
					
		// Cookie cookie = null; 로 설정해준후 있으면 없으면 으로 if문 해주면 됨
		
		
		
			return "forward:/product/getProduct.jsp";
		
		
				
	}
	
}
