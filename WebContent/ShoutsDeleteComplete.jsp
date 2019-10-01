<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>Shouter - 叫び削除完了 -</title>
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
			<form action="Top" method="post">
				<%--入力された値をパラメータで取得し表示 --%>
				<br><br>
				叫びをを削除しました
				<br><br><br>
				<input type="hidden" name="nowLoginId" value="${nowLoginId}">
				<input type="hidden" name="nowLoginUser" value="${nowLoginUser}">
				<input type="hidden" name="nowLoginUserId" value="${nowLoginUserId}">
				<input type="hidden" name="nowLoginProfile" value="${nowLoginProfile}">
				<input type="hidden" name="nowLoginIcon" value="${nowLoginIcon}">
				<input type="hidden" name="nowLoginPassword" value="${nowLoginPassword}">

				<input type="submit"value="トップへ戻る" class="btn btn-warning">
			</form>
		</div>
	</div>
</body>
</html>
