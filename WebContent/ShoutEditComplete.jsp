<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>Shouter - 叫び編集完了 -</title>
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
	編集が完了しました
		<div style="width: 40%" class="container padding-y-5">
			<form action="Top" method="post">
				<%--入力された値をパラメータで取得し表示 --%>
				<table border="1" class="table">

					<tr>
						<th><label for="userName"><span
								class="icon-smile pe-2x pe-va"></span>&nbsp;名前</label></th>
						<td>${userName}</td>
					</tr>

					<tr>
						<th><span class="icon-users pe-2x pe-va"></span>&nbsp;アイコン</th>
						<td><span class="${icon} pe-5x pe-va"></span></td>

					</tr>
					<tr>
						<th><span class="icon-speaker pe-2x pe-va"></span>&nbsp;叫び</th>
						<td>${writing}</td>
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
