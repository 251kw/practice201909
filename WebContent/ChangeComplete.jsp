<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>Shouter - ユーザー情報変更完了 -</title>
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


	<%
		request.setCharacterEncoding("UTF-8");
	%>
	<div class="padding-y-5 text-center">
	変更が完了しました
		<div style="width: 40%" class="container padding-y-5">
			<form action="Top" method="post">
				<%--入力された値をパラメータで取得し表示 --%>
				<table border="1" class="table">
					<tr>
						<th><label for="loginId"><span
								class="icon-headphones pe-2x pe-va"></span>&nbsp;ログインID</label></th>
						<td>${changeUserLoginId}</td>
					</tr>

					<tr>
						<th><label for="userName"><span
								class="icon-smile pe-2x pe-va"></span>&nbsp;名前</label></th>
						<td>${userName}</td>
					</tr>

					<tr>
						<th><label for="pass"><span
								class="icon-note pe-2x pe-va"></span>&nbsp;パスワード</label></th>
						<td>${password}</td>
					</tr>
					<tr>
						<th><span class="icon-users pe-2x pe-va"></span>&nbsp;アイコン</th>
						<td><span class="${icon} pe-5x pe-va"></span></td>

					</tr>
					<tr>
						<th><span class="icon-note2 pe-2x pe-va"></span>&nbsp;自己紹介</th>
						<td>${profile}</td>
					</tr>
					<tr>
						<%--変更内容で再ログインしてトップに戻る--%>
						<td colspan="2" class="text-center">

						<input type="submit"value="トップへ戻る" class="btn btn-warning"></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>
