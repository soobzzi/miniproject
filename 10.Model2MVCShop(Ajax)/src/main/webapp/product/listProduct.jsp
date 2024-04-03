<%@ page contentType="text/html; charset=EUC-KR"%>
 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<html>
<head>
<title>��ǰ �����ȸ</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">
<script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
<script type="text/javascript">

	function fncGetList(currentPage){
		document.getElementById("currentPage").value = currentPage;
		
		document.detailForm.submit();
	}
	
	<%--$(function(){
		$(".ct_list_pop td:nth-child(3)").on("click",function(){
			var prodNo = $($(this).children()).val();
	        var menu = "${param.menu}"; 
			self.location = "/product/getProduct?prodNo="+prodNo+"&menu="+menu;
			
			
		});
	}); --%>
	$(function(){
		$( ".ct_list_pop td:nth-child(3)").on("click" , function() {

			var prodNo = $($(this).children()).val();
	        var menu = "${param.menu}"; 
			$.ajax( 
					{
						url : "/product/json/getProduct/"+prodNo,
						method : "GET" ,
						dataType : "json" ,
						headers : {
							"Accept" : "application/json",
							"Content-Type" : "application/json"
						},
						success : function(JSONData , status) {
							
							alert(status);
							alert("JSONData : \n"+JSONData);
							
							var displayValue = "<h3>"
														
								+"�� ǰ ��  : "+JSONData.product.prodName+"<br/>"
								+"��   ��  : "+JSONData.product.price+"<br/>"
								+"�� �� �� : "+JSONData.product.manuDate+"<br/>"
								
								+"</h3>";
								
							$("h3").remove();
							$( "#"+prodNo+"" ).html(displayValue);
						}
				});
		});
		
		
			$(".ct_list_pop td:nth-child(3)").css("color","red");
			$("h7").css("color","red");
		
		
		
			$("td.ct_btn01:contains('�˻�')").on("click",function(){
				fncGetList(1);
			});
		
	});
	
	
	
	
	

</script>
</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width:98%; margin-left:10px;">

<form name="detailForm" action="/product/listProduct?menu=manage" method="post">

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
						��ǰ���� 
						</c:if>
					
					
					<c:if test = "${param.menu=='search'}">
						��ǰ�����ȸ
						</c:if>
						
					
				
				 <%-- %> <%if(menu != null && menu.equals("manage")){%>
								��ǰ ����
						<%}else{%>
								��ǰ�����ȸ
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
				
				<option value="0"  ${ ! empty search.searchCondition && search.searchCondition==0 ? "selected" : "" }>��ǰ��ȣ</option>
				<option value="1"  ${ ! empty search.searchCondition && search.searchCondition==1 ? "selected" : "" }>��ǰ��</option>
				<option value="2"  ${ ! empty search.searchCondition && search.searchCondition==2 ? "selected" : "" }>��ǰ����</option>
				
				<%-- <% if(searchCondition.equals("0")){ 
				
				<option value="0" selected>��ǰ��ȣ</option>
				<option value="1">��ǰ��</option>
				<option value="2">��ǰ����</option>
				
				<%}else if(searchCondition.equals("1")){ %>
				
				<option value="0">��ǰ��ȣ</option>
				<option value="1" selected>��ǰ��</option>
				<option value="2">��ǰ����</option>
				
				<%}else if(searchCondition.equals("2")) { %>
				
				<option value="0">��ǰ��ȣ</option>
				<option value="1" >��ǰ��</option>
				<option value="2" selected>��ǰ����</option>
				
				<% } %> --%>
				
				
			</select>
			<input type="text" name="searchKeyword"  value="${! empty search.searchKeyword ? search.searchKeyword : "" }"
					class="ct_input_g" style="width:200px; height:19px" />
		</td>
		
		
		<%--<%}else if(searchCondition == ""){ %>
				
		 <td align="right">
			<select name="searchCondition" class="ct_input_g" style="width:80px">
				<option value="0">��ǰ��ȣ</option>
				<option value="1">��ǰ��</option>
				<option value="2">��ǰ����</option>
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
						�˻�
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
		<td colspan="11" >��ü ${resultPage.totalCount} �Ǽ�, ���� ${resultPage.currentPage} ������</td>
	</tr>
	<tr>
		<td class="ct_list_b" width="100">No</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">��ǰ��<br>
			<h7> ( click : ������ )</h7>
		</td>
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
	
	<%-- 	
		int no=list.size();
		for(int i=0; i<list.size(); i++) {
			Product vo = (Product)list.get(i);
	--%>
	
		<c:set var="i" value ="0"/>	 <%--�ʱ�ȭ --%>
		<c:forEach var ="product" items="${map.list}"><%-- ���δ�Ʈ���� ����Ʈ�����ͼ� ����Ʈ��ŭ ������ --%>
			<c:set var ="i" value = "${ i+1 }" />
			<tr class="ct_list_pop">
				<td align="center">${ i }</td>
				<td></td>
				
				<td align="left">${product.prodName} 
				<input type = "hidden" name = "prodNo" value = "${product.prodNo}">
				</td>
					<%-- <td align="left"><a href="/product/getProduct?prodNo=${product.prodNo}&menu=${param.menu}">${product.prodName}</a></td>
					--%>
				<!--  request�� �Ķ���͸� ��Ƽ� ��(menu) -->
				<td></td>
				<td align="left">${product.price}</td>
				<td></td>
				<td align="left">${product.manuDate}</td>
				<td></td>
				<td align="left">
			
				
					�Ǹ���
				
					</td>	
			</tr>
			<tr>
				<td id = "${product.prodNo}" colspan="11" bgcolor="D6D7D6" height="1"></td>
			</tr>	
	</c:forEach>	
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td align="center">
		 <input type="hidden" id="currentPage" name="currentPage" value="${i}"/>
		
		<jsp:include page="../common/pageNavigator.jsp"/>
 
		
    	</td>
	</tr>
</table>
<!--  ������ Navigator �� -->

</form>

</div>
</body>
</html>