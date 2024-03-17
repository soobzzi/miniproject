package com.model2.mvc.service.product.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.product.vo.ProductVO;


public class ProductDAO {
	
	public ProductDAO() {
	}
	
	public ProductVO findProduct(int prodNo) throws Exception{
		
		Connection con = DBUtil.getConnection();
		
		String sql = "select * from product where prod_no = ?";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, prodNo);
		
		ResultSet rs = stmt.executeQuery();
		
		ProductVO productVO = null;
		
		while(rs.next()) {
			productVO = new ProductVO();
			productVO.setProdNo(rs.getInt("PROD_NO"));
			productVO.setProdName(rs.getString("PROD_NAME"));
			productVO.setManuDate(rs.getString("MANUFACTURE_DAY"));
			productVO.setPrice(rs.getInt("PRICE"));
			productVO.setFileName(rs.getString("IMAGE_FILE"));
			productVO.setProdDetail(rs.getString("PROD_DETAIL"));
			productVO.setRegDate(rs.getDate("REG_DATE"));
		}
		
		con.close();
			
		return productVO;
		
	}
	

	public HashMap<String,Object> getProductList(SearchVO searchVO) throws Exception{
		
		Connection con = DBUtil.getConnection();
		
		String sql = "select p.prod_no,p.prod_name ,p.prod_detail, p.manufacture_day,p.price,p.image_file,p.reg_date ,t.tran_status_code"
				+ " from product p, transaction t"
				+ " where p.prod_no = t.prod_no(+)";
		if(searchVO.getSearchCondition() != null) {
			if(searchVO.getSearchCondition().equals("0")) {
				sql += "where PROD_NO like '%" + searchVO.getSearchKeyword()+"%' ";
			}else if(searchVO.getSearchCondition().equals("1")) {
				sql += "where PROD_NAME like '%" + searchVO.getSearchKeyword()+"%' ";
			}else if(searchVO.getSearchCondition().equals("2")) {
				sql += "where PRICE like '%" + searchVO.getSearchKeyword()+"%' ";
			}
		}
			//SearchCondition은 앞에 선택하는부분 , Keyword 는 뒤에 검색할거 적는 부분
	
		sql += "ORDER BY PROD_NO";
		
		System.out.println(sql);
		
		PreparedStatement stmt = con.prepareStatement( sql,ResultSet.TYPE_SCROLL_INSENSITIVE,
														ResultSet.CONCUR_UPDATABLE);
	
		ResultSet rs = stmt.executeQuery();
		
		rs.last();
		int total = rs.getRow();
		System.out.println("로우의 수: "+total);
		
		HashMap<String,Object> map = new HashMap<String,Object>();
		
		map.put("count", new Integer(total));
		
		rs.absolute(searchVO.getPage() * searchVO.getPageUnit() - searchVO.getPageUnit()+1);
		System.out.println("searchVO.getPage():" + searchVO.getPage());
		System.out.println("searchVO.getPageUnit():" + searchVO.getPageUnit());
		
		ArrayList<ProductVO> list = new ArrayList<ProductVO>();
		
		if (total > 0) {
			for (int i = 0; i < searchVO.getPageUnit(); i++) {
				ProductVO vo = new ProductVO();
				vo.setProdNo(rs.getInt("PROD_NO"));
				vo.setProdName(rs.getString("PROD_NAME"));
				vo.setManuDate(rs.getString("MANUFACTURE_DAY"));
				vo.setPrice(rs.getInt("PRICE"));
				vo.setFileName(rs.getString("IMAGE_FILE"));
				vo.setProdDetail(rs.getString("PROD_DETAIL"));
				vo.setRegDate(rs.getDate("REG_DATE"));
				
				String TranCode = rs.getString("tran_status_code");
				
				System.out.println(":::::"+TranCode);
				
				if(TranCode != null) {
					TranCode = TranCode.trim();
					
				}
				
				if(TranCode == null) {
					vo.setProTranCode("판매중");
				}else if(TranCode.equals("0")) {
					vo.setProTranCode("판매완료");
				}else if(TranCode.equals("1")) {
					vo.setProTranCode("배송중");
				}else if(TranCode.equals("2")){
					vo.setProTranCode("배송완료");
				}
				
				
				list.add(vo);
//				
//				if (!rs.next())
//					break;
			}
		}
		
		System.out.println("list.size() : " +list.size());
		map.put("list",list);
		System.out.println("map().size : " + map.size());
		
		con.close();
		
		return map;
		
	}
	
	public void insertProduct(ProductVO productVO) throws Exception{
			
			Connection con = DBUtil.getConnection();
			
			String sql = "insert into PRODUCT values(seq_product_prod_no.nextval,?,?,?,?,?,sysdate)";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		
		
		stmt.setString(1, productVO.getProdName());
		stmt.setString(2, productVO.getManuDate());
		stmt.setInt(3, productVO.getPrice());
		stmt.setString(4, productVO.getFileName());
		stmt.setString(5, productVO.getProdDetail());
		
		stmt.executeUpdate();
		
		con.close();
		
	}

	public void updateProduct(ProductVO productVO) throws Exception {
		
		Connection con = DBUtil.getConnection();
		
		String sql = "update product set  PROD_NAME = ? , MANUFACTURE_DAY = ? , PRICE = ? , IMAGE_FILE = ? where prod_no = ?";
		
		PreparedStatement stmt = con.prepareStatement(sql);
			
		stmt.setString(1, productVO.getProdName());
		stmt.setString(2, productVO.getManuDate());
		stmt.setInt(3, productVO.getPrice());
		stmt.setString(4, productVO.getFileName());
		stmt.setInt(5, productVO.getProdNo());
		
		stmt.executeUpdate();
		
		con.close();
		
	}
	
	

}
