<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>first</title>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<STYLE>
TD,TH {
	text-align : center;
}
</STYLE>
</head>
<body>
<h2>유저 목록</h2>
<input type="button" value="기존유저추가" onclick='location.href="user.do"'>
<form action="attendanceCheck.do" onsubmit="return updateBeforeCheck();">
	<input type="submit" value="게임 전적 업데이트" >
	<input type="date" name="date" id="date" value="">
	
	<table style="border:1px solid #ccc">
		<colgroup>
			<col width="10%"/>
			<col width="*"/>
			<col width="15%"/>
			<col width="20%"/>
		</colgroup>
		<thead>
			<tr>
				<th scope="col">닉네임</th>
				<th scope="col">티어</th>
				<th scope="col">라인</th>
				<th scope="col">직책</th>
				<th scope="col">저번주 클럽원과 게임체크</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${fn:length(list) > 0}">
					<c:forEach items="${list }" var="row">
						<tr>
							<td>${row.name }</td>
							<td>${row.tier }</td>
							<td>${row.line }</td>
							<td>${row.authority }</td>
							<td>${row.playCheck }</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="4">조회된 결과가 없습니다.</td>
					</tr>
				</c:otherwise>
			</c:choose>
			
		</tbody>
	</table>
</form>
<script>
	function updateBeforeCheck() {
		var date = document.getElementById('date').value;
		if(date == null || date == "") {
			alert('날짜 미입력');
			return false;
		}
	
	}
</script>
</body>
</html>


