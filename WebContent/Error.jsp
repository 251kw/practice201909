<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>Shouter - 会員検索 -</title>
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
	<br>
	<br>
	<div class="padding-y-5 text-left">
		<div style="width: 50%" class="container padding-y-5 text-center">
			<c:if
				test="${requestScope.alert != null && requestScope.alert != ''}">

				<%-- リクエストスコープの alert の値を出力 --%>
				<div class="color-error text-center"><c:out value="${requestScope.alert}" />
				</div>
				<input type="hidden" name="userDelete" value="userDelete">
			</c:if>

			<br>
			<br>

			<!-- 戻るボタンを押したら1ページ前に戻る -->
			<form>
				<input type="button" class="btn" value="戻る" onClick="history.back()"><br>
				<a href="Top" class="btn btn-warning">トップへ戻る</a>
			</form>

		</div>
	</div>
</body>
</html>