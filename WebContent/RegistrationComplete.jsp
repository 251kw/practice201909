<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Shouter -ユーザー登録</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
</head>
<body>
	
	<%--登録完了を表示 --%>
	<div class="bg-success padding-y-5">
		<div class="container padding-y-5 text-center">
			<h1>登録完了しました</h1>
		</div>
	</div>
	<%--ログイン画面に戻る --%>
	<form action="index.jsp" method="post">
		<div class="padding-y-5 text-center">
			<input class="btn" type="submit" value="トップページに戻る">
		</div>
	</form>
</body>
</html>