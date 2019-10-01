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
<%-- 文字化け対策 --%>
	<%
		request.setCharacterEncoding("UTF-8");
	%>
	<div class="bg-success padding-y-5">
		<div class="container padding-y-5 text-center">
			<h1>検索結果</h1>
		</div>
	</div>

	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-center">
			<table style="width: 600px" class="table">
				<%--繰り返し --%>
				<c:forEach var="user" items="${searchlist}">
					<tr>
					    <td><label><input type="checkbox"><font size="5">${user.userName}</font></label></td>
						<td><form action="./CUI" method="post">
						<%--CUIservletに情報を渡す為にhiddenで値を格納。登録情報変更ボタン --%>
								<input type="hidden" name="userName" value="${user.userName}">
								<input class="btn btn-rigth" type="submit" value="登録情報変更">
							</form>
						</td>
						<td>
						<%-- 削除ボタン --%>
							<form action="./UD" method="post">
								<input class="btn btn-left" type="submit" value="削除">
							</form>
						</td>
					</tr>
				</c:forEach>
			</table>
			<%--検索画面へ --%>
			<form action="Search.jsp" method="post">
				<input class="btn" type="submit" value="戻る" />
			</form>
		</div>
	</div>
</body>
</html>