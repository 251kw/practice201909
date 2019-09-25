<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Shouter -ユーザー登録</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
</head>
<body>
	<div class="bg-success padding-y-5">
		<div class="container padding-y-5 text-center">
			<h1>ユーザー登録</h1>
		</div>
	</div>
	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-left">
			<strong>アカウントを作成</strong>
		</div>
	</div>
	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-center">
			<%-- action 属性にサーブレットを指定 --%>
			<form action="./RS" method="post">
				<table style="width: 400px" class="table">
					<tr>
						<%-- ログインID 入力欄の名前は loginId --%>
						<td class="color-main text-left">ログインID</td>
						<td class="text-left"><input class="form-control" type="text"
							name="loginId" value="" size="20" /></td>
					</tr>
					<tr>
						<%-- パスワード入力欄の名前は password --%>
						<td class="color-main text-left">パスワード</td>
						<td class="text-left"><input class="form-control"
							type="password" name="password" value="" size="20" /></td>
					</tr>
					<tr>
						<%-- 氏名は userName --%>
						<td class="color-main text-left">氏名</td>
						<td class="text-left"><input class="form-control" type="text"
							name="userName" value="" size="20" /></td>
					</tr>
					<tr>
						<%-- アイコンはicon --%>
						<td class="color-main text-left">アイコン</td>
						<td><select name="icon" class="form-control">
								<option value="icon-user">男
								<option value="icon-user-female">女
								<option value="icon-bell">ベル
						</select></td>
					</tr>
					<tr>
                        <%-- プロフィールは profile --%>
                        <td class="color-main text-left">プロフィール</td>
                        <td class="text-left"><input class="form-control" type="text"
                            name="profile" value="" size="20" /></td>
                    </tr>
					<tr>
						<td colspan="2" class="text-right"><input class="btn"
							type="submit" value="ユーザーアカウント作成" /></td>
						<%-- リクエストスコープに alert があれば --%>
						<c:if
							test="${requestScope.alert != null && requestScope.alert != ''}">
							<tr>
								<%-- リクエストスコープの alert の値を出力 --%>
								<td colspan="2" class="color-error text-left"><c:out
										value="${requestScope.alert}" /></td>
							</tr>
						</c:if>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>