package com.model2.mvc.view.purchase;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.purchase.vo.PurchaseVO;
import com.model2.mvc.service.user.vo.UserVO;

public class AddPurchaseViewAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		PurchaseVO purchase = new PurchaseVO();
		ProductService service = new ProductServiceImpl();
		int prodNo = Integer.parseInt(request.getParameter("prodNo"));
		ProductVO product = service.getProduct(prodNo);
		// product�� �ٰ����ͼ� ������ ���� product������ �� �ҷ���
		
		HttpSession session = request.getSession();
		UserVO user = (UserVO)session.getAttribute("user");
		
		System.out.println("======================");
		System.out.println("purchaseVO : "+purchase);
		
		purchase.setPurchaseProd(product);
		//purchase�� product �־����
		purchase.setBuyer(user);
		
		System.out.println("�����Դ����δ�Ʈ?"+product);	
		System.out.println(purchase);
		
		request.setAttribute("purchase", purchase);
		
		
		return "forward:/purchase/addPurchaseView.jsp";
	}
	
}
	
	
	
