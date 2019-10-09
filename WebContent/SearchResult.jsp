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

<style type="text/css">

#content {
    width: 100%;
    table-layout: fixed;
}
.tdata {
    height: 100%;
    overflow: hidden;
    white-space: nowrap;
    text-overflow: ellipsis;
}

</style>
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
		<div style="width: 60%" class="container padding-y-5">
				検索結果<span class="icon-search text-cenyer"></span><br>
					<c:choose>
					<c:when test="${requestScope.alert != null && requestScope.alert != ''}">
						<%-- リクエストスコープの alert の値を出力 --%>
						<br><br><div  class="color-error text-center">
						<c:out value="${requestScope.alert}" /></div><br><br>
					</c:when>
					<c:otherwise>

			  <form action="MultipleDelete" method="post">
				<table border="1" class="table text-center" id="content">

			        <tr>
			        <!-- 表の見出し -->
			        	<th><div class="text-center"><h6>アイコン</h6></div></th>
			            <th><div class="text-center"><h6>ユーザー名</h6></div></th>
			            <th><div class="text-center"><h6>ログインID</h6></div></th>
			            <th><div class="text-center"><h6>自己紹介</h6></div></th>
			            <th><div class="text-center"><h6>編集</h6></div></th>
			            <th><div class="text-center"><h6>削除</h6></div></th>
			            <th><div class="text-center"><a href="AllSelect?select=select" class="btn btn-dark btn-sm">選</a>
			            <a href="AllSelect?select=Lifted" class="btn btn-light btn-sm">解</a></div></th>
			        </tr>
			        <!-- 名前とログインIDを表示する -->

			        <c:forEach var="name" items="${searchResult}" varStatus="status">
			        	<tr>
				        	<td><span class="${user.icon} pe-3x pe-va"></span>
				            <td>${name.userName}</td>
				            <td><p class="tdata">${name.loginId}</p></td>
				            <td><p class="tdata">${name.profile}</p></td>
				            <td>
							<a href="UserEdit?loginId=${name.loginId}" class="btn btn-sm">編集</a>

			            <td>
			            <a href="UserDelete?loginId=${name.loginId}" class="btn btn-error btn-sm">削除</a>

			            <td>
			            <input type="checkbox" name="deletes" value="${name.loginId}" ${checked}
			            <c:forEach var="id" items="${deliIds}"><c:if test="${id == name.loginId}">checked</c:if></c:forEach>><br>
			            </td>

			        </tr>
					</c:forEach>
				</table>

				<div class="padding-y-5 text-right">
					<input type="hidden" name="delete" value="delete">
					<input type="submit" name="multipleDelete" class="btn btn-error btn-sm" value="まとめて削除">
					</div>
					</form>
					</c:otherwise>
					 </c:choose>



			<!-- 戻るボタンを押したら1ページ前に戻る -->
			<form>
			<div class="padding-y-5 text-left">
				<a href="Search.jsp" class="btn">戻る</a><br>
				<a href="Top" class="btn btn-warning">トップへ戻る</a>
				</div>
			</form>

			</div>
		</div>
	</body>
</html>