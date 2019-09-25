<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>新規会員登録</title>
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
	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-center">
			<strong class="color-main">新規会員登録</strong>
		</div>
	</div>

	<div class="padding-y-5">
	<div style="width: 40%" class="container padding-y-5">
		<form action="RegisterLogic" method="post">
			<table border="1" class="table">
				<tr>
					<th><label for="loginId"><span class="icon-headphones pe-2x pe-va"></span>&nbsp;ログインID</label></th>
					<td><input type="text" name="loginId" id="loginId" class="form-control"></td>
				</tr>

				<tr>
					<th><label for="userName"><span class="icon-smile pe-2x pe-va"></span>&nbsp;名前</label></th>
					<td><input type="text" name="userName" id="userName" class="form-control"></td>
				</tr>

				<tr>
					<th><label for="password"><span class="icon-note pe-2x pe-va"></span>&nbsp;パスワード</label></th>
					<td><input type="password" name="password" id="password" class="form-control"></td>
				</tr>
				<tr>
					<th><span class="icon-users pe-2x pe-va"></span>&nbsp;アイコン</th>
					<td><label class="fancy-radio"><input type="radio" name="icon" value="icon-user" checked><span>男性</span></label>
					<label class="fancy-radio"><input type="radio" name="icon" value="icon-user-female"><span>女性</span></label>
					<label class="fancy-radio"><input type="radio" name="icon" value="icon-bell" ><span>ベル</span></label></td>

				</tr>
				<tr>
					<th><span class="icon-note2 pe-2x pe-va"></span>&nbsp;自己紹介</th>
					<td><textarea rows="5" cols="20" name="profile" class="form-control"></textarea></td>
				</tr>

				<tr>
					<td colspan="2" class="text-center"><input type="submit" value="OK" class="btn"></td>
				</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>
