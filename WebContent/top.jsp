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
							class="${nowLoginIcon} pe-3x pe-va"></span></td>
						<td width="256">${nowLoginUser}</td>
						<td><input class="btn btn-light" type="submit" value="ログアウト" /></td>
					</tr>
					<tr>
						<td colspan="2">${nowLoginProfile}</td>
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
						<td>
							<input type="hidden" name="nowLoginId" value="${nowLoginId}">
							<input type="hidden" name="nowLoginUser" value="${nowLoginUser}">
							<input type="hidden" name="nowLoginUserId" value="${nowLoginUserId}">
							<input type="hidden" name="nowLoginProfile" value="${nowLoginProfile}">
							<input type="hidden" name="nowLoginIcon" value="${nowLoginIcon}">
							<input type="hidden" name="nowLoginPassword" value="${nowLoginPassword}">
							<input class="btn btn-success" type="submit" value="会員検索" />
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
				</table>
			</form>
		</div>
	</div>
	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-left">
			<strong class="color-main">みんなの叫び</strong>
		</div>
	</div>
	<div class="padding-y-5">
		<div style="width: 40%" class="container padding-y-5">
			<%-- リストにある要素の数だけ繰り返し --%>
			<c:forEach var="shout" items="${shouts}">
				<table class="table table-striped table-bordered">
					<tr>
						<td rowspan="2" class="text-center"><span
							class="${shout.icon} pe-3x pe-va"></span></td>
						<td>${shout.userName}</td>
					</tr>
					<tr>
						<td>${shout.date}</td>
					</tr>
					<tr>
						<td colspan="2"><textarea rows="5" class="form-control">${shout.writing}</textarea>
						</td>
					</tr>
					<tr>
						<td colspan="2" class="text-center">
							<c:if test="${nowLoginId == shout.loginId}">
								<form action="ShoutsDeleteCheck.jsp" method="post">
									<input type="hidden" name="shoutsId" value="${shout.shoutsId}">
									<input type="hidden" name="icon" value="${shout.icon}">
									<input type="hidden" name="userName" value="${shout.userName}">
									<input type="hidden" name="date" value="${shout.date}">
									<input type="hidden" name="writing" value="${shout.writing}">

									<input type="hidden" name="nowLoginId" value="${nowLoginId}">
									<input type="hidden" name="nowLoginUser" value="${nowLoginUser}">
									<input type="hidden" name="nowLoginUserId" value="${nowLoginUserId}">
									<input type="hidden" name="nowLoginProfile" value="${nowLoginProfile}">
									<input type="hidden" name="nowLoginIcon" value="${nowLoginIcon}">
									<input type="hidden" name="nowLoginPassword" value="${nowLoginPassword}">
									<input type="submit" class="btn btn-error btn-sm" value="削除">
								</form>
							</c:if>
						</td>
					</tr>
				</table>
			</c:forEach>
		</div>
	</div>
</body>
</html>
