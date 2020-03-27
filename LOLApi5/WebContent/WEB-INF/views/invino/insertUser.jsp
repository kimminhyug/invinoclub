<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>first</title>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<style>
input,select {
	height : 30px;
}
</style>

<script>
	function insertBefore() {
		alert(1);

		
		
	}
	function si() {
		var name = document.getElementById('name').value;
		var auth = getRadioChck(document.getElementsByName('authority'));
		var main = document.getElementsByName('main')[0].value;
		var number = document.getElementsByName('number')[0].value;

		if(name == null || name =='') {
			alert("닉네임 필수");
			return false;
		}
		if(auth == null || auth =='') {
			alert("유저 관리자중 선택 필수");
			return false;
		}
		if(main != null || main != '') {
			if(number != null || number != '') {
				document.getElementById('tier').value = main + number;
			
			}
		}
	}
function getRadioChck(name) {
	var value = '';
	for(var i=0; i<name.length; i++) {
	    if(name[i].checked) {
	    	value = name[i].value;
	    }
	}
	return value;
}
</script>
</head>
<body>
<h2>유저 추가</h2>
<form action="insertUserProcess.do" onsubmit="return si();" method="GET">
<input type="submit" value="추가" >
<table style="border:1px solid #ccc">
	<tr>
		<td>닉네임</td>
		<td><input type="text" name="name" id="name"/></td>
	</tr>
	<tr>
		<td>tier</td>
		<td>
		<input type="hidden" name="tier" id="tier" />
			<select name="main" >
				<option value=""></option>
				<option value="아이언">아이언</option>
				<option value="브론즈">브론즈</option>
				<option value="실버" >실버</option>
				<option value="도금" >도금</option>
				<option value="플레" >플레</option>
				<option value="다이아" >다이아</option>
				<option value="기타" >기타</option>
				<option value="언랭" >언랭</option>
			</select>
			<select name="number">
				<option value=""></option>
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="3" >3</option>
				<option value="4" >4</option>
				<option value="5" >5</option>
			</select>
		</td>
	</tr>
	<tr>
		<td>line</td>
		<td>
		
			<input type="checkbox" name="line" value="TOP">TOP
			<input type="checkbox" name="line" value="JUNGLE">JUNGLE
			<input type="checkbox" name="line" value="MID">MID
			<input type="checkbox" name="line" value="SUPPOT">SUPPOT
			<input type="checkbox" name="line" value="AD">AD
			<input type="checkbox" name="line Wind" value="Knife Wind">Knife Wind	
		</td>
	</tr>
	<tr>
		<td>직책</td>
		
		<td><input type="radio" name="authority" value="유저" checked>유저
			<input type="radio" name="authority" value="관리자">관리자
		</td>
	</tr>
	<tr>
		
	</tr>
	
	
	
</table>
</form>

</body>
</html>


