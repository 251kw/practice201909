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
		<div style="width: 40%" class="container padding-y-5 text-center">
				検索結果<span class="icon-search"></span>

				<table class="table table-striped table-bordered table-hover text-center">

			        <tr>
			            <th><input type="checkbox" name="allSelect"></th>
			            <th>ユーザー名</th>
			            <th>編集</th>
			            <th>削除</th>
			        </tr>

			        <tr>
			        <c:forEach var="name" items="${unSearchResult}">
			            <td><input type="checkbox" name="Select"></td>
			            <td>${name.userName}</td>
			            <td><form action="UserEdit" method="post"><input type="hidden" name="change" value="edit"><input type="submit" value="編集"></form></td>
			            <td><form action="UserEdit" method="post"><input type="submit" value="削除"></form></td>
					</c:forEach>
			        </tr>
				</table>
		</div>
	</div>
</body>
</html>