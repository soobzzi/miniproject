package com.model2.mvc.view.product;

import java.io.IOException;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;


public class UpdateProductAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		Product productVO = new Product();
		
		int prodNo = Integer.parseInt(request.getParameter("prodNo"));
		productVO.setProdNo(prodNo);
		productVO.setProdName(request.getParameter("prodName"));
		productVO.setManuDate(request.getParameter("manuDate"));
		int price = Integer.parseInt(request.getParameter("price"));
		productVO.setPrice(price);
		productVO.setFileName(request.getParameter("fileName"));
		productVO.setProdDetail(request.getParameter("prodDetail"));
		// String을 date로
		//Date regDate = LocalDate.parse(request.getParameter("regDate"),DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		
		
		ProductService service = new ProductServiceImpl();
		service.updateProduct(productVO);
		
		
		
		return "forward:/getProduct.do?prodNo="+prodNo+"&menu=update";
	}
	
	//return forward 보내주고 , getProduct.do로 
	
	}

