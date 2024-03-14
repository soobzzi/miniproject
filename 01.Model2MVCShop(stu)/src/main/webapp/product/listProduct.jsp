<%@ page contentType="text/html; charset=EUC-KR"%>

<%@ page import="java.util.*"  %>
<%@ page import="com.model2.mvc.service.product.vo.*" %>
<%@ page import="com.model2.mvc.common.*" %>

<%
	HashMap<String,Object> map=(HashMap<String,Object>)request.getAttribute("map");
	SearchVO searchVO=(SearchVO)request.getAttribute("searchVO");
	
	int total=0;
	ArrayList<ProductVO> list = null;
	if(map != null){
		total=((Integer)map.get("count")).intValue();
		list = (ArrayList<ProductVO>)map.get("list");
	}
	
	
	String ps = getServletContext().getInitParameter("pageSize");
	int pageSize = Integer.parseInt(ps);
	int currentPage=searchVO.getPage();
	int pageUnit = searchVO.getPageUnit();
	int startPage = (( currentPage - 1 )/ pageSize ) * pageSize + 1 ;
	int endPage = (startPage + pageSize) - 1 ;
	
	
	int totalPage=0;
	if(total > 0) {
		totalPage= total / searchVO.getPageUnit() ;
		//pageUnit ���������� �̾Ƴ��� ��
		if(total%searchVO.getPageUnit() >0)
			totalPage += 1;
	}
	if(totalPage <= endPage){
		endPage = totalPage;
	}

	String menu = request.getParameter("menu");
%>

<html>
<head>
<title>��ǰ �����ȸ</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript">
<!--
function fncGetProductList(){
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
				
				  <%if(menu != null && menu.equals("manage")){%>
								��ǰ ����
						<%}else{%>
								��ǰ�����ȸ
						<%}%>
						
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
		<% if(searchVO.getSearchCondition() != null) {%>
		
		<td align="right">
		
			<select name="searchCondition" class="ct_input_g" style="width:80px">
				
				<% if(searchVO.getSearchCondition().equals("0")){ %>
				
				<option value="0" selected>��ǰ��ȣ</option>
				<option value="1">��ǰ��</option>
				<option value="2">��ǰ����</option>
				
				<%}else if(searchVO.getSearchCondition().equals("1")){ %>
				
				<option value="0">��ǰ��ȣ</option>
				<option value="1" selected>��ǰ��</option>
				<option value="2">��ǰ����</option>
				
				<%}else if(searchVO.getSearchCondition().equals("2")) { %>
				
				<option value="0">��ǰ��ȣ</option>
				<option value="1" >��ǰ��</option>
				<option value="2" selected>��ǰ����</option>
				
				<% } %>
				
				
			</select>
			<input type="text" name="searchKeyword"  value="<%=searchVO.getSearchKeyword() %>"
					class="ct_input_g" style="width:200px; height:19px" />
		</td>
		
		
		<%}else if(searchVO.getSearchCondition() == null){ %>
				
		<td align="right">
			<select name="searchCondition" class="ct_input_g" style="width:80px">
				<option value="0">��ǰ��ȣ</option>
				<option value="1">��ǰ��</option>
				<option value="2">��ǰ����</option>
			</select>
			<input type="text" name="searchKeyword" class="ct_input_g" style="width:200px; height:19px" >
		</td>
		
		<% } %>
		
		<td align="right" width="70">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="17" height="23">
						<img src="/images/ct_btnbg01.gif" width="17" height="23">
					</td>
					<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
						<a href="javascript:fncGetProductList();">�˻�</a>
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
		<td colspan="11" >��ü <%= total %> �Ǽ�, ���� <%= currentPage %> ������</td>
	</tr>
	<tr>
		<td class="ct_list_b" width="100">No</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">��ǰ��</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">����</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">�����</td>	
		<td class="ct_line02"></td>
		<td class="ct_list_b">�������</td>	
	</tr>
	<tr>
		<td colspan="11" bgcolor="808285" height="1"></td>
	</tr>
	
	<% 	
		int no=list.size();
		for(int i=0; i<list.size(); i++) {
			ProductVO vo = (ProductVO)list.get(i);
			
			System.out.println(vo.toString());
	%>
		
	<tr class="ct_list_pop">
		<td align="center"><%= no-- %></td>
		<td></td>
			<td align="left"><a href="/getProduct.do?prodNo=<%= vo.getProdNo()%>&menu=<%= request.getParameter("menu")%>"><%= vo.getProdName() %></a></td>
			
		<!--  request�� �Ķ���͸� ��Ƽ� ��(menu) -->
		<td></td>
		<td align="left"><%= vo.getPrice() %></td>
		<td></td>
		<td align="left"><%= vo.getManuDate() %></td>
		<td></td>
		<td align="left">
	
		
			
			<% if(vo.getProTranCode().equals("�ǸſϷ�")&&(menu.equals("manage"))) {%>
			<a href="/updateTranCodeByProd.do?prodNo=<%= vo.getProdNo()%>">����ϱ�</a>
			
			<%}else if(vo.getProTranCode().equals("�Ǹ���")&&(menu.equals("search"))) {%>
			 �Ǹ���
			
			<%}else{ %>
			 ������
			<%}%>
		
		</td>	
	</tr>
	<tr>
		<td colspan="11" bgcolor="D6D7D6" height="1"></td>
	</tr>		
	<% } %>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td align="center">
		<input type="hidden" id="currentPage" name="currentPage" value=""/>
		
		
		
		
			
	<% for(int centerPage = startPage ; centerPage <= endPage ; centerPage++){ %>
		
			<%if(currentPage == totalPage){ %>
				<a href="/listProduct.do?page=<%= totalPage %>"></a>
			<%} %>
		
		<%if(centerPage == currentPage ){ %>
			<b><%= centerPage %></b>
		<%}else{%>
		
			<% if(searchVO.getSearchKeyword() != null ){ %>
				<a href="/listProduct.do?page=<%= centerPage %>&menu=<%= request.getParameter("menu") %>&searchCondition=<%= searchVO.getSearchCondition() %>&searchKeyword=<%= searchVO.getSearchKeyword() %>"><%=centerPage %></a>
			<%}else{ %>
				<a href="/listProduct.do?page=<%= centerPage %>&menu=<%= request.getParameter("menu") %>"><%=centerPage %></a>
			
			<%}%>
			
		<%}%>
		
		<%} %>
		
		<% if(currentPage < totalPage){ %>
			<% if (searchVO.getSearchKeyword() != null) {%>
			<a href="/listProduct.do?page=<%= currentPage +1 %>&menu=<%=request.getParameter("menu")%>&searchCondition=<%= searchVO.getSearchCondition()%>&searchKeyword=<%=searchVO.getSearchKeyword()%>"> > </a>
		<%}else{%>
			<a href="/listProduct.do?page=<%= currentPage +1 %>&menu=<%=request.getParameter("menu")%>"> > </a>
		<%} %>
		<%} %>
			
		
    	</td>
	</tr>
</table>
<!--  ������ Navigator �� -->

</form>

</div>
</body>
</html>