<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>Shouter - 叫び編集 -</title>
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

	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-left">
			<strong class="color-main">叫び編集</strong>
		</div>
	</div>

	<div class="padding-y-5">
		<div style="width: 40%" class="container padding-y-5">
			<form action="ShoutEditCheck" method="post">
				<table border="1" class="table">
				<c:forEach var="shout" items="${shout}">
					<tr>
						<th><span class="icon-smile pe-2x pe-va"></span>&nbsp;名前</th>
						<td>${shout.userName}</td>
					</tr>

					<tr>
						<th><span class="icon-users pe-2x pe-va"></span>&nbsp;アイコン</th>
						<td><span
							class="${shout.icon} pe-3x pe-va"></span></td>
					</tr>
					<tr>
						<th><span class="icon-speaker pe-2x pe-va"></span>&nbsp;叫び</th>
						<td><textarea rows="5" cols="20" name="writing"
								class="form-control">${shout.writing}</textarea></td>
					</tr>
					<tr>
						<td colspan="2" class="text-center">
						<input type="hidden" name="shoutsId" value="${shout.shoutsId}">
						<input type="hidden" name="userName" value="${shout.userName}">
						<input type="hidden" name="icon" value="${shout.icon}">
						<input type="submit" value="確認" class="btn btn-success"></td>
					</tr>
					</c:forEach>
					<%--アラートがあるか確認 --%>
					<c:if
						test="${requestScope.alert != null && requestScope.alert != ''}">
						<tr>
							<%-- リクエストスコープの alert の値を出力 --%>
							<td colspan="2" class="color-error text-left"><c:out
								value="${requestScope.alert}" /></td>
						</tr>
						<tr>

						</tr>
					</c:if>
				</table>
			</form>

			<!-- 戻るボタンを押したら1ページ前に戻る -->
			<form>
				<input type="button" class="btn" value="戻る" onClick="history.back()">
			</form>

			</div>
		</div>
</body>
</html>