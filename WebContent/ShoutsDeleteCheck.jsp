<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
			<form action="ShoutDelete" method="post">
				<%--入力された値をパラメータで取得し表示 --%>
				下記の叫びを削除します

				<table class="table table-striped table-bordered">
					<tr>
						<td rowspan="2" class="text-center"><span
							class="${param.icon} pe-3x pe-va"></span></td>
						<td>${param.userName}</td>
					</tr>
					<tr>
						<td>${param.date}</td>
					</tr>
					<tr>
						<td colspan="2"><textarea rows="5" class="form-control">${param.writing}</textarea>
						</td>
					</tr>
					</table>


				<input type="hidden" name="shoutsId" value="${param.shoutsId}">
				<input type="submit" value="削除" class="btn btn-warning">
				<%--HiddenでloginIdをDeleteに渡す--%>
			</form>

			<%--戻るボタンを押したら1つ前にページに戻る --%>
			<form>
				<input type="button" class="btn" value="戻る" onClick="history.back()">
			</form>
					</div>
	</div>
</body>
</html>
