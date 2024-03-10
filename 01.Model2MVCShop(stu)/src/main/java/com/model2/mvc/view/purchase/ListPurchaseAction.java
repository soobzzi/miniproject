package com.model2.mvc.view.purchase;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;

public class ListPurchaseAction extends Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		SearchVO search = new SearchVO();
		
		int page = 1;
		if(request.getParameter("page") != null)
			
		page = Integer.parseInt(request.getParameter("page"));
		search.setPage(page);
		
		String pageUnit = getServletContext().getInitParameter("pageSize");
		search.setPageUnit(Integer.parseInt(pageUnit));
		
		PurchaseService service = new PurchaseServiceImpl();
		HashMap<String,Object> map = service.getPurchaseList(search);
		
		
		
		request.setAttribute("map", map);
		request.setAttribute("search", search);
		
		return "forward:/purchase/listPurchase.jsp";
	}

}
