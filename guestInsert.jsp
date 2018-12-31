<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script>
$(document).ready(function(){
	$("#send").click(function(){
		var name = $("#name").val();
		var content = $("#content").val();
		var grade = $("input:radio[name=grade]:checked").val();
		var postString = "name="+name+"&content="+content+"&grade="+grade;
		$.ajax({
			type : "post",
			url : "create",
			data : postString,
			success : function(data){
// 				alert(data);
				$("#results").html(data);
			},
			beforeSend : showRequest
		});
	});//send
	$("#btnSearch").click(function(){
		getSearch(1,$("#field").val(),$("#word").val());
	});
	getData(1);
});
//검색찾기
function getSearch(pageNum,field,word){
	$("#results").load("search",
			{"pageNum":pageNum,"field":field,"word":word},
			function(data){
				$("#result").html(data);
			});
}
// guestInsert.jsp가 실행될때 로드됨
function getData(pageNum){
	$("#results").load("list",
								{"pageNum":pageNum},
									function(data){
										$("#results").html(data);
	});
}
//focus 커서이동 메서드
function showRequest(){
	if($("#name").val()==""){
		alert("글쓴이를 입력해주세요.");
		$("#name").focus();
		return false;
	}
	if($("#content").val()==""){
		alert("글쓴이를 입력해주세요.");
		$("#content").focus();
		return false;
	}
	if($("input:radio[name=grade]:checked").length == 0){
		alert("평가를 해주세요.");
		return false;
	}
}
//글자수 체크하는 함수
function textCount(obj,target){
	var len = obj.value.length;//입력한 글자수
	if(obj.size<len){//글쓴이 : 20, 내용  : 70
		obj.value = "";
		alert("글자 수 초과");
		return;
	}
	$("#"+target).text(len); //target 영역에 글자수 출력
}
//상세보기
function fview(num){
	$.get("view",
			{"num": num,},
			function(data){
				$("#view").html(data);
			});
}
</script>
</head>

<body>
<div align = "right">
<c:if test="${sessionScope.mdto == null }">
<a href = "login.jsp">로그인</a>
</c:if>
<c:if test="${sessionScope.mdto != null }">
${sessionScope.mdto.name }님 반갑습니다.<br>
<a href = "logout">로그아웃</a>
</c:if>
</div>
	<form>
		<table>
			<tr>
				<td align="center">글쓴이</td>
				<td>
				<input type="text" id="name" size=20 name="name" onkeyup = "textCount(this,'nameCount')">
					*20글자이내
					 (<span id="nameCount" style="color: red;">0</span>)
				</td>
			</tr>
			<tr>
			<td align="center">내 용</td>
				<td>
				<input type="text" id="content" size=70 name="content" onkeyup = "textCount(this,'contentCount')">
					*70글자이내
					 (<span id="contentCount" style="color: red;">0</span>)
				</td>
			</tr>
			<tr>
			<td align="center">평 가</td>
			<td>
			<input type = "radio" name = "grade" value = "excellent" checked = "checked">아주잘함(excellent)
			<input type = "radio" name = "grade" value = "good">잘함(good)
			<input type = "radio" name = "grade" value = "normal">보통(normal)
			<input type = "radio" name = "grade" value = "fail">노력(fail)
			</td>
			</tr>
			<tr>
			<td colspan="2">
			<input type = "button" value = "입력" id = "send">
			</td>
			</tr>
		</table>
	</form>
	<div align = "right">
	<form name = "search" id = "search">
	<select name = "field" id = "field">
		<option value = "name">이름</option>
		<option value = "content">내용</option>
	</select>
	<input type = "text" name = "word" id = "word">
	<input type = "button" value = "찾기" id = "btnSearch">
	</form>
	</div>
	<br><br><br><br>
	<!-- 	방명록 결과 출력 부분 -->
	<div id = "results" align = "center"></div>
	<br><br><br><br>
	<!-- 	상세보기 출력 부분 -->
	<div id = "view" align = "center"></div>
</body>
</html>