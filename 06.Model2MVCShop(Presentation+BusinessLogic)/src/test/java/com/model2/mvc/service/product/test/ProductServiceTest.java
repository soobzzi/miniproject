package com.model2.mvc.service.product.test;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/context-common.xml",
									"classpath:config/context-aspect.xml",
									"classpath:config/context-mybatis.xml",
									"classpath:config/context-transaction.xml"})
public class ProductServiceTest {
	
	
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	
	//@Test
	public void testAddProduct() throws Exception{
		
		Product product = new Product();
			
		product.setProdName("정한이의맥북");
		product.setProdDetail("중고a급");
		product.setManuDate("20201010");
		product.setPrice(100);
		product.setFileName("macbookfile");
	
		
		productService.addProduct(product);
		
		product = productService.getProduct(10000);
		
		
		System.out.println(product);
		
		Assert.assertEquals("정한이의맥북", product.getProdName());
		Assert.assertEquals("중고a급", product.getProdDetail());
		Assert.assertEquals("20201010", product.getManuDate());
		Assert.assertEquals(100, product.getPrice());
		Assert.assertEquals("macbookfile", product.getFileName());
		
		
		
	}
	
	@Test
	public void testGetProduct() throws Exception{
		
		Product product = new Product();
		
		product = productService.getProduct(10000);
		
		System.out.println("getget"+product);
		
		Assert.assertEquals("정한이의맥북", product.getProdName());
		Assert.assertEquals("중고a급", product.getProdDetail());
		Assert.assertEquals("20201010", product.getManuDate());
		Assert.assertEquals(100, product.getPrice());
		Assert.assertEquals("macbookfile", product.getFileName());
		
		Assert.assertNotNull(productService.getProduct(10000));
	}
	
	//@Test
	public void testUpdateProduct() throws Exception{
		
		Product product = productService.getProduct(10000);
		
		System.out.println(product);
		
		Assert.assertNotNull(product);
		
		Assert.assertEquals("중고a급", product.getProdDetail());
		Assert.assertEquals("20201010", product.getManuDate());
		Assert.assertEquals(100, product.getPrice());
		Assert.assertEquals("macbookfile", product.getFileName());
		
		
		product.setProdDetail("중고b급");
		product.setManuDate("20202222");
		product.setPrice(10000);
		product.setFileName("macbookfile2");
		
		productService.updateProduct(product);
		
		product = productService.getProduct(10000);
		Assert.assertNotNull(product);
		
		Assert.assertEquals("중고b급", product.getProdDetail());
		Assert.assertEquals("20202222", product.getManuDate());
		Assert.assertEquals(10000, product.getPrice());
		Assert.assertEquals("macbookfile2", product.getFileName());
		
		System.out.println(product);
	}
	
	//@Test
	public void testGetProductList() throws Exception{
		
		Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);

	 	Map<String,Object> map = productService.getProductList(search);
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	Assert.assertEquals(3, list.size());
	 	
		//==> console 확인
	 	System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	System.out.println(map);
	 	
	 	System.out.println("=======================================");
	 	
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	search.setSearchCondition("0");
	 	search.setSearchKeyword("10000");
	 	map = productService.getProductList(search);
	 	
	 	list = (List<Object>)map.get("list");
	 	Assert.assertEquals(1, list.size());
	 	
		//==> console 확인
	 	System.out.println(list);
	 	
	 	totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 
	 }
		
		
	}


