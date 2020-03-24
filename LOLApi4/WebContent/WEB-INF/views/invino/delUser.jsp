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

<form action="userDelP.do" onsubmit="">
	<input type="submit" value="일괄 삭제" >

	
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
							<td><input type="checkbox" name="delUser" value=${row.name }>삭제</td>
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
	function delProc(e) {
		console.log(e);
	}
	function updateBeforeCheck() {
		var date = document.getElementById('date').value;
		var date2 = document.getElementById('date2').value;
		var bool = false;
		if(date == null || date == "") {
			bool = true;
		}
		if(date2 == null || date2 == "") {
			bool = true;
		}
		if(bool == true) {
			alert("You can only use it every seven days");
			return false;
		}
	}
	function changeDate(e) {
		
		var date = document.getElementById('date');
		var date2 = document.getElementById('date2');
		var getDateValue;
		if(e.id == "date") {
			getDateValue = getDATE(e.value,6);
			var m =((getDateValue.getMonth() + '').length == 1)?0+(getDateValue.getMonth() +1+ ''):(getDateValue.getMonth() +1+ '');
			var d =((getDateValue.getDate() + '').length == 1)?0+(getDateValue.getDate() + ''):(getDateValue.getDate() + '');
			date2.value = getDateValue.getFullYear()+"-"+m+"-"+d;
		} else if(e.date == "date2") {
			getDateValue = getDATE(e.value,-6);
			var m =((getDateValue.getMonth() + '').length == 1)?0+(getDateValue.getMonth() +1+ ''):(getDateValue.getMonth() +1+ '');
			var d =((getDateValue.getDate() + '').length == 1)?0+(getDateValue.getDate() + ''):(getDateValue.getDate() + '');
			date.value = getDateValue.getFullYear()+"-"+m+"-"+d;
		}
	}
	function getDATE(date,calc) {
		var yyyy = date.split("-")[0];
	    var mm = date.split("-")[1];
	    var dd = date.split("-")[2];
	    calc = calc*1;
	    dd = dd*1;
	    dd = dd+calc;
	    var _date = new Date(yyyy, mm-1, dd);
	    console.log(_date);
		return _date;
	}
</script>
</body>
</html>


