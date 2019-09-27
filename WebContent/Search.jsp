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
	<div class="bg-success padding-y-5">
		<div class="container padding-y-5 text-center">
			<h1>
				Shouter&nbsp;<span class="icon-speaker"></span>
			</h1>
		</div>
	</div>

	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-left">
			<strong class="color-main">会員情報の検索</strong>
		</div>
	</div>
	<div class="padding-y-5 text-left">
		<div style="width: 40%" class="container padding-y-5 text-left">
			<form action="SeachLogic" method="get">
				<table class="table">
					検索条件<span class="icon-search"></span>
					<tr><td>ユーザー名</td><td><input type="text" size="40" name="userNameSearch"></td></tr>
					<tr><td>ユーザーID</td><td><input type="text" size="40" name="loginIdSearch"></td></tr>
					<tr><td>自己紹介</td><td><input type="text" size="40" name="profileSearch"></td></tr>
				</table>
				<div class="padding-y-5 text-right"><input class="btn btn-success" type="submit" value="検索" /></div>
			</form>
		</div>
	</div>
</body>
</html>