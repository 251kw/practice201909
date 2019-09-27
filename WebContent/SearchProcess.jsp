<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>検索結果</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
</head>
<body>
<div class="padding-y-5 text-center">
	<table style="width: 400px" class="table">
		<%--繰り返し --%>
		<tr><c:forEach var="user" items="${list}" varStatus="status">${status.count}:${user.userName}
<form action="ChangeUserInformation.jsp" method="post">
				<input class="btn" type="submit" value="変更" />
			</form>
			<form action="./UD" method="post">
				<input class="btn" type="submit" value="削除" />
			</form>
			<br>
		</c:forEach></tr>
	</table>
	</div>
</body>
</html>