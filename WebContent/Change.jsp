<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>Shouter - ユーザー情報変更 -</title>
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
			<strong class="color-main">ユーザー情報変更</strong>
		</div>
	</div>

	<div class="padding-y-5">
		<div style="width: 40%" class="container padding-y-5">
			<form action="Change" method="post">
				<table border="1" class="table">
					<tr>
						<th><span class="icon-headphones pe-2x pe-va"></span>&nbsp;ログインID</th>
						<td><input type="text" name="loginId" id="loginId"
							value="${loginIdChange}" class="form-control"></td>
					</tr>

					<tr>
						<th><span class="icon-smile pe-2x pe-va"></span>&nbsp;名前</th>
						<td><input type="text" name="userName" id="userName"
							value="${userNameChange}" class="form-control"></td>
					</tr>

					<tr>
						<th><span class="icon-note pe-2x pe-va"></span>&nbsp;パスワード</th>
						<td><input type="password" name="password" id="password"
							value="${passwordChange}" class="form-control"></td>
					</tr>
					<tr>
						<th><span class="icon-users pe-2x pe-va"></span>&nbsp;アイコン</th>
						<td><label class="fancy-radio"><input type="radio"
								name="icon" value="icon-user" checked><span>男性</span></label> <label
							class="fancy-radio"><input type="radio" name="icon"
								value="icon-user-female"><span>女性</span></label> <label
							class="fancy-radio"><input type="radio" name="icon"
								value="icon-bell"><span>ベル</span></label></td>

					</tr>
					<tr>
						<th><span class="icon-note2 pe-2x pe-va"></span>&nbsp;自己紹介</th>
						<td><textarea rows="5" cols="20" name="profile"
								class="form-control">${profileChange}</textarea></td>
					</tr>

					<tr>
						<td colspan="2" class="text-center">
						<input type="hidden" name="userId" value="${userId}">
						<input type="hidden" name="changeUserLoginId" value="${changeUserLoginId}">

						<input type="submit" value="確認" class="btn"></td>
					</tr>
					<%--アラートがあるか確認 --%>
					<c:if
						test="${requestScope.alert != null && requestScope.alert != ''}">
						<tr>
							<%-- リクエストスコープの alert の値を出力 --%>
							<td colspan="2" class="color-error text-left"><c:out
								value="${requestScope.alert}" /></td>
						</tr>
						<tr>

						</tr>
					</c:if>
				</table>
			</form>

			<!-- 戻るボタンを押したら1ページ前に戻る -->
			<form>
				<input type="button" class="btn" value="戻る" onClick="history.back()">
			</form>

			</div>
		</div>
</body>
</html>