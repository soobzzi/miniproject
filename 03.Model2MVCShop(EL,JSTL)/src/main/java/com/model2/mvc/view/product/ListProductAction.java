package com.model2.mvc.view.product;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;



public class ListProductAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Search searchVO = new Search();
		//Page resultPage = new Page();
		
		int page = 1;
		if(request.getParameter("currentPage") != null)
			page = Integer.parseInt(request.getParameter("currentPage"));
			
		searchVO.setCurrentPage(page);
		searchVO.setSearchCondition(request.getParameter("searchCondition"));
		searchVO.setSearchKeyword(request.getParameter("searchKeyword"));
		
		int pageUnit = Integer.parseInt(getServletContext().getInitParameter("pageUnit"));
		int pageSize = Integer.parseInt(getServletContext().getInitParameter("pageSize"));
		searchVO.setPageSize(pageSize);
		
		ProductService service = new ProductServiceImpl();
		HashMap<String,Object> map = service.getProductList(searchVO);
		
		
		//정보를 다 받은후 만들 그래야 리저트페이지 받을수있음
		Page resultPage = new Page(page,((Integer)(map.get("count"))).intValue(),pageUnit, pageSize);
		
		
		
		request.setAttribute("map", map);
		request.setAttribute("search", searchVO);
		request.setAttribute("resultPage", resultPage);
		request.setAttribute("listType", "Product");
		
		
		//String menu = request.getParameter("menu");
		
		return "forward:/product/listProduct.jsp";
		
	}
	
}


