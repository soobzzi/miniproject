package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;

public class UpdateTranCodeByProdAction extends Action{
	//���� �ȸ��� ��۴����� 
		@Override
		public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
			
			Product product = new Product();
			
			product.setProdNo(Integer.parseInt(request.getParameter("prodNo")));
			System.out.println(product.getProdNo());
			
			PurchaseService service = new PurchaseServiceImpl();
			service.updateTranCodeByProd(product);
			
			
			
			return "forward:/listProduct.do?menu=manage";
			//�ǸŰ����� �Ѱ��� 
		}

	}


