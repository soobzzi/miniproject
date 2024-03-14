package com.model2.mvc.service.purchase.impl;

import java.util.HashMap;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.dao.PurchaseDAO;
import com.model2.mvc.service.purchase.vo.PurchaseVO;

public class PurchaseServiceImpl implements PurchaseService{

	private PurchaseDAO purchaseDAO;
	
	public PurchaseServiceImpl() {	
		purchaseDAO = new PurchaseDAO();
	}

	@Override
	public void addPurchase(PurchaseVO purchase) throws Exception {
		purchaseDAO.addPurchase(purchase);
	}

	@Override
	public PurchaseVO getPurchase(int tranNo) throws Exception {
		return purchaseDAO.getPurchase(tranNo);
	}

	@Override
	public HashMap<String, Object> getPurchaseList(SearchVO search) throws Exception {
		return purchaseDAO.getPurchaseList(search);
	}

	@Override
	public HashMap<String, Object> getSaleList(SearchVO search) throws Exception {
		return null;
	}

	@Override
	public PurchaseVO updatePurchase(PurchaseVO purchase) throws Exception {
		return purchaseDAO.updatePurchase(purchase);
	}

	@Override
	public void updateTranCode(PurchaseVO purchase) throws Exception {
		purchaseDAO.updateTranCode(purchase);
	}

	@Override
	public void updateTranCodeByProd(ProductVO product) throws Exception {
		purchaseDAO.updateTranCodeByProd(product);
		
	}

	
}