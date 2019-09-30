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
	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-left">
			<strong class="color-main">ユーザー情報</strong>
		</div>
	</div>
	<div class="padding-y-5">
		<div style="width: 40%" class="container padding-y-5">
			<%-- action 属性にサーブレットを指定 --%>
			<form action="RS2" method="post">
			<%-- 登録情報 --%>
				<table class="table table-bordered">
					<tr>
						<th>アイコン:${param.icon}</th>
					</tr>
					<tr>
						<th>氏名:${param.userName}</th>
					</tr>
					<tr>
						<th>ログインID：${param.loginId}</th>
					</tr>
					<tr>
						<th>パスワード：${param.password}</th>
					</tr>
					<tr>
						<th>プロフィール：${param.profile}</th>
					</tr>
					<tr>
						<%-- 内容確認 --%>
						<th>この内容でよろしいですか？</th>
						<%-- hiddenで情報を送信 --%>
						<th><input type="hidden" name="loginId"
							value="${param.loginId}"> <input type="hidden"
							name="password" value="${param.password}"> <input
							type="hidden" name="userName" value="${param.userName}">
							<input type="hidden" name="icon" value="${param.icon}"> <input
							type="hidden" name="profile" value="${param.profile}">
							<%-- 確認ボタン --%>
							<button type="submit" name="button1" class="btn btn-right"
								value="btn1">はい</button>
							<button type="submit" name="button2" class="btn btn-left"
								value="btn2">いいえ</button></th>
					</tr>
				</table>
			</form>
		</div>
	</div>

</body>
</html>