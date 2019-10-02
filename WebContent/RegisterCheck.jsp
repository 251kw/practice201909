<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登録確認</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
</head>
<body>
	<%-- 文字化け対策 --%>
	<%
		request.setCharacterEncoding("UTF-8");
	%>
	<div class="bg-success padding-y-5">
		<div class="container padding-y-5 text-center">
			<h1>ユーザー登録</h1>
		</div>
	</div>
	<div class="padding-y-5 text-center">
		<div style="width: 50%" class="container padding-y-5 text-left">
			<strong>ユーザー情報</strong>
		</div>
	</div>
	<div class="padding-y-5 text-center">
		<div style="width: 50%" class="container padding-y-5">
			<%-- action 属性にサーブレットを指定 --%>
			<form action="RS2" method="post">
				<%-- 登録情報 --%>
				<table style="width: 600px" class="table">
					<tr>
						<td class="color-main text-left">アイコン:</td>
						<td>${param.icon}</td>
						<td></td>
					</tr>
					<tr>
						<td class="color-main text-left">氏名:</td>
						<td>${param.userName}</td>
						<td></td>
					</tr>
					<tr>
						<td class="color-main text-left">ログインID：</td>
						<td>${param.loginId}</td>
						<td></td>
					</tr>
					<tr>
						<td class="color-main text-left">パスワード：</td>
						<td>${param.password}</td>
						<td></td>
					</tr>
					<tr>
						<td class="color-main text-left">プロフィール：</td>
						<td>${param.profile}</td>
						<td></td>
					</tr>
					<tr>
						<%-- 内容確認 --%>
						<td>この内容でよろしいですか？ <%-- hiddenで情報を送信 --%> <input type="hidden"
							name="loginId" value="${param.loginId}"> <input
							type="hidden" name="password" value="${param.password}">
							<input type="hidden" name="userName" value="${param.userName}">
							<input type="hidden" name="icon" value="${param.icon}"> <input
							type="hidden" name="profile" value="${param.profile}"></td>
						<%-- 確認ボタン --%>
						<td><button type="submit" name="button1"
								class="btn btn-right" value="btn1">はい</button></td>
						<td><button type="submit" name="button2" class="btn btn-left"
								value="btn2">いいえ</button></td>
					</tr>
				</table>
			</form>
		</div>
	</div>

</body>
</html>