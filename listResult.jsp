<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${count gt 0}">
	<div align="center">총 게시물 수 : ${count}</div>
	<br>
	<table border="2">
		<tr>
			<th colspan="6">전체 List</th>
		</tr>
		<tr>
			<th>NO.</th>
			<th>글쓴이</th>
			<th>내용</th>
			<th>평가</th>
			<th>날짜</th>
			<th>IP</th>
		</tr>
		<c:forEach items="${ lists}" var="guest">
			<tr>
				<td>${guest.num}</td>
				<td><a href="javascript:fview(${guest.num})">${guest.name}</a>
				</td>
				<td>${guest.content}</td>
				<td>${guest.grade}</td>
				<td>${guest.created}</td>
				<td>${guest.ipaddr}</td>
			</tr>
		</c:forEach>
	</table>
	<div align="center">
		<!-- 이전 -->
		<c:if test="${startpage > blockpage}">
			<a href="javascript:getData(${startpage-blockpage})">[이전]</a>
		</c:if>
		<!-- 페이지출력 -->
		<c:forEach begin="${ startpage}" end="${endpage }" var="i">
		<c:if test="${currentPage == i }">
			[${i }]
		</c:if>
		<c:if test="${currentPage != i }">
			<a href="javascript:getData(${i })">[${i }]</a>
		</c:if>
		</c:forEach>
		<!-- 다음 -->
		<c:if test="${endpage < totpage}">
			<a href="javascript:getData(${endpage+1})">[다음]</a>
		</c:if>
	</div>
</c:if>