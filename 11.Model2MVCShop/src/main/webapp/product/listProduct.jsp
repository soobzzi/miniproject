<%@ page contentType="text/html; charset=EUC-KR"%>
<%@ page pageEncoding="EUC-KR"%>

<!--  ///////////////////////// JSTL  ////////////////////////// -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>

<html lang="ko">

<head>
<meta charset="EUC-KR">

<!-- ���� : http://getbootstrap.com/css/   ���� -->
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<!--  ///////////////////////// Bootstrap, jQuery CDN ////////////////////////// -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


<!-- Bootstrap Dropdown Hover CSS -->
<link href="/css/animate.min.css" rel="stylesheet">
<link href="/css/bootstrap-dropdownhover.min.css" rel="stylesheet">
<!-- Bootstrap Dropdown Hover JS -->
<script src="/javascript/bootstrap-dropdownhover.min.js"></script>



<!-- jQuery UI toolTip ��� CSS-->
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<!-- jQuery UI toolTip ��� JS-->
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<!--  ///////////////////////// CSS ////////////////////////// -->
<style>
body {
	padding-top: 50px;
}
</style>

<!--  ///////////////////////// JavaScript ////////////////////////// -->
<script type="text/javascript">
	//=============    �˻� / page �ΰ��� ��� ���  Event  ó�� =============	
	function fncGetList(currentPage) {
		$("#currentPage").val(currentPage)
		$("form").attr("method", "POST").attr("action",
				"/product/listProduct?menu=${param.menu}").submit();
	}

	//============= "�˻�"  Event  ó�� =============	
	$(function() {
		//==> DOM Object GET 3���� ��� ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
		//$( "button.btn.btn-default" ).on("click" , function() {
		//	fncGetUserList(1);
		//});
	});

	//============= userId �� ȸ����������  Event  ó��(Click) =============	
	// $(function() {

	//==> DOM Object GET 3���� ��� ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
	//	$("td:nth-child(2)").on("click", function() {
	//		self.location = "/user/getUser?userId=" + $(this).text().trim();
	//	});

	//==> userId LINK Event End User ���� ���ϼ� �ֵ��� 
	//	$("td:nth-child(2)").css("color", "red");

	//});

	//============= userId �� ȸ����������  Event  ó�� (double Click)=============
	$(function() {
		$(".ct_list_pop td:nth-child(5)").on(
				"click",
				function() {

					var prodNo = $($(this).children()).val();
					var menu = "${param.menu}";
					$.ajax({
						url : "/product/json/getProduct/" + prodNo,
						method : "GET",
						dataType : "json",
						headers : {
							"Accept" : "application/json",
							"Content-Type" : "application/json"
						},
						success : function(JSONData, status) {

							var displayValue = "<h6>"

							+ "�� ǰ ��: " + JSONData.product.prodName + "<br/>"
									+ "��   ��: " + JSONData.product.price
									+ "<br/>" + "�� �� ��: "
									+ JSONData.product.manuDate + "<br/>"

									+ "</h6>";

							$("h6").remove();
							$("#" + prodNo + "").html(displayValue);
						}
					});
				});

		//==> userId LINK Event End User ���� ���ϼ� �ֵ��� 
		$(".ct_list_pop td:nth-child(2)").css("color", "red");
		$("h7").css("color", "red");

		$("button.btn.btn-default").on("click", function() {
			fncGetList(1);
		});

		$(".ct_list_pop td:nth-child(2)")
				.on(
						"click",
						function() {
							//var prodNo = $(this).closest("tr").find("input[name='prodNo']").val();
							self.location = "/product/getProduct?menu=${param.menu}&prodNo="
									+ $(this).closest("tr").find(
											"input[name='prodNo']").val();
						});
		
		});
	
</script>

</head>

<body>

	<!-- ToolBar Start /////////////////////////////////////-->
	<jsp:include page="/layout/toolbar.jsp" />
	<!-- ToolBar End /////////////////////////////////////-->

	<!--  ȭ�鱸�� div Start /////////////////////////////////////-->
	<div class="container">

		<div class="page-header text-info">
			<h3>
				<c:if test="${param.menu=='manage'}">
						��ǰ���� 
						</c:if>


				<c:if test="${param.menu=='search'}">
						��ǰ�����ȸ
						</c:if>
			</h3>
		</div>

		<!-- table ���� �˻� Start /////////////////////////////////////-->
		<div class="row">

			<div class="col-md-6 text-left">
				<p class="text-primary">��ü ${resultPage.totalCount } �Ǽ�, ����
					${resultPage.currentPage} ������</p>
			</div>

			<div class="col-md-6 text-right">
				<form class="form-inline" name="detailForm">

					<div class="form-group">
						<select name="searchCondition" class="ct_input_g"
							style="width: 80px">

							<option value="0"
								${ ! empty search.searchCondition && search.searchCondition==0 ? "selected" : "" }>��ǰ��ȣ</option>
							<option value="1"
								${ ! empty search.searchCondition && search.searchCondition==1 ? "selected" : "" }>��ǰ��</option>
							<option value="2"
								${ ! empty search.searchCondition && search.searchCondition==2 ? "selected" : "" }>��ǰ����</option>


						</select>
						<div class="form-group">
							<label class="sr-only" for="searchKeyword">�˻���</label> 
							<input
								type="text" class="form-control" id="searchKeyword" onkeypup="enterkey()"
								name="searchKeyword" placeholder="�˻���" 
								value="${! empty search.searchKeyword ? search.searchKeyword : '' }">
						</div>
						<button type="button" class="btn btn-default" >�˻�</button>
						<input type="hidden" id="currentPage" name="currentPage" value="" />
				</form>
			</div>

		</div>
		<!-- table ���� �˻� Start /////////////////////////////////////-->


		<!--  table Start /////////////////////////////////////-->
		<table class="table table-hover table-striped">

			<thead>
				<tr>
					<th align="center">No</th>
					<th align="left">��ǰ��</th>
					<th align="left">����</th>
					<th align="left">����</th>
					<th align="left">��������</th>
				</tr>
			</thead>

			<tbody>

				<c:set var="i" value="0" />
				<%--�ʱ�ȭ --%>
				<c:forEach var="product" items="${map.list}">
					<%-- ���δ�Ʈ���� ����Ʈ�����ͼ� ����Ʈ��ŭ ������ --%>
					<c:set var="i" value="${ i+1 }" />
					<tr class="ct_list_pop">
						<td align="center">${ i }</td>
						<td align="left">${product.prodName}</td>
						<td align="left">${product.price}</td>
						<td align="left">�Ǹ���</td>
						<td align="left"><input type="hidden" name="prodNo"
							value="${product.prodNo}"> <i
							class="glyphicon glyphicon-ok" id="${product.prodNo}"></i></td>
					</tr>

					<!--  <tr>
						<td id="${product.prodNo}" ></td>
					</tr>-->
				</c:forEach>

			</tbody>

		</table>
		<!--  table End /////////////////////////////////////-->

	</div>
	<!--  ȭ�鱸�� div End /////////////////////////////////////-->


	<!-- PageNavigation Start... -->
	<jsp:include page="../common/pageNavigator_new.jsp" />
	<!-- PageNavigation End... -->

</body>

</html>