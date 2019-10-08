<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>Shouter - 叫び編集確認画面 -</title>
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
		<form action="ShoutEditLogic" method="post">
		<%--入力された値をパラメータで取得し表示 --%>
		下記内容で変更します
			<table border="1" class="table">
				<tr>
					<th><label for="userName"><span class="icon-smile pe-2x pe-va"></span>&nbsp;名前</label></th>
					<td>${userName}</td>
				</tr>
				<tr>
					<th><span class="icon-users pe-2x pe-va"></span>&nbsp;アイコン</th>
					<td><span class="${icon} pe-5x pe-va"></span></td>

				</tr>
				<tr>
					<th><span class="icon-speaker pe-2x pe-va"></span>&nbsp;叫び</th>
					<td>${writing}</td>
				</tr>
				<tr>
					<%--Hiddenでユーザー情報をChangeLogicに渡す--%>
					<td colspan="2" class="text-center">
					<input type="hidden" name="loginId" value="${loginId}">
					<input type="hidden" name="userName" value="${userName}">
					<input type="hidden" name="icon" value="${icon}">
					<input type="hidden" name="writing" value="${writing}">
					<input type="hidden" name="shoutsId" value="${shoutsId}">

					<input type="submit" value="OK" class="btn btn-success"></td>
				</tr>
				</table>
			</form>

			<%--戻るボタンを押したとき、Change.jspに転送し、Hiddenでパラメータを渡す --%>
			<input type="button" class="btn" value="戻る" onClick="history.back()"><br>
			<a href="Top" class="btn btn-warning">トップへ戻る</a>
		</div>
	</div>
</body>
</html>
