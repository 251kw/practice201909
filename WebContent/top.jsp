<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>Shouter - みんなの心の叫び -</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
<style type="text/css">

tabule .shout,

table .shout td {
background-color: #FFFFFF;
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
		<div style="width: 40%" class="container padding-y-5 text-left">
			<strong class="color-main">ログインユーザー情報</strong>
		</div>
	</div>
	<div class="padding-y-5">
		<div style="width: 40%" class="container padding-y-5">
			<%-- action 属性にサーブレットを指定 --%>
			<form action="./logout" method="post">
				<table class="table table-bordered">
					<tr>
						<td rowspan="2" class="text-center"><span
							class="${user.icon} pe-3x pe-va"></span></td>
						<td width="256">${user.userName}</td>
						<td><input class="btn btn-light" type="submit" value="ログアウト" /></td>
					</tr>
					<tr>
						<td colspan="2">${user.profile}</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<%--会員検索欄 --%>
	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-left">
			<strong class="color-main">会員情報の検索</strong>
		</div>
	</div>
	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-right">
			<%--Search.jspに転送 --%>
			<form action="Search.jsp" method="post">
				<table class="table">
					<tr>
						<%--会員検索ボタン --%>
						<td><input class="btn btn-success" type="submit" value="会員検索" />
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-left">
			<strong class="color-main">今の気持ちを叫ぼう</strong>
		</div>
	</div>
	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-center">
			<%-- action 属性にサーブレットを指定 --%>
			<form action="./bbs" method="post">
				<table class="table">
					<tr>
						<%-- 今の気持ち入力欄の名前は shout --%>
						<td><input class="form-control" type="text" name="shout"
							value="" size="60" /></td>
						<td><input class="btn" type="submit" value="叫ぶ" /></td>
					</tr>
					<c:if
						test="${requestScope.alert != null && requestScope.alert != ''}">
						<tr>
							<%-- リクエストスコープの alert の値を出力 --%>
							<td colspan="2" class="color-error text-left"><c:out
									value="${requestScope.alert}" /></td>
						</tr>

					</c:if>

				</table>
			</form>
		</div>
	</div>
	<jsp:useBean id="shouts" scope="session"
		type="java.util.ArrayList<dto.ShoutDTO>" />
	<c:choose>
		<c:when test="${stResult == 'true'}">

				<div class="padding-y-5 text-center">
					<div style="width: 40%" class="container padding-y-5 text-left">
						<strong class="color-main">みんなの叫び</strong>
					</div>

					<c:if test="${idResult == 'true'}">
					<div style="width: 40%" class="container padding-y-5 text-right">
						<a href="AllSelect?select=allDelete&stResult=${stResult}&idResult=${idResult}" class="btn btn-dark btn-sm">選</a>
						<a href="AllSelect?select=notAll&stResult=${stResult}&idResult=${idResult}" class="btn btn-light btn-sm">解</a>
					</div></c:if>
				</div>

				<div class="padding-y-5">
					<div style="width: 40%" class="container padding-y-5">
						<form action="ShoutsDeleteCheck" method="post">

							<%-- リストにある要素の数だけ繰り返し --%>
							<c:forEach var="shout" items="${shouts}">
								<table class="table table-striped table-bordered">
									<tr>
										<td rowspan="2" class="text-center"><span
											class="${shout.icon} pe-3x pe-va"></span></td>
										<td>${shout.userName}</td>
										<td class="text-center"><c:if
												test="${user.loginId == shout.loginId}">
												<a href="ShoutsDeleteCheck?id=${shout.shoutsId}"
													class="btn btn-error btn-sm">削除</a>
											</c:if></td>
										<td class="text-center"><c:if
												test="${user.loginId == shout.loginId}">
												<input type="checkbox" name="deleteShouts"
													value="${shout.shoutsId}" ${checked}>
											</c:if></td>
									</tr>
									<tr>
										<td colspan="3">${shout.date}</td>
									</tr>
									<tr>
										<td colspan="4" class="shout text-center"><br>${shout.writing}<br>
											<br></td>
									</tr>
									<tr>
										<td class="text-center" colspan="4"><c:if
												test="${user.loginId == shout.loginId}">
												<a href="ShoutEdit?id=${shout.shoutsId}"
													class="btn btn-success btn-sm">編集</a>
											</c:if>
									</tr>
								</table>
							</c:forEach>
							<c:if test="${idResult == 'true'}">
							<div class="padding-y-5 text-center">
								<input type="submit" class="btn btn-error btn-sm" value="まとめて削除">
							</div></c:if>
						</form>
					</div>
				</div>
			</c:when>
			<c:otherwise>

			</c:otherwise>
	</c:choose>
</body>
</html>
