<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
<title>検索画面</title>
</head>
<body>
	<div class="bg-success padding-y-5">
		<div class="container padding-y-5 text-center">
			<h1>ユーザー検索</h1>
		</div>
	</div>
	<div class="padding-y-5">
		<div style="width: 40%" class="container padding-y-5">

			<form action="./SP" method="post">
				<table style="width: 600px" class="table ">

					<%-- ユーザー検索 --%>
					<tr>
						<th><strong class="color-main">ユーザー名で検索</strong></th>
						<td><div class="text-left">
								<input class="form-control" type="text" name="userName" value=""
									size="20" />
							</div></td>
						<%-- ユーザー検索ボタン --%>
						<td><input class="btn" name="btn1" type="submit" value="検索" /></td>
					</tr>
					<%-- icon検索 --%>
					<tr>
						<th><strong class="color-main">アイコンで検索</strong></th>
						<td><select name="icon" class="form-control">
								<option value="icon-user">男
								<option value="icon-user-female">女
								<option value="icon-bell">ベル
						</select></td>
						<td><input class="btn" name="btn2" type="submit" value="検索" /></td>
					</tr>
					<%-- プロフィール検索 --%>
					<tr>
						<th><strong class="color-main">プロフィールで検索</strong></th>
						<td><div class="text-left">
								<input class="form-control" type="text" name="profile" value=""
									size="20" />
							</div></td>
						<%-- ユーザー検索ボタン --%>
						<td><input class="btn" name="btn3" type="submit" value="検索" /></td>
					</tr>

				</table>
			</form>
			<%--topページへ --%>
			<form action="top.jsp" method="post">
				<input class="btn" type="submit" value="戻る" />
			</form>
		</div>
	</div>
	<%-- リクエストスコープに alert があれば --%>
    <div class="padding-y-5 text-center">
        <c:if test="${requestScope.alert != null && requestScope.alert != ''}">
            <div class="color-error text-center">
                <%-- リクエストスコープの alert の値を出力 --%>
                <c:out value="${requestScope.alert}" />
            </div>
        </c:if>
    </div>
</body>
</html>