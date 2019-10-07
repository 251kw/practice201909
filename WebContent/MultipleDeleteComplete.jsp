<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>Shouter - ユーザー削除完了 -</title>
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
	<%
		request.setCharacterEncoding("UTF-8");
	%>
	<div class="padding-y-5">
		<div style="width: 40%" class="container padding-y-5 text-center">
		<jsp:useBean id="deleteList" scope="session" type="java.util.ArrayList<dto.UserDTO>" />
			<form action="Top" method="post">
				<%--入力された値をパラメータで取得し表示 --%>
				下記ユーザーを削除しました


				<table class="table">
				<c:forEach var="user" items="${deleteList}">
					<tr>
						<td>${user.userName}</td>
					</tr>
				</c:forEach>
					<tr>
						<td colspan="2" class="text-center">
							<input type="submit"value="トップへ戻る" class="btn btn-warning">
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>
