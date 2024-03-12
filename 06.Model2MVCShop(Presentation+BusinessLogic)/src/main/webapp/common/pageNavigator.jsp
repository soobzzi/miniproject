<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	

	<c:if test = "${resultPage.beginUnitPage > 1}">
		
			<a href="javascript:fncGet${listType}List('${'1'}')">[ù������]</a>
	</c:if>
	
	<c:if test = "${resultPage.currentPage > 1 }">
			<a href="javascript:fncGet${listType}List('${ resultPage.currentPage-1}')">��</a>
	</c:if>
	
	<c:forEach var="i"  begin="${resultPage.beginUnitPage}" end="${resultPage.endUnitPage}" step="1">
		<c:if test="${i == resultPage.currentPage }">
			<b><a href="javascript:fncGet${listType}List('${ i }');">${ i }</a></b>
		</c:if>
		<c:if test="${i != resultPage.currentPage }">
			<a href="javascript:fncGet${listType}List('${ i }');">${ i }</a>
		</c:if>	
	</c:forEach>
	
	<c:if test = "${resultPage.currentPage < resultPage.endUnitPage}">
			<a href="javascript:fncGet${listType}List('${ resultPage.currentPage+1}')">��</a>
	</c:if>
	
	<c:if test = "${resultPage.endUnitPage < resultPage.maxPage}">
			<a href="javascript:fncGet${listType}List('${resultPage.maxPage}')">[������������]</a>
	</c:if>
	
		