<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>Shouter - ユーザー情報変更確認画面 -</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
</head>
<body>

	<%
		request.setCharacterEncoding("UTF-8");
	%>
	<div class="bg-success padding-y-5">
		<div class="container padding-y-5 text-center">
			<h1>
				Shouter&nbsp;<span class="icon-speaker"></span>
			</h1>
		</div>
	</div>
	<% request.setCharacterEncoding("UTF-8"); %>
	<div class="padding-y-5">
	<div style="width: 40%" class="container padding-y-5 text-center">
		<form action="ChangeLogic" method="post">
		<%--入力された値をパラメータで取得し表示 --%>
		下記内容で変更します
			<table border="1" class="table">
				<tr>
					<th><label for="loginId"><span class="icon-headphones pe-2x pe-va"></span>&nbsp;ログインID</label></th>
					<td>${changeUserLoginId}</td>
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
					<%--Hiddenでユーザー情報をChangeLogicに渡す--%>
					<td colspan="2" class="text-center">
					<input type="hidden" name="changeUserLoginId" value="${changeUserLoginId}">
					<input type="hidden" name="userId" value="${userId}">
					<input type="hidden" name="loginId" value="${loginId}">
					<input type="hidden" name="password" value="${password}">
					<input type="hidden" name="userName" value="${userName}">
					<input type="hidden" name="icon" value="${icon}">
					<input type="hidden" name="profile" value="${profile}">

					<input type="submit" value="OK" class="btn btn-warning"></td>
				</tr>
				</table>
			</form>

			<%--戻るボタンを押したとき、Change.jspに転送し、Hiddenでパラメータを渡す --%>
			<input type="button" class="btn" value="戻る" onClick="history.back()">
		</div>
	</div>
</body>
</html>
