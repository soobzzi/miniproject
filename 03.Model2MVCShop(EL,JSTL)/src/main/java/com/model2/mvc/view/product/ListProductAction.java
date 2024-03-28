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
		
		String pageUnitt = getServletContext().getInitParameter("pageUnit");
		int pageUnit = Integer.parseInt(pageUnitt);
		
		String pageSizee = getServletContext().getInitParameter("pageSize");
		int pageSize = Integer.parseInt(pageSizee);
		
		
		searchVO.setPageSize(Integer.parseInt(pageSizee));
		
		ProductService service = new ProductServiceImpl();
		
		HashMap<String,Object> map = service.getProductList(searchVO);
		
		//정보를 다 받은후 만들 그래야 리저트페이지 받을수있음
		Page resultPage = new Page(page,((Integer)(map.get("count"))).intValue(),pageUnit, pageSize);
		
		request.setAttribute("listType", "Product");
		request.setAttribute("map", map);
		request.setAttribute("searchVO", searchVO);
		request.setAttribute("resultPage", resultPage);
		
		
		
		//String menu = request.getParameter("menu");
		
		return "forward:/product/listProduct.jsp";
		
	}
	
}


