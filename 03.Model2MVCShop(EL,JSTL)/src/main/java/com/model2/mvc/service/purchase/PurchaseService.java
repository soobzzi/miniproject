package com.model2.mvc.service.purchase;

import java.util.HashMap;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;



public interface PurchaseService {
	
	
	public void addPurchase(Purchase purchase) throws Exception;
	
	public Purchase getPurchase(int purchase) throws Exception;
	
	public HashMap<String,Object> getPurchaseList(Search search) throws Exception;

	public HashMap<String,Object> getSaleList(Search search) throws Exception;
	
	public Purchase updatePurchase(Purchase purchase) throws Exception;
	
	public void updateTranCode(Purchase purchase) throws Exception;
	
	public void updateTranCodeByProd(Product product) throws Exception;	
}
