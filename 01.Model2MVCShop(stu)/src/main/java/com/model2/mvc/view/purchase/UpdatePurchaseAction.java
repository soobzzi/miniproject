package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.purchase.vo.PurchaseVO;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.impl.UserServiceImpl;
import com.model2.mvc.service.user.vo.UserVO;
import java.sql.Date;

public class UpdatePurchaseAction extends Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		PurchaseVO pur = new PurchaseVO();
		
		UserService service = new UserServiceImpl();
		UserVO user = service.getUser("userId");
		
		
		String tranNo = request.getParameter("tranNo");
		
		pur.setTranNo(Integer.parseInt(request.getParameter("tranNo")));
		pur.setBuyer(user);
		pur.setPaymentOption(request.getParameter("paymentOption"));
		pur.setReceiverName(request.getParameter("receiverName"));
		pur.setReceiverPhone(request.getParameter("receiverPhone"));
		pur.setDivyAddr(request.getParameter("divyaddr"));
		pur.setDivyRequest(request.getParameter("divyrRequest"));
		pur.setDivyDate(request.getParameter("divyDate"));
		pur.setOrderDate(Date.valueOf(request.getParameter("orderDate")));
		//Date.valueOf
		
		
		PurchaseService servicee = new PurchaseServiceImpl();
		servicee.updatePurchase(pur);
		
		
		
		
		return "forward:/getPurchase.do?tranNo="+tranNo;
	}
	
	
	
	

}
