<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>登録内容確認</title>
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
			<strong class="color-main">登録内容確認</strong>
		</div>
	</div>
	<%--文字化け防止 --%>
	<% request.setCharacterEncoding("UTF-8"); %>
	<div class="padding-y-5">
	<div style="width: 40%" class="container padding-y-5">
		<form action="RegisterLogic" method="post">
		<%--入力された値をパラメータで取得し表示 --%>
			<table border="1" class="table">
				<tr>
					<th><label for="loginId"><span class="icon-headphones pe-2x pe-va"></span>&nbsp;ログインID</label></th>
					<td>${param.loginId}</td>
				</tr>

				<tr>
					<th><label for="userName"><span class="icon-smile pe-2x pe-va"></span>&nbsp;名前</label></th>
					<td>${param.userName}</td>
				</tr>

				<tr>
					<th><label for="pass"><span class="icon-note pe-2x pe-va"></span>&nbsp;パスワード</label></th>
					<td>${param.password}</td>
				</tr>
				<tr>
					<th><span class="icon-users pe-2x pe-va"></span>&nbsp;アイコン</th>
					<td><span class="${param.icon} pe-5x pe-va"></span></td>

				</tr>
				<tr>
					<th><span class="icon-note2 pe-2x pe-va"></span>&nbsp;自己紹介</th>
					<td>${param.profile}</td>
				</tr>
				<tr>
					<%--Hiddenでユーザー情報をRegisterLogicに渡す--%>
					<td colspan="2" class="text-center">
					<input type="hidden" name="loginId" value="${param.loginId}">
					<input type="hidden" name="password" value="${param.password}">
					<input type="hidden" name="userName" value="${param.userName}">
					<input type="hidden" name="icon" value="${param.icon}">
					<input type="hidden" name="profile" value="${param.profile}">
					<input type="submit" value="登録" class="btn btn-warning"></td>
				</tr>
				</table>
			</form>
			<%--戻るボタンを押したとき、Register.jspに転送し、Hiddenでパラメータを渡す --%>
			<form action="Register.jsp" class="text-center">
				<input type="hidden" name="loginId" value="${param.loginId}">
				<input type="hidden" name="password" value="${param.password}">
				<input type="hidden" name="userName" value="${param.userName}">
				<input type="hidden" name="icon" value="${param.icon}">
				<input type="hidden" name="profile" value="${param.profile}">
				<input type="submit" value="戻る" class="btn">
			</form>
		</div>
	</div>
</body>
</html>