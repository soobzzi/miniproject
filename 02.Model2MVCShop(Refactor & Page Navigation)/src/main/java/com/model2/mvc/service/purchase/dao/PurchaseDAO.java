package com.model2.mvc.service.purchase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.model2.mvc.common.Search;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.impl.UserServiceImpl;


public class PurchaseDAO {
	
	public void addPurchase(Purchase purchase) throws Exception{
		
		Connection con = DBUtil.getConnection();
		
		String sql = "INSERT INTO transaction values"
				+ "(seq_transaction_tran_no.nextval,?,?,?,?,?,?,?,?,sysdate,?)";
					
		PreparedStatement stmt = con.prepareStatement(sql);
		//Pruco sprco = Purchase.get(); 미리 user랑 purchase 불러와서 사용가능
		
		stmt.setInt(1, purchase.getPurchaseProd().getProdNo());
		stmt.setString(2, purchase.getBuyer().getUserId());
		stmt.setString(3, purchase.getPaymentOption());
		stmt.setString(4, purchase.getReceiverName());
		stmt.setString(5, purchase.getReceiverPhone());
		stmt.setString(6, purchase.getDivyAddr());
		stmt.setString(7, purchase.getDivyRequest());
		stmt.setString(8, purchase.getTranCode());
		stmt.setString(9, purchase.getDivyDate());
		
		
		stmt.executeUpdate();
		
		con.close();
		
		}		
	
	
	public Purchase getPurchase(int tranNo) throws Exception{
		
		Connection con = DBUtil.getConnection();
		
		String sql = "SELECT * FROM transaction where tran_no = ?";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1,tranNo);
		
		ResultSet rs = stmt.executeQuery();
		
		Purchase purchase = null;
		
		ProductService service = new ProductServiceImpl();
		UserService servicee = new UserServiceImpl();
		
		
		while(rs.next()) {
			purchase = new Purchase();
			
			purchase.setTranNo(rs.getInt("tran_no"));
			purchase.setPurchaseProd(service.getProduct(rs.getInt("prod_no")));
			purchase.setBuyer(servicee.getUser(rs.getString("buyer_id")));
			//굿
			purchase.setPaymentOption(rs.getString("payment_option").trim());
			purchase.setReceiverName(rs.getString("receiver_name"));
			purchase.setReceiverPhone(rs.getString("receiver_phone"));
			purchase.setDivyAddr(rs.getString("demailaddr"));
			purchase.setDivyRequest(rs.getString("dlvy_request"));
			purchase.setTranCode(rs.getString("tran_status_code"));
			purchase.setOrderDate(rs.getDate("order_data"));
			purchase.setDivyDate(rs.getString("dlvy_date"));
			
		}
		
		con.close();
		return purchase;
	}
	
	public HashMap<String,Object> getPurchaseList(Search search) throws Exception{
		
		Connection con = DBUtil.getConnection();
		
		String sql = "select  buyer_id, demailaddr, "
				+ "TO_CHAR(dlvy_date, 'YYYY-MM-DD') dlvy_date, "
				+ "dlvy_request, order_data,payment_option ,receiver_name,"
				+ "receiver_phone,tran_status_code, tran_no,prod_no "
				+ "FROM transaction";
		
	
		int total = this.getTotalCount(sql);
		
		sql = makeCurrentPageSql(sql,search);
		
		PreparedStatement stmt = con.prepareStatement(sql);
		
		ResultSet rs = stmt.executeQuery();
		
		HashMap<String,Object> map = new HashMap<String,Object>();	
		ArrayList<Purchase> list = new ArrayList<Purchase>();
		
		ProductService proservice = new ProductServiceImpl();
		UserService uservice = new UserServiceImpl();
		
		while(rs.next()) {
			
				Purchase pur= new Purchase();
				pur.setTranNo(rs.getInt("tran_no"));
				pur.setPurchaseProd(proservice.getProduct(rs.getInt("prod_no")));
				pur.setBuyer(uservice.getUser(rs.getString("buyer_id")));
				pur.setReceiverName(rs.getString("receiver_name"));
				pur.setReceiverPhone(rs.getString("receiver_phone"));
				String TranCode = rs.getString("tran_status_code").trim();
				
				//null 은 없어.... 프로덕트만... 구매안하면 안생기니까 없는거야...
				
				if(TranCode.equals("0")) {
					pur.setTranCode("현재 구매완료");
				}else if(TranCode.equals("1")) {
					pur.setTranCode("현재 배송중");
				}else if(TranCode.equals("2")){
					pur.setTranCode("배송완료");
				}
								
				list.add(pur);
				
//				if(!rs.next()) {
//					break;
//				}
			}
			
			
			map.put("list",list);
			System.out.println("list사이즈 :"+list.size());
			
			map.put("count",new Integer(total));
			System.out.println("map사이즈 :"+map.size());
			
			rs.close();
			stmt.close();
			con.close();
			
			
			return map;
		}	
		
		
		
	
//	public HashMap<String, Object> getSaleList(SearchVO search) throws Exception {
//		
//		Connection con = DBUtil.getConnection();
//		
//		String sql = "select * from transaction";
//		
//		PreparedStatement stmt = con.prepareStatement(sql);
//		
//	
//		return null;
//	}
	
	public Purchase updatePurchase(Purchase purchase) throws Exception{
		
		Connection con = DBUtil.getConnection();
		
		String sql = "update transaction set buyer_id = ?,payment_option = ?,receiver_name= ?,"
				+ "receiver_phone= ?,demailaddr= ?,dlvy_request= ?,dlvy_date= ? where tran_no = ?";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		
		stmt.setString(1, purchase.getBuyer().getUserId());
		stmt.setString(2, purchase.getPaymentOption());
		stmt.setString(3, purchase.getReceiverName());
		stmt.setString(4, purchase.getReceiverName());
		stmt.setString(5, purchase.getReceiverPhone());
		stmt.setString(6, purchase.getDivyAddr());
		stmt.setString(7, purchase.getDivyRequest());
		stmt.setString(8, purchase.getDivyDate());
		stmt.setInt(9, purchase.getTranNo());
		
		stmt.executeUpdate();
		
		con.close();
		stmt.close();
		
		
		return purchase;
	}
	
	public void updateTranCode(Purchase purchase) throws Exception {
		
		Connection con = DBUtil.getConnection();
		
		String sql = "update transaction set tran_status_code = 2 where tran_no = ?";
		//배송도착

		PreparedStatement stmt = con.prepareStatement(sql);
		
		stmt.setInt(1, purchase.getTranNo());
		stmt.executeUpdate();
		
		con.close();
		
	}
	
	public void updateTranCodeByProd(Product product) throws Exception {
		//해당 물건리스트 중에서 판매완료된 애들 배송시작하는 버튼 누르는 액션
		
		Connection con = DBUtil.getConnection();
		
		String sql = "update transaction set tran_status_code = 1 where prod_no = ?";
		//배송시작

		PreparedStatement stmt = con.prepareStatement(sql);
		
		stmt.setInt(1, product.getProdNo());
		
		stmt.executeUpdate();
		
		con.close();
		stmt.close();
		
	}
	
	private String makeCurrentPageSql(String sql, Search search) {
		//페이지수
		
		sql = "SELECT * FROM "
				+ "( SELECT inner_table.* , ROWNUM rnum " 
				+ "FROM ( "+sql+" ) inner_table "
				+ "WHERE ROWNUM <= " +search.getCurrentPage() * search.getPageSize()+") "
				+ "WHERE rnum BETWEEN "+((search.getCurrentPage()-1) * search.getPageSize()+1)+ " AND " + search.getCurrentPage() * search.getPageSize();
		
			System.out.println("ProductDAO :: make SQL :: "+ sql);
			
			return sql;
	}
	
	private int getTotalCount(String sql) throws Exception{
		
		sql = "SELECT COUNT(*) "+
				"FROM ("+sql+") countTable";
		
		Connection con = DBUtil.getConnection();
		PreparedStatement pStmt = con.prepareStatement(sql);
		ResultSet rs = pStmt.executeQuery();
		
		
		int totalCount = 0;
		if(rs.next()) {
			totalCount = rs.getInt(1);
		}
		
		pStmt.close();
		con.close();
		rs.close();
		
		return totalCount;	
	}
	

	
	
}




