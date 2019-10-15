<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="dto.UserDTO"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登録情報変更</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
</head>
<body>
	<%
		UserDTO user1 = (UserDTO) request.getAttribute("user1");
		ArrayList<UserDTO> searchlist = (ArrayList<UserDTO>) request.getAttribute("searchlist");
	%>
	<div class="bg-success padding-y-5">
		<div class="container padding-y-5 text-center">
			<h1>登録情報変更</h1>
		</div>
	</div>
	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-center">
			<form action="./CUI" method="post">
				<table style="width: 400px" class="table">
					<tr>
						<%-- sessionに保存したloginIdを表示 --%>
						<td class="color-main text-left">ログインID</td>
						<td class="color-main text-left">${user1.loginId}<%--
						ChangeUerInformationでparamaterを取得できるように
						hiddenでuser2に保存してあるloginIdを格納 --%> <input type="hidden"
							name="loginId" value="${user1.loginId}">
						</td>
					</tr>
					<tr>
						<%-- sessionに保存したpasswordを表示 --%>
						<td class="color-main text-left">パスワード</td>
						<td class="text-left">
							<%--
                        ChangeUerInformationでparamaterを取得できるように
                        hiddenでuser2に保存してあるpasswordを格納 --%> <input
							class="form-control" type="password" name="password"
							value="${user1.password}" size="20" />
						</td>
					</tr>
					<tr>
						<%-- sessionに保存したuserNameを表示 --%>
						<td class="color-main text-left">氏名</td>
						<td class="text-left">
							<%--
                        ChangeUerInformationでparamaterを取得できるように
                        hiddenでuser2に保存してあるuserNameを格納 --%> <input
							class="form-control" type="text" name="userName"
							value="${user1.userName}" size="20" />
						</td>
					</tr>
					<tr>
						<%-- sessionに保存したiconを表示 --%>
						<td class="color-main text-left">アイコン</td>
						<td><select name="icon" class="form-control">
								<option value="icon-user" label="男" ${user1.selected}>
								<option value="icon-user-female" label="女" ${user1.selected1}>
								<option value="icon-bell" label="ベル" ${user1.selected2}>
						</select></td>
					</tr>
					<tr>
						<%-- sessionに保存したprofileを表示 --%>
						<td class="color-main text-left">プロフィール</td>
						<td class="text-left">
							<%--
                        ChangeUerInformationでparamaterを取得できるように
                        hiddenでuser2に保存してあるprofileを格納 --%> <input
							class="form-control" type="text" name="profile"
							value="${user1.profile}" size="20" />
						</td>
					</tr>
					<tr>
						<%--CUIservletに情報送信 --%>
						<td><input class="btn" name="btn" type="submit" value="更新" /></td>
						<td><input class="btn btn-right" name="btn" type="submit"
							value="戻る" /></td>
					</tr>

					<%-- リクエストスコープに alert があれば --%>
					<c:if
						test="${requestScope.alert != null && requestScope.alert != ''}">
						<tr>
							<%-- リクエストスコープの alert の値を出力 --%>
							<td colspan="2" class="color-error text-left"><c:out
									value="${requestScope.alert}" /></td>
						</tr>
					</c:if>
				</table>
				
				<c:forEach var="loginId2" items="${loginId2}">
					<input type="hidden" name="loginId2" value="${loginId2}">
				</c:forEach>
			</form>
		</div>
	</div>
</body>
</html>