package com.model2.mvc.service.purchase.impl;

import java.util.HashMap;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.dao.PurchaseDAO;

public class PurchaseServiceImpl implements PurchaseService{

	private PurchaseDAO purchaseDAO;
	
	public PurchaseServiceImpl() {	
		purchaseDAO = new PurchaseDAO();
	}

	@Override
	public void addPurchase(Purchase purchase) throws Exception {
		purchaseDAO.addPurchase(purchase);
	}

	@Override
	public Purchase getPurchase(int tranNo) throws Exception {
		return purchaseDAO.getPurchase(tranNo);
	}

	@Override
	public HashMap<String, Object> getPurchaseList(Search search) throws Exception {
		return purchaseDAO.getPurchaseList(search);
	}

	@Override
	public HashMap<String, Object> getSaleList(Search search) throws Exception {
		return null;
	}

	@Override
	public Purchase updatePurchase(Purchase purchase) throws Exception {
		return purchaseDAO.updatePurchase(purchase);
	}

	@Override
	public void updateTranCode(Purchase purchase) throws Exception {
		purchaseDAO.updateTranCode(purchase);
	}

	@Override
	public void updateTranCodeByProd(Product product) throws Exception {
		purchaseDAO.updateTranCodeByProd(product);
		
	}

	
}