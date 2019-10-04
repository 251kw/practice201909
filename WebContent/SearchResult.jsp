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

			  <form action="MultipleDelete" method="post">
				<table border="1" class="table text-center">

			        <tr>
			        <!-- 表の見出し -->
			        	<th><div class="text-center"><h5>No.</h5></div></th>
			            <th><div class="text-center"><h5>ユーザー名</h5></div></th>
			            <th><div class="text-center"><h5>ログインID</h5></div></th>
			            <th><div class="text-center"><h5>編集</h5></div></th>
			            <th><div class="text-center"><h5>削除</h5></div></th>
			            <th><div class="text-center"><h5><input type="checkbox" name="allSelect"></h5></div></th>
			        </tr>
			        <!-- 名前とログインIDを表示する -->

			        <c:forEach var="name" items="${searchResult}" varStatus="status">
			        	<tr>
				        	<td><c:out value="${status.count}"></c:out>
				            <td>${name.userName}</td>
				            <td>${name.loginId}</td>
				            <td>
							<a href="UserEdit?loginId=${name.loginId}" class="btn btn-sm">編集</a>

			            <td>
			            <a href="UserDelete?loginId=${name.loginId}" class="btn btn-error btn-sm">削除</a>

			            <td>
			            <input type="checkbox" name="deletes" value="${name.loginId}"><br>
			            </td>

			        </tr>
					</c:forEach>

				</table>
				<div class="padding-y-5 text-right">
					<c:choose>
					<c:when test="${requestScope.alert != null && requestScope.alert != ''}">
						<%-- リクエストスコープの alert の値を出力 --%>
						<div  class="color-error text-left">
						<c:out value="${requestScope.alert}" /></div>
					</c:when>
					<c:otherwise>
					<input type="hidden" name="delete" value="delete">
					<input type="submit" name="multipleDelete" class="btn btn-error btn-sm" value="まとめて削除"></c:otherwise></c:choose></div>

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