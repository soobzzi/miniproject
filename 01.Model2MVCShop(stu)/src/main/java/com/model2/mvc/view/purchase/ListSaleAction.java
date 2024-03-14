package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;

public class ListSaleAction extends Action{
//���� �ȸ��� ��۴����� 
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ProductVO product = new ProductVO();
		
		product.setProdNo(Integer.parseInt(request.getParameter("prodNo")));
		
		PurchaseService service = new PurchaseServiceImpl();
		service.updateTranCodeByProd(product);
		
		
		
		return "forward:/listProduct.do?menu=manage";
		//�ǸŰ����� �Ѱ��� 
	}

}
