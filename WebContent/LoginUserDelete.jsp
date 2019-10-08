<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>Shouter - ユーザー情報削除確認画面 -</title>
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
	<div class="padding-y-5">
		<div style="width: 40%" class="container padding-y-5 text-center">
			<form action="Delete" method="get">
				<%--入力された値をパラメータで取得し表示 --%>
				下記ユーザーを削除した場合、自動的にログアウトされます
				<table class="table table-bordered">
					<tr>
						<td rowspan="2" class="text-center"><span
							class="${icon} pe-3x pe-va"></span></td>
						<td width="256">${userName}</td>
					</tr>
					<tr>
						<td colspan="2">${profile}</td>
					</tr>
				</table>
				<input type="hidden"name="loginId" value="${loginId}">
				<input type="hidden" name="userName" value="${userName}">
				<input type="hidden" name="delete" value="loginUserDelete">
				<input type="submit" value="削除" class="btn btn-warning">
				<%--HiddenでloginIdをDeleteに渡す--%>
			</form>

			<%--戻るボタンを押したら1つ前にページに戻る --%>
			<form>
				<input type="button" class="btn btn-success" value="戻る" onClick="history.back()"><br>
				<a href="Top" class="btn btn-warning">トップへ戻る</a>
			</form>
		</div>
	</div>
</body>
</html>
