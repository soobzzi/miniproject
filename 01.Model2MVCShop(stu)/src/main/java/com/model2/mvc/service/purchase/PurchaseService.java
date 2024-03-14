package com.model2.mvc.service.purchase;

import java.util.HashMap;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.purchase.vo.PurchaseVO;

public interface PurchaseService {
	
	
	public void addPurchase(PurchaseVO purchase) throws Exception;
	
	public PurchaseVO getPurchase(int purchase) throws Exception;
	
	public HashMap<String,Object> getPurchaseList(SearchVO search) throws Exception;

	public HashMap<String,Object> getSaleList(SearchVO search) throws Exception;
	
	public PurchaseVO updatePurchase(PurchaseVO purchase) throws Exception;
	
	public void updateTranCode(PurchaseVO purchase) throws Exception;
	
	public void updateTranCodeByProd(ProductVO product) throws Exception;	
}
