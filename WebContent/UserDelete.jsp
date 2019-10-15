<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="dto.UserDTO"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ユーザー削除</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
</head>
<body>
	<%-- 文字化け対策 --%>
	<%
		request.setCharacterEncoding("UTF-8");

	request.getAttribute("loginId2");
		ArrayList<UserDTO> searchlist = (ArrayList<UserDTO>) request.getAttribute("searchlist");
	%>
	<div class="bg-success padding-y-5">
		<div class="container padding-y-5 text-center">
			<h1>ユーザー削除</h1>
		</div>
	</div>
	<div class="padding-y-5">
		<div style="width: 60%" class="container padding-y-5 text-left">
			<strong>ユーザー情報</strong>
		</div>
	</div>
	<div class="padding-y-5">
		<div style="width: 60%" class="container padding-y-5">
			<%-- action 属性にサーブレットを指定 --%>
			<form action="./UD" method="post">
				<%-- 登録情報 --%>
				<table style="width: 800px" class="table-striped table-bordered ">
					<tr>
						<td><label><font size="4">アイコン</font></label></td>
						<td><label><font size="4">ユーザー名</font></label></td>
						<td><label><font size="4">ログインID</font></label></td>
						<td><label><font size="4"> パスワード</font></label></td>
						<td><label><font size="4">プロフィール</font></label></td>
					</tr>
					<%-- deleatelistのuserDTOをforEachで取得 --%>
					<c:forEach var="user3" items="${deletelist}">
						<tr>
							<td><font size="3"><span
									class="${user3.icon} pe-3x pe-va"></span></font></td>

							<%-- userNameを取得し表示 --%>
							<td><font size="4">${user3.userName}</font></td>
							<%-- loginIdを取得し表示 --%>
							<td><font size="4">${user3.loginId}</font></td>
							<%-- passwordを取得し表示 --%>
							<td><font size="4">${user3.password}</font></td>
							<%-- profileを取得し表示 --%>
							<td><font size="4">${user3.profile}</font></td>
						</tr>
						<%-- forEachの中身をhiddenで送信--%>
						<td><input type="hidden" name="loginId"
							value="${user3.loginId}"> <input type="hidden"
							name="password" value="${user3.password}"> <input
							type="hidden" name="userName" value="${user3.userName}">
							<input type="hidden" name="icon" value="${user3.icon}"> <input
							type="hidden" name="profile" value="${user3.profile}"></td>
					</c:forEach>
				</table>
				<c:forEach var="loginId2" items="${loginId2}">
					<input type="hidden" name="loginId2" value="${loginId2}">
				</c:forEach>
				<br>
				<tr>
					<%-- 内容確認 --%>
					<td><font size="4">本当に削除しますか？</font></td>
					<%-- hiddenで情報を送信 --%>
					<%-- 確認ボタン --%>
					<td><button type="submit" name="btn"
							class="btn btn-right btn-sm" value="はい">はい</button>
						<button type="submit" name="btn" class="btn btn-left btn-sm"
							value="いいえ">いいえ</button></td>
				</tr>
			</form>
		</div>
	</div>
</body>
</html>