package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.purchase.vo.PurchaseVO;
import com.model2.mvc.service.user.vo.UserVO;

public class AddPurchaseAction extends Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		PurchaseVO purchase = new PurchaseVO();
		ProductVO product = new ProductVO();
		
		HttpSession session = request.getSession();
		UserVO user = (UserVO)session.getAttribute("user");
		//������������
		
		product.setProdNo(Integer.parseInt(request.getParameter("prodNo")));
		purchase.setBuyer(user);
		purchase.setPaymentOption(request.getParameter("paymentOption"));
		purchase.setReceiverName(request.getParameter("receiverName"));
		//user.setPhone(user.getPhone());
		purchase.setReceiverPhone(request.getParameter("receiverPhone").replaceAll("-", ""));
		purchase.setDivyAddr(request.getParameter("receiverAddr"));
		purchase.setDivyRequest(request.getParameter("receiverRequest"));
		purchase.setDivyDate(request.getParameter("receiverDate").replaceAll("-", ""));
		
		System.out.println("AddPurchaseAction �����");
		
		purchase.setPurchaseProd(product);
		
		
		
		//Impl �ҷ�����
		PurchaseService service = new PurchaseServiceImpl();
		//add
		service.addPurchase(purchase);
		
		request.setAttribute("pur", purchase);
		
		return "forward:/purchase/addPurchase.jsp";
		
		
	}
	//jsp���� �Ķ���ͷ� ���� �� ����
	//productVO�� ���ǹ�ȣ�� �־��� 
	//

}
