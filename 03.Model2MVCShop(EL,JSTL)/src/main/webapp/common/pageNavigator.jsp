<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	

	<c:if test = "${resultPage.beginUnitPage > 1}">
		
			<a href="javascript:fncGet${listType}List('${'1' }')">[첫페이지]</a>
	</c:if>
	
	<c:if test = "${resultPage.currentPage > 1 }">
			<a href="javascript:fncGet${listType}List('${ resultPage.currentPage-1}')">◁</a>
	</c:if>
	
	<c:forEach var="i"  begin="${resultPage.beginUnitPage}" end="${resultPage.endUnitPage}" step="1">
			<a href="javascript:fncGet${listType}List('${ i }');">${ i }</a>
	</c:forEach>
	
	<c:if test = "${resultPage.currentPage < resultPage.endUnitPage}">
			<a href="javascript:fncGet${listType}List('${ resultPage.currentPage+1}')">▷</a>
	</c:if>
	
	<c:if test = "${resultPage.endUnitPage < resultPage.maxPage}">
			<a href="javascript:fncGet${listType}List('${resultPage.maxPage}')">[마지막페이지]</a>
	</c:if>
	

		
		
		<%-- <c:if test = "${resultPage.beginUnitPage > 1}">
			<a href="/listProduct.do?page=1&menu=${param.menu}">[첫페이지]</a>
		</c:if>
		
	
		<c:if test = "${resultPage.currentPage > 1 }">
				<a href="/listProduct.do?page=${resultPage.currentPage-1}&menu=${param.menu}"> ◁ </a>
		</c:if>
		

		<c:forEach var = "i" begin="${resultPage.beginUnitPage}" end="${resultPage.endUnitPage}" step="1">
		
			<c:choose>
				<c:when test ="${empty param.menu}">
					<a href="/listUser.do?page=${i}">${i}</a>
				</c:when>
				
				<c:otherwise>
				<a href="/listProduct.do?page=${i}&menu=${param.menu}&searchCondition=${search.searchCondition}&searchKeyword=${search.searchKeyword}">${i}</a>
				</c:otherwise>
			</c:choose>
				
		</c:forEach>
		
		
		<c:if test = "${resultPage.currentPage < resultPage.endUnitPage}">
			<a href="/listProduct.do?page=${ resultPage.currentPage+1}&menu=${param.menu}">▷</a>
		</c:if>
			
		<c:if test = "${resultPage.endUnitPage < resultPage.maxPage}">
			<a href="/listProduct.do?page=${resultPage.maxPage}&menu=${param.menu}">[마지막페이지]</a>
		</c:if>
	--%>