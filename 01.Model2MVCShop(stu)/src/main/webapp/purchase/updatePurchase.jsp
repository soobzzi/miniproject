<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
    <%@ page import = "com.model2.mvc.service.purchase.vo.*" %>    
    
    
    <% PurchaseVO pur = (PurchaseVO)request.getAttribute("pur"); 

	System.out.println(pur.getBuyer().getUserId());%>
<html>
<head>
<title>Insert title here</title>
</head>

<body>

<form name="updatePurchase" action="/updatePurchaseView.do?tranNo=0" method="post">

������ ���� ���Ű� �Ǿ����ϴ�.

<table border=1>
	<tr>
		<td>��ǰ��ȣ</td>
		<td><%=pur.getTranNo() %></td>
		<td></td>
	</tr>
	<tr>
		<td>�����ھ��̵�</td>
		<td><%=pur.getBuyer().getUserId() %></td>
		<td></td>
	</tr>
	<tr>
		<td>���Ź��</td>
		<td>
		
			<%=pur.getPaymentOption() %>
		
		</td>
		<td></td>
	</tr>
	<tr>
		<td>�������̸�</td>
		<td><%=pur.getReceiverName() %></td>
		<td></td>
	</tr>
	<tr>
		<td>�����ڿ���ó</td>
		<td><%=pur.getReceiverPhone() %></td>
		<td></td>
	</tr>
	<tr>
		<td>�������ּ�</td>
		<td><%=pur.getDivyAddr() %></td>
		<td></td>
	</tr>
		<tr>
		<td>���ſ�û����</td>
		<td><%=pur.getDivyRequest() %></td>
		<td></td>
	</tr>
	<tr>
		<td>����������</td>
		<td><%=pur.getDivyDate() %></td>
		<td></td>
	</tr>
</table>
</form>

</body>
</html>