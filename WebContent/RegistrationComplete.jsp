<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>会員登録完了</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
</head>
<body>
	<div class="bg-success padding-y-5">
		<div class="container padding-y-5 text-center">
			<h1>
				Shouter&nbsp;<span class="icon-speaker"></span>
			</h1>
		</div>
	</div>
	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-center">
			<strong class="color-main">会員登録完了</strong>
		</div>
	</div>

	<div class="padding-y-5">
	<div style="width: 40%" class="container padding-y-5">
		<form action="login" method="get">
			<table border="1" class="table">
				<% request.setCharacterEncoding("UTF-8");  %>
				<tr>
					<td>ようこそ ${userName} さん
				</tr>

				<tr>
					<%--Hiddenで値をLoginに渡す --%>
					<td colspan="2" class="text-center">
					<input type="hidden" name="loginId" value="${loginId}">
					<input type="hidden" name="password" value="${password}">
					<input type="hidden" name="icon" value="${icon}">
					<input type="hidden" name="userName" value="${userName}">
					<input type="hidden" name="profile" value="${profile}">
					<input type="hidden" name="Compleat" value="Compleat">
					<input type="submit" value="トップへ" class="btn"></td>
				</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>
