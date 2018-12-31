<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <script>
    function deleteBtn(){
    	if(confirm("정말삭제하시겠습니까?")){
    		location.href = "delete?num=${dto.num}";
    	}
    }
    </script>
<form id = "frm">
<table border = "2">
	<tr>
			<th colspan = "2">상세 List</th>
	</tr>
	<tr>
		<th>NO.</th>
		<td><input type="text" id="num" name="num" value="${dto.num}"></td>
	</tr>
	<tr>
		<th>글쓴이</th>
		<td><input type="text" id="name" name="name" value="${dto.name}"></td>
	</tr>
	<tr>
		<th>내용</th>
		<td><input type="text" id="content" name="content" value="${dto.content}"></td>
	</tr>
	<tr>
		<th>평가</th>
		<td><input type="text" id="grade" name="grade" value="${dto.grade}"></td>
	</tr>
	<tr>
		<th>날짜</th>
		<td><input type="text" id="created" name="created" value="${dto.created}"></td>
	</tr>
	<tr>
		<th>IP</th>
		<td><input type="text" id="ipaddr" name="ipaddr" value="${dto.ipaddr}"></td>
	</tr>
	<c:if test="${sessionScope.mdto.name == dto.name}">
	<tr>
		<td colspan = "2" align = "center">
		<input type="button" onclick ="deleteBtn()" name="delete" value="삭제">
		</td>
	</tr>
	</c:if>
</table>
</form>