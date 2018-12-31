<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>

</script>
</head>
<body>
<form action="login" method = "post">
ID : <input type = "text" name = "userid" id = "userid"><br>
PWD : <input type = "password" name = "pwd" id = "pwd"><br>
<input type = "submit" value = "로그인">
<input type = "reset" value = "취소">
</form>
<br><br>
<c:if test="${msg == '회원이 맞습니다.' }">
 <c:redirect url="guestInsert.jsp">
 	<c:param name="id" value = "${id }"></c:param>
 </c:redirect>
</c:if>
<c:if test="${msg != '회원이 맞습니다.' }">
${msg}
</c:if>
</body>
</html>