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
		<div style="width: 60%" class="container padding-y-5 text-center">
						<form action="./UI" method="post">
			<table style="width: 800px" class="table-striped table-bordered ">
				<tr>
					<td><label><font size="4"></font></label></td>
					<td><label><font size="4">アイコン</font></label></td>
					<td><label><font size="4">ユーザー名</font></label></td>
					<td><label><font size="4">プロフィール</font></label></td>
					<td><label><font size="4">変更</font></label></td>
					<td><label><font size="4">削除</font></label></td>
				</tr>
				<%--繰り返し --%>
				<c:forEach var="user1" items="${searchlist}">
					<tr>
						<td><input type="checkbox" name="loginId" value="${user1.loginId}"></td>
						<td><font size="3"><span
								class="${user1.icon} pe-3x pe-va"></span></font></td>
						<td><label><font size="4">${user1.userName}</font></label></td>
						<td><font size="4">${user1.profile}</font></td>
					</tr>
				</c:forEach>
			</table>
								<%--CUIservletに情報を渡す為にhiddenで値を格納。登録情報変更ボタン --%>
								<%-- <input type="hidden" name="loginId" value="${user1.loginId}"> --%>
								<input class="btn btn-sm " type="submit" value="変更">
							</form>

							<%-- 削除ボタン --%>
							<form action="./D" method="post">
								<input class="btn btn-sm btn-error" type="submit" value="削除">
							</form>

				<%-- リクエストスコープに alert があれば --%>
				<c:if
					test="${requestScope.alert != null && requestScope.alert != ''}">

						<%-- リクエストスコープの alert の値を出力 --%>
						<td class="color-error text-center"><label><font
								size="5"><c:out value="${requestScope.alert}" /></font></label></td>

				</c:if>
			<%--検索画面へ --%>
			<form action="Search.jsp" method="post">
				<input class="btn" type="submit" value="戻る" />
			</form>
		</div>
	</div>
</body>
</html>