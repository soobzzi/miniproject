<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	

	<c:if test = "${resultPage.beginUnitPage > 1}">
		
			<a href="javascript:fncGetList('${'1'}')">[첫페이지]</a>
	</c:if>
	
	<c:if test = "${resultPage.currentPage > 1 }">
			<a href="javascript:fncGetList('${ resultPage.currentPage-1}')">◁</a>
	</c:if>
	
	<c:forEach var="i"  begin="${resultPage.beginUnitPage}" end="${resultPage.endUnitPage}" step="1">
		<c:if test="${i == resultPage.currentPage }">
			<b><a href="javascript:fncGetList('${ i }');">${ i }</a></b>
		</c:if>
		<c:if test="${i != resultPage.currentPage }">
			<a href="javascript:fncGetList('${ i }');">${ i }</a>
		</c:if>	
	</c:forEach>
	
	<c:if test = "${resultPage.currentPage < resultPage.endUnitPage}">
			<a href="javascript:fncGetList('${ resultPage.currentPage+1}')">▷</a>
	</c:if>
	
	<c:if test = "${resultPage.currentPage < resultPage.maxPage}">
			<a href="javascript:fncGetList('${resultPage.maxPage}')">[마지막페이지]</a>
	</c:if>
	
		