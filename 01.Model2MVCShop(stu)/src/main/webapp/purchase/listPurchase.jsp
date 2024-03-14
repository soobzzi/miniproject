<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<%@ page import="java.util.*"  %>
<%@ page import="com.model2.mvc.service.purchase.vo.*" %>
<%@ page import="com.model2.mvc.service.product.vo.*" %>
<%@ page import="com.model2.mvc.common.*" %>

<%
	HashMap<String,Object> map = (HashMap<String,Object>)request.getAttribute("map");
	SearchVO search = (SearchVO)request.getAttribute("search");
	
	
	int total = 0;
	ArrayList<PurchaseVO> list = null;
	if(map != null){
		total=((Integer)map.get("count")).intValue();
		list = (ArrayList<PurchaseVO>)map.get("list");
	}
	
	
	String ps = getServletContext().getInitParameter("pageSize");
	int pageSize = Integer.parseInt(ps);
	int currentPage = search.getPage();
	int pageUnit = search.getPageUnit();
	int startPage = (( currentPage - 1 )/ pageSize ) * pageSize + 1 ;
	int endPage = (startPage + pageSize) - 1 ;
	
	
	int totalPage=0;
	if(total > 0) {
		totalPage= total / search.getPageUnit() ;
		//pageUnit 한페이지에 뽑아놓는 수
		if(total%search.getPageUnit() >0)
			totalPage += 1;
	}
	if(totalPage <= endPage){
		endPage = totalPage;
	}
	
	
	
	
%>





<html>
<head>
<title>구매 목록조회</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript">
	function fncGetUserList() {
		document.detailForm.submit();
	}
</script>
</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width: 98%; margin-left: 10px;">

<form name="detailForm" action="/listUser.do" method="post">

<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37"><img src="/images/ct_ttl_img01.gif"width="15" height="37"></td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left: 10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">구매 목록조회</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37"><img src="/images/ct_ttl_img03.gif"	width="12" height="37"></td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0"	style="margin-top: 10px;">
	<tr>
		<td colspan="11">전체 <%= total %> 건수, 현재 <%= currentPage %> 페이지</td>
	</tr>
	<tr>
		<td class="ct_list_b" width="100">No</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">회원ID</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">회원명</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">전화번호</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">배송현황</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">정보수정</td>
	</tr>
	<tr>
		<td colspan="11" bgcolor="808285" height="1"></td>
	</tr>

	
	<% 
	int no = list.size();
	
	for(int i = 0 ; i<list.size() ; i++){
		PurchaseVO purchase = (PurchaseVO)list.get(i);
		
	
	%>
	
	<tr class="ct_list_pop">
		<td align="center">
			<a href="/getPurchase.do?tranNo=<%= purchase.getTranNo()%>"><%= no-- %></a>
		</td>
		<td></td>
		<td align="left">
			<a href="/getUser.do?userId=<%= purchase.getBuyer().getUserId()%>"><%=purchase.getBuyer().getUserId() %></a>
		</td>
		<td></td>
		<td align="left"><%= purchase.getReceiverName() %></td>
		<td></td>
		<td align="left"><%= purchase.getReceiverPhone() %></td>
		<td></td>
		<td align="left">
		
			현재 구매완료 상태 입니다.</td>
		<td></td>
		<td align="left">
		
			<a href="/updateTranCode.do?tranNo=<%= purchase.getTranNo() %>&tranCode=3"><%= purchase.getPurchaseProd().getProTranCode() %></a>
		
			
		</td>
	</tr>
	<tr>
		<td colspan="11" bgcolor="D6D7D6" height="1"></td>
	</tr>
	<%} %>
	
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top: 10px;">
	<tr>
		<td align="center">
		
			
		<% for(int centerPage = startPage ; centerPage <= endPage ; centerPage++){ %>
		
			<%if(currentPage == totalPage){ %>
				<a href="/listPurchase.do?page=<%= totalPage %>"></a>
			<%} %>
		
		<%if(centerPage == currentPage ){ %>
			<b><%= centerPage %></b>
		<%}else{%>
		
				<a href="/listPurchase.do?page=<%= centerPage %>&menu=<%= request.getParameter("menu") %>"><%=centerPage %></a>
			
			<%}%>
		<%}%>
		
		<% if(currentPage < totalPage){ %>
			<a href="/listPurchase.do?page=<%= currentPage +1 %>&menu=<%=request.getParameter("menu")%>"> > </a>
		<%} %>
		
		 
		
		</td>
	</tr>
</table>

<!--  페이지 Navigator 끝 -->
</form>

</div>

</body>
</html>