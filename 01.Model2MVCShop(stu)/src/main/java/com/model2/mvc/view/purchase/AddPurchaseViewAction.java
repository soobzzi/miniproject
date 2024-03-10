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
		// product를 다가져와서 보내면 위에 product정보를 다 불러옴
		
		HttpSession session = request.getSession();
		UserVO user = (UserVO)session.getAttribute("user");
		
		System.out.println("======================");
		
//		int prodNo = Integer.parseInt(request.getParameter("prodNo"));
//		product.setProdNo(prodNo);
//		product.setProdName(request.getParameter("prodName"));
//		product.setProdDetail(request.getParameter("prodDetail"));
//		product.setManuDate(request.getParameter("manuDate"));
//		int price = Integer.parseInt(request.getParameter("price"));
//		product.setPrice(price);
//		Date regDate = Date.valueOf("regDate");
//		product.setRegDate(regDate);
//		//user.setUserId(user.getUserId());//고정임//purchase로 해야함
//		purchase.setPaymentOption(request.getParameter("paymentOption"));
//		//user.setPhone(user.getPhone());//purchase로 해야함
//		purchase.setReceiverPhone(request.getParameter("receiverPhone").replaceAll("-", ""));
//		//user.setAddr(user.getAddr()); //purchase로 해야함
//		purchase.setDivyAddr(request.getParameter("receiverAddr"));
//		purchase.setDivyRequest(request.getParameter("receiverRequest"));
//		purchase.setDivyDate(request.getParameter("receiverDate"));
		
		//통째로 다가져옴
		
		System.out.println("purchaseVO : "+purchase);
		
		purchase.setPurchaseProd(product);
		//purchase에 product 넣어놓기
		purchase.setBuyer(user);
		
		System.out.println("가져왔니프로덕트?"+product);	
		System.out.println(purchase);
		
		request.setAttribute("purchase", purchase);
		
		
		return "forward:/purchase/addPurchaseView.jsp";
	}
	
}
	
	
	
