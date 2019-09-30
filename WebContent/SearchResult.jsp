<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>Shouter - 検索結果 -</title>
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

	<div class="padding-y-5 text-left">
		<div style="width: 40%" class="container padding-y-5">
				検索結果<span class="icon-search"></span>

				<table border="1" class="table text-center">

			        <tr>
			            <th><div class="text-center"><h5><input type="checkbox" name="allSelect"></h5></div></th>
			            <th><div class="text-center"><h5>ユーザー名</h5></div></th>
			            <th><div class="text-center"><h5>ログインID</h5></div></th>
			            <th><div class="text-center"><h5>編集</h5></div></th>
			            <th><div class="text-center"><h5>削除</h5></div></th>
			        </tr>

			        <c:forEach var="name" items="${searchResult}">
			        <tr>
			            <td><input type="checkbox" name="Select[]"></td>
			            <td>${name.userName}</td>
			            <td>${name.loginId}</td>
			            <td><form action="UserEdit" method="post"><input type="hidden" name="edit" value="change">
			            <input type="hidden" name="loginId" value="${name.loginId}">
			            <input type="submit" value="編集"></form></td>
			            <td><form action="UserEdit" method="post"><input type="hidden" name="edit" value="delete">
			            <input type="hidden" name="loginId" value="${name.loginId}">
			            <input type="submit" value="削除"></form></td>
			        </tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</body>
</html>