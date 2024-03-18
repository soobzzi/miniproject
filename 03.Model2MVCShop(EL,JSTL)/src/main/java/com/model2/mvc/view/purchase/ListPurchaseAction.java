package com.model2.mvc.view.purchase;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;

public class ListPurchaseAction extends Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Search search = new Search();
		
		int page = 1;
		if(request.getParameter("page") != null)
		page = Integer.parseInt(request.getParameter("page"));
		search.setCurrentPage(page);
		
		int pageSize = Integer.parseInt(getServletContext().getInitParameter("pageSize"));
		int pageUnit = Integer.parseInt(getServletContext().getInitParameter("pageUnit"));
		
		search.setPageSize(pageSize);
		
		
		PurchaseService service = new PurchaseServiceImpl();
		HashMap<String,Object> map = service.getPurchaseList(search);
		
		
		Page resultPage = new Page(page,(int)map.get("count"),pageUnit,pageSize);
		
		
		request.setAttribute("map", map);
		request.setAttribute("search", search);
		request.setAttribute("resultPage", resultPage);
		
		return "forward:/purchase/listPurchase.jsp";
	}

}
