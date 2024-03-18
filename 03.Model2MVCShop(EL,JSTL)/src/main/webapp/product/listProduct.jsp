<%@ page contentType="text/html; charset=EUC-KR"%>

<%-- <%@ page import="java.util.*"  %>
<%@ page import="com.model2.mvc.service.domain.Product" %>

<%@ page import="com.model2.mvc.common.util.CommonUtil"%>
<%@ page import="com.model2.mvc.common.Search" %>
<%@ page import="com.model2.mvc.common.Page"%>--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--<%
	HashMap<String,Object> map = (HashMap<String,Object>)request.getAttribute("map");

	//List<Product> list = (List<Product>)request.getAttribute("list");
	List<Product> list = (List<Product>)map.get("list");
	Page resultPage = (Page)request.getAttribute("resultPage");
	Search search = (Search)request.getAttribute("searchVO");
	
	
	String searchCondition = CommonUtil.null2str(search.getSearchCondition());
	String searchKeyword = CommonUtil.null2str(search.getSearchKeyword());

	String menu = request.getParameter("menu");
--%>

<html>
<head>
<title>상품 목록조회</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript">
<!--
function fncGetProductList(currentPage){
	document.getElementById("currentPage").value = currentPage;
	
	document.detailForm.submit();
}
-->
</script>
</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width:98%; margin-left:10px;">

<form name="detailForm" action="/listProduct.do?menu=manage" method="post">

<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37">
			<img src="/images/ct_ttl_img01.gif" width="15" height="37"/>
		</td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left:10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">
				
					<c:if test = "${param.menu=='manage'}">
						상품관리 
						</c:if>
					
					
					<c:if test = "${param.menu=='search'}">
						상품목록조회
						</c:if>
						
					
				
				 <%-- %> <%if(menu != null && menu.equals("manage")){%>
								상품 관리
						<%}else{%>
								상품목록조회
						<%}%> --%>
						
					</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37">
			<img src="/images/ct_ttl_img03.gif" width="12" height="37"/>
		</td>
	</tr>
</table>


<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<%-- <% if(searchCondition != "") {--%>
		
		<td align="right">
		
			<select name="searchCondition" class="ct_input_g" style="width:80px">
				
				<option value="0"  ${ ! empty search.searchCondition && search.searchCondition==0 ? "selected" : "" }>상품번호</option>
				<option value="1"  ${ ! empty search.searchCondition && search.searchCondition==1 ? "selected" : "" }>상품명</option>
				<option value="2"  ${ ! empty search.searchCondition && search.searchCondition==2 ? "selected" : "" }>상품가격</option>
				
				<%-- <% if(searchCondition.equals("0")){ 
				
				<option value="0" selected>상품번호</option>
				<option value="1">상품명</option>
				<option value="2">상품가격</option>
				
				<%}else if(searchCondition.equals("1")){ %>
				
				<option value="0">상품번호</option>
				<option value="1" selected>상품명</option>
				<option value="2">상품가격</option>
				
				<%}else if(searchCondition.equals("2")) { %>
				
				<option value="0">상품번호</option>
				<option value="1" >상품명</option>
				<option value="2" selected>상품가격</option>
				
				<% } %> --%>
				
				
			</select>
			<input type="text" name="searchKeyword"  value="${! empty search.searchKeyword ? search.searchKeyword : "" }"
					class="ct_input_g" style="width:200px; height:19px" />
		</td>
		
		
		<%--<%}else if(searchCondition == ""){ %>
				
		 <td align="right">
			<select name="searchCondition" class="ct_input_g" style="width:80px">
				<option value="0">상품번호</option>
				<option value="1">상품명</option>
				<option value="2">상품가격</option>
			</select>
			<input type="text" name="searchKeyword" class="ct_input_g" style="width:200px; height:19px" >
		</td>
		
		<% } %>
		--%>
		
		<td align="right" width="70">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="17" height="23">
						<img src="/images/ct_btnbg01.gif" width="17" height="23">
					</td>
					<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
						<a href="javascript:fncGetProductList(1);">검색</a>
					</td>
					<td width="14" height="23">
						<img src="/images/ct_btnbg03.gif" width="14" height="23">
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>


<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td colspan="11" >전체 ${resultPage.totalCount} 건수, 현재 ${resultPage.currentPage} 페이지</td>
	</tr>
	<tr>
		<td class="ct_list_b" width="100">No</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">상품명</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">가격</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">등록일</td>	
		<td class="ct_line02"></td>
		<td class="ct_list_b">현재상태</td>	
	</tr>
	<tr>
		<td colspan="11" bgcolor="808285" height="1"></td>
	</tr>
	
	<%-- 	
		int no=list.size();
		for(int i=0; i<list.size(); i++) {
			Product vo = (Product)list.get(i);
	--%>
	
		<c:set var="i" value ="0"/>	 <%--초기화 --%>
		<c:forEach var ="product" items="${map.list}"><%-- 프로덕트에서 리스트가져와서 리스트만큼 돌리기 --%>
			<c:set var ="i" value = "${ i+1 }" />
			<tr class="ct_list_pop">
				<td align="center">${ i }</td>
				<td></td>
					<td align="left"><a href="/getProduct.do?prodNo=${product.prodNo}&menu=${param.menu}">${product.prodName}</a></td>
					
				<!--  request에 파라미터를 담아서 옴(menu) -->
				<td></td>
				<td align="left">${product.price}</td>
				<td></td>
				<td align="left">${product.manuDate}</td>
				<td></td>
				<td align="left">
			
				
			<% System.out.println("tranCode : "+ product.getProTranCode()); %>
			
			<% if(vo.getProTranCode().equals("판매완료")&&(product.equals("manage")&&(menu != null))) {%>
			<a href="/updateTranCodeByProd.do?prodNo=<%= product.getProdNo()%>">배송하기</a>
			
			<%}else if(product.getProTranCode().equals("판매중")&&(menu != null)) {%>
			 판매중
			
			<%}else{ %>
			 재고없음
			<%}%>
				
					</td>	
			</tr>
			<tr>
				<td colspan="11" bgcolor="D6D7D6" height="1"></td>
			</tr>	
	</c:forEach>	
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td align="center">
		 <input type="hidden" id="currentPage" name="currentPage" value=""/>
		
		<%-- 
		<% if(resultPage.getBeginUnitPage() > 1) {%>
			<a href="/listProduct.do?page=1&menu=<%=request.getParameter("menu")%>"> ◀ </a>
		<%} %>
		
		<% if(resultPage.getCurrentPage() > 1) {%>
			<a href="/listProduct.do?page=<%=resultPage.getCurrentPage()-1%>&menu=<%=request.getParameter("menu")%>"> ◁ </a>
		<%} %>
		
		<% for(int i = resultPage.getBeginUnitPage() ; i <= resultPage.getEndUnitPage() ; i++){ %>
			<% if(searchCondition != null && searchKeyword != null) {%>
			<a href="/listProduct.do?page=<%=i %>&menu=<%=menu%>&searchCondition=<%=searchCondition %>&searchKeyword=<%=searchKeyword %>"><%=i %></a>
			
			<%}else{ %>
			<a href="/listProduct.do?page=<%=i %>&menu=<%=menu%>"></a>
			<%} %>
		<%} %>
		
		<%if(resultPage.getCurrentPage() < resultPage.getEndUnitPage()){ %>
			<a href="/listProduct.do?page=<%=resultPage.getEndUnitPage()+1%>&menu=<%=request.getParameter("menu")%>">▷</a>
		<%} %>
			
		<% if(resultPage.getEndUnitPage() <resultPage.getMaxPage()) {%>
			<a href="/listProduct.do?page=<%=resultPage.getMaxPage()%>&menu=<%=request.getParameter("menu")%>">▶</a>
		<%} %>
		
		--%>
		
		<jsp:include page="../common/pageNavigator.jsp"/>
 
		
    	</td>
	</tr>
</table>
<!--  페이지 Navigator 끝 -->

</form>

</div>
</body>
</html>