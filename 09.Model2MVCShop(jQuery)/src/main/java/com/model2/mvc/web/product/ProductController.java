package com.model2.mvc.web.product;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;

@Controller
@RequestMapping("/product/*")
public class ProductController {
	
	@Autowired 
	@Qualifier("productServiceImpl")
	private ProductService productService;
	
	public ProductController() {
		System.out.println(this.getClass());
	}
	
	@Value("#{commonProperties['pageUnit']}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize']}")
	int pageSize;
	
	
	
	@RequestMapping(value = "addProduct")
	public String addProduct(@ModelAttribute("product") Product product) throws Exception{
		
		System.out.println("/product/addProduct");
		
		product.setManuDate(product.getManuDate().replaceAll("-", ""));
		
		productService.addProduct(product);
		
		return "forward:/product/addProduct.jsp";
	}
	
	@RequestMapping(value = "getProduct")
	public String getProduct(@RequestParam("prodNo") int prodNo ,@RequestParam("menu") String menu, Model model,HttpServletRequest request,HttpServletResponse response)throws Exception{
		
		System.out.println("/product/getProduct");
		
		Product product = productService.getProduct(prodNo);
		System.out.println(product);
		model.addAttribute("product",product);
		
		if(menu.equals("manage")) {
			return "forward:/product/updateProductView.jsp";
			
		}else if(menu.equals("update")){
			return "forward:/product/updateProduct.jsp";	
		}	
		
		Cookie[] cookies = request.getCookies();
		//리퀘스트에 존재하는 모든 쿠키 가져옴
		String history = null;
		
		boolean coo = true;
		
		int index = 0;
		
		for (int i = 0; i < cookies.length; i++) {
				Cookie cookie = cookies[i];
				if (cookie.getName().equals("history")) {
					//히스토리란 이름을 가진걸 찾음
					coo = false;
					index = i;
					break;				
				}				
			}
		
		if (coo) {		
			Integer itg = new Integer(prodNo);
			Cookie cookie = new Cookie("history",itg.toString());
			//쿠키가 없을때 만들어줌
			response.addCookie(cookie);	
			
		}else {	
			history = cookies[index].getValue();
					//업데이트된 벨류값을 히스토리에저장
					history += ":"+ product.getProdNo();
					cookies[index].setValue(history);
					//업데이트된 히스토리를 담아서 보내줌
					cookies[index].setPath("/");
					response.addCookie(cookies[index]);
					//브라우저에 보내기위해 리스펀스에 실어보냄	
		}
		
		
		return "forward:/product/getProduct.jsp";
	}
	
	@RequestMapping(value = "listProduct")
	public String listProduct(@ModelAttribute("search") Search search, Model model ,HttpServletRequest request) throws Exception{
		
		System.out.println("/product/listProduct :  GET");
		int page = 1;
		if(search.getCurrentPage() != 0) 
			page = search.getCurrentPage();
		
		search.setCurrentPage(page);
		search.setSearchCondition(search.getSearchCondition());
		search.setSearchKeyword(search.getSearchKeyword());
		
		search.setPageSize(pageSize);
		
		Map<String,Object> map = productService.getProductList(search);
		
		
		//정보를 다 받은후 만들 그래야 리저트페이지 받을수있음
		Page resultPage = new Page(page,((Integer)(map.get("totalCount"))).intValue(),pageUnit, pageSize);
		System.out.println(resultPage);
		
		model.addAttribute("resultPage",resultPage);
		model.addAttribute("map",map);
		model.addAttribute("search",search);
		model.addAttribute("listType","Product");
		
		
		
		return "forward:/product/listProduct.jsp?menu="+request.getParameter("menu");
	}
	
	@RequestMapping(value = "updateProduct", method = RequestMethod.POST)
	public String updateProduct(@ModelAttribute("product") Product product, Model model) throws Exception{
		
		System.out.println("/product/updateProduct");
			
		int prodNo = product.getProdNo();
		product.setManuDate(product.getManuDate().replaceAll("-", ""));

		productService.updateProduct(product);
		
		
		
		return "forward:/product/getProduct?menu=update";
	}
	
//	@RequestMapping("/updateProductView.do")
//	public String updateProductView(@RequestParam("prodNo") int prodNo, Model model) throws Exception{
//		
//		System.out.println("/updateProductView.do");
//		
//		Product product = productService.getProduct(prodNo);
//		
//		model.addAttribute("product",product);
//		
//		
//		return "forward:/product/updateproduct.jsp";
//	}
//	

}
