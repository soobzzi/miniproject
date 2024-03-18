package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.impl.UserServiceImpl;
import java.sql.Date;

public class UpdatePurchaseAction extends Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Purchase purchase = new Purchase();
		
		UserService service = new UserServiceImpl();
		User user = service.getUser("userId");
		
		
		String tranNo = request.getParameter("tranNo");
		
		purchase.setTranNo(Integer.parseInt(request.getParameter("tranNo")));
		purchase.setBuyer(user);
		purchase.setPaymentOption(request.getParameter("paymentOption"));
		purchase.setReceiverName(request.getParameter("receiverName"));
		purchase.setReceiverPhone(request.getParameter("receiverPhone"));
		purchase.setDivyAddr(request.getParameter("divyaddr"));
		purchase.setDivyRequest(request.getParameter("divyrRequest"));
		purchase.setDivyDate(request.getParameter("divyDate"));
		purchase.setOrderDate(Date.valueOf(request.getParameter("orderDate")));
		//Date.valueOf
		
		
		PurchaseService servicee = new PurchaseServiceImpl();
		servicee.updatePurchase(purchase);
		
		
		
		
		return "forward:/getPurchase.do?tranNo="+tranNo;
	}
	
	
	
	

}
