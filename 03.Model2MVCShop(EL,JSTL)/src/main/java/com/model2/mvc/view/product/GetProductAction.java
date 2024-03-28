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
//		 ��Ű���� -> ��Ű�̸��� "history"�� ������ �������� ������ prodno (int�� string���� �ٲ���ߵ�)
//		 add.cookie(string,string)
//		 if(��Ű������) ���ø����ֱ� 
		
		Cookie[] cookies = request.getCookies();
		//������Ʈ�� �����ϴ� ��� ��Ű ������
		String history = null;
		
		boolean coo = true;
		
		int index = 0;
		
		for (int i = 0; i < cookies.length; i++) {
				Cookie cookie = cookies[i];
				if (cookie.getName().equals("history")) {
					//�����丮�� �̸��� ������ ã��
					coo = false;
					index = i;
					break;				
				}				
			}
		
		if (coo) {		
			Integer itg = new Integer(prodNo);
			Cookie cookie = new Cookie("history",itg.toString());
			//��Ű�� ������ �������
			response.addCookie(cookie);	
			
		}else {	
			history = cookies[index].getValue();
					//������Ʈ�� �������� �����丮������
					history += ":"+ product.getProdNo();
					cookies[index].setValue(history);
					//������Ʈ�� �����丮�� ��Ƽ� ������
					response.addCookie(cookies[index]);
					//�������� ���������� �����ݽ��� �Ǿ��	
		}
					
					
		// Cookie cookie = null; �� ���������� ������ ������ ���� if�� ���ָ� ��
		
		
		
			return "forward:/product/getProduct.jsp";
		
		
				
	}
	
}
