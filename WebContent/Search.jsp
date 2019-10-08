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

	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-left">
			<strong class="color-main">会員情報の検索</strong>
		</div>
	</div>
	<div class="padding-y-5 text-left">
		<div style="width: 40%" class="container padding-y-5 text-left">
			<c:if
				test="${requestScope.alert != null && requestScope.alert != ''}">

				<%-- リクエストスコープの alert の値を出力 --%>
				<div class="color-error text-center"><c:out value="${requestScope.alert}" />
				</div>
				<input type="hidden" name="userDelete" value="userDelete">
			</c:if>

			検索条件<span class="icon-search"></span>
			<c:if test="${sessionScope.searchList != null && sessionScope.searchList != ''}">
			<jsp:useBean id="searchList" scope="session" type="java.util.ArrayList<String>" /></c:if>
			<form action="SeachLogic" method="get">
				<table class="table">
					<tr>
						<td>ユーザー名</td>
						<td><input type="text" size="40" name="userNameSearch" value="${searchList[0]}"
							class="form-control"></td>
					</tr>
					<tr>
						<td>ユーザーID</td>
						<td><input type="text" size="40" name="loginIdSearch" value="${searchList[1]}"
							class="form-control"></td>
					</tr>
					<tr>
						<td>自己紹介</td>
						<td><input type="text" size="40" name="profileSearch" value="${searchList[2]}"
							class="form-control"></td>
					</tr>
					<tr>
						<td>アイコン</td>
						<td><label class="fancy-radio"><input type="radio"
								name="icon" value="null" checked ><span>未選択</span></label> <label
							class="fancy-radio"><input type="radio" name="icon"
								value="icon-user" <c:if test="${searchList[3] == 'icon-user'}">checked</c:if>><span>男性</span></label> <label
							class="fancy-radio"><input type="radio" name="icon"
								value="icon-user-female" <c:if test="${searchList[3] == 'icon-user-female'}">checked</c:if>><span>女性</span></label> <label
							class="fancy-radio"><input type="radio" name="icon"
								value="icon-bell" <c:if test="${searchList[3] == 'icon-bell'}">checked</c:if>><span>ベル</span></label></td>
					</tr>
				</table>

				<div class="padding-y-5 text-right">
				<input type="hidden" name="search" value="search">
					<input class="btn btn-success" type="submit" value="検索" />
				</div>
			</form>

			<!-- 戻るボタンを押したら1ページ前に戻る -->
			<form>
				<a href="Top" class="btn">戻る</a>
			</form>

		</div>
	</div>
</body>
</html>