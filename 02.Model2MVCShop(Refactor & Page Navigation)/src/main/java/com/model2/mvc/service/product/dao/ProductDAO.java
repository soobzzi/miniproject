package com.model2.mvc.service.product.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.model2.mvc.common.Search;
import com.model2.mvc.common.Search;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.domain.Product;


public class ProductDAO {
	
	public ProductDAO() {
	}
	
	public Product findProduct(int prodNo) throws Exception{
		
		Connection con = DBUtil.getConnection();
		
		String sql = "select * from product where prod_no = ?";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, prodNo);
		
		ResultSet rs = stmt.executeQuery();
		
		Product productVO = null;
		
		while(rs.next()) {
			productVO = new Product();
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
	

	public HashMap<String,Object> getProductList(Search searchVO) throws Exception{
		
		
		Connection con = DBUtil.getConnection();
		
		String sql = "select p.prod_no,p.prod_name ,p.prod_detail, p.manufacture_day,p.price,p.image_file,p.reg_date ,t.tran_status_code"
				+ " from product p, transaction t"
				+ " where p.prod_no = t.prod_no(+)";
		if(searchVO.getSearchCondition() != null) {
			if(searchVO.getSearchCondition().equals("0")) {
				System.out.println("라랄라라라랄");
				sql += "AND p.PROD_NO like '%" + searchVO.getSearchKeyword()+"%' ";
			}else if(searchVO.getSearchCondition().equals("1")) {
				sql += "AND p.PROD_NAME like '%" + searchVO.getSearchKeyword()+"%' ";
			}else if(searchVO.getSearchCondition().equals("2")) {
				sql += "AND p.PRICE like '%" + searchVO.getSearchKeyword()+"%' ";
			}
		}
			//SearchCondition은 앞에 선택하는부분 , Keyword 는 뒤에 검색할거 적는 부분
	
		sql += "ORDER BY PROD_NO";
		System.out.println("ProductDAO :: sql " + sql);
		
		int total = this.getTotalCount(sql);
		System.out.println("Product :: totalcount "+total);
					
		sql = makeCurrentPageSql(sql, searchVO);
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		
		System.out.println(searchVO);
		
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("count", new Integer(total));
		
		ArrayList<Product> list = new ArrayList<Product>();
		
		while(rs.next()) {
			// rs.next: sql문을 통해 얻은 데이터에서 첫번째 행에서 마지막행까지 추출할때 사용
			System.out.println("라랄라라라랄");
				Product vo = new Product();
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
		}
		
		
		
		map.put("list",list);
		
		con.close();
		rs.close();
		stmt.close();
		
		return map;
		
	}
	
	public void insertProduct(Product productVO) throws Exception{
			
			Connection con = DBUtil.getConnection();
			
			String sql = "insert into PRODUCT values(seq_product_prod_no.nextval,?,?,?,?,?,sysdate)";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		
		
		stmt.setString(1, productVO.getProdName());
		stmt.setString(2, productVO.getProdDetail());
		stmt.setString(3, productVO.getManuDate());
		stmt.setInt(4, productVO.getPrice());
		stmt.setString(5, productVO.getFileName());
		
		
		stmt.executeUpdate();
		
		con.close();
		
	}

	public void updateProduct(Product productVO) throws Exception {
		
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
}
	
	

	
