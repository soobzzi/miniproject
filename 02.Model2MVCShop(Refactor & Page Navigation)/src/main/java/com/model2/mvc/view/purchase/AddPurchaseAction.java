package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;

public class AddPurchaseAction extends Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Purchase purchase = new Purchase();
		Product product = new Product();
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		//유저가져오기
		
		product.setProdNo(Integer.parseInt(request.getParameter("prodNo")));
		purchase.setBuyer(user);
		purchase.setPaymentOption(request.getParameter("paymentOption"));
		purchase.setReceiverName(request.getParameter("receiverName"));
		//user.setPhone(user.getPhone());
		purchase.setReceiverPhone(request.getParameter("receiverPhone").replaceAll("-", ""));
		purchase.setDivyAddr(request.getParameter("receiverAddr"));
		purchase.setDivyRequest(request.getParameter("receiverRequest"));
		purchase.setDivyDate(request.getParameter("receiverDate").replaceAll("-", ""));
		purchase.setTranCode("0");
		
		System.out.println("AddPurchaseAction 실행됨");
		
		purchase.setPurchaseProd(product);
		
		
		
		//Impl 불러오기
		PurchaseService service = new PurchaseServiceImpl();
		//add
		service.addPurchase(purchase);
		
		request.setAttribute("purchase", purchase);
		
		return "forward:/purchase/addPurchase.jsp";
		
		
	}
	//jsp에서 파라미터로 받은 값 셋팅
	//productVO에 물건번호값 넣어줌 
	//

}
