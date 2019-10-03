<%@page import="java.util.ArrayList, dto.UserDTO"%>
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
		<div style="width: 40%" class="container padding-y-5">
				検索結果<span class="icon-search text-cenyer"></span><br>

			  <form action="UserEdit" method="post">
				<table border="1" class="table text-center">

			        <tr>
			        	<th><div class="text-center"><h5>No.</h5></div></th>
			            <th><div class="text-center"><h5>ユーザー名</h5></div></th>
			            <th><div class="text-center"><h5>ログインID</h5></div></th>
			            <th><div class="text-center"><h5>編集</h5></div></th>
			            <th><div class="text-center"><h5>削除</h5></div></th>
			            <th><div class="text-center"><h5><input type="checkbox" name="allSelect"></h5></div></th>
			        </tr>
			        <c:forEach var="name" items="${searchResult}" varStatus="status">
			        	<tr>
				        	<td><c:out value="${status.count}"></c:out>
				            <td>${name.userName}</td>
				            <td>${name.loginId}</td>
				            <td>
				            <input type="hidden" name="nowLoginId" value="${nowLoginId}">
				            <input type="hidden" name="nowLoginUser" value="${nowLoginUser}">
				            <input type="hidden" name="nowLoginUserId" value="${nowLoginUserId}">
				            <input type="hidden" name="nowLoginProfile" value="${nowLoginProfile}">
				            <input type="hidden" name="nowLoginIcon" value="${nowLoginIcon}">
				            <input type="hidden" name="nowLoginPassword" value="${nowLoginPassword}">

				            <input type="hidden" name="loginId" value="${name.loginId}">


				            <input type="submit" name="change" class="btn btn-sm" value="編集"></td>

			            <td>
			           <input type="submit" name="delete" class="btn btn-error btn-sm" value="削除"></td>

			            <td>
			            <input type="checkbox" name="deletes" value="${name.loginId}"><br>
			            </td>

			        </tr>
					</c:forEach>

				</table>
				<div class="padding-y-5 text-right">
					<input type="submit" name="multipleDelete" class="btn btn-error btn-sm" value="まとめて削除"></div>
			    </form>


			<!-- 戻るボタンを押したら1ページ前に戻る -->
			<form>
			<div class="padding-y-5 text-left">
				<input type="button" class="btn" value="戻る" onClick="history.back()">
				</div>
			</form>

			</div>
		</div>
	</body>
</html>