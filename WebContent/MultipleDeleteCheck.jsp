<%@ page pageEncoding="UTF-8"%>
<%@ page import="dto.UserDTO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>Shouter - ユーザー情報削除確認画面 -</title>
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
			<form action="MultipleDelete" method="post">
				<%--入力された値をパラメータで取得し表示 --%>
				下記ユーザーを削除します

					<c:if
						test="${requestScope.alert != null && requestScope.alert != ''}">

							<%-- リクエストスコープの alert の値を出力 --%>
							<div  class="color-error text-center">${user.userName}さんを<c:out
									value="${requestScope.alert}" /></div>
									<input type="hidden" name="userDelete" value="userDelete">
							</c:if>
				<c:forEach var="user" items="${deleteList}">
					<table class="table table-bordered">

						<tr>
							<td rowspan="2" class="text-center"><span
								class="${user.icon} pe-3x pe-va"></span></td>
							<td width="256">${user.userName}</td>
						</tr>
						<tr>
							<td colspan="2">${user.profile}</td>
						</tr>
					</table>
				</c:forEach>
				<input type="submit" value="削除" class="btn btn-warning">
				<%--HiddenでloginIdをDeleteに渡す--%>
			</form>

			<%--戻るボタンを押したら1つ前にページに戻る --%>
			<form>
				<a href="SeachLogic" class="btn">戻る</a><br>
				<a href="Top" class="btn btn-warning">トップへ戻る</a>
			</form>
		</div>
	</div>
</body>
</html>
