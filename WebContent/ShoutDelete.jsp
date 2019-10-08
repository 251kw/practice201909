<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>叫び削除</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
</head>
<body>
	<div class="padding-y-5">
		<div style="width: 40%" class="container padding-y-5">

			<c:forEach var="shouts" items="${shoutdeletelist}">
				<form action="./SD2" method="post">
					<table class="table table-striped table-bordered">
						<tr>
							<td rowspan="2" class="text-center"><span
								class="${shouts.icon} pe-3x pe-va"></span></td>
							<td>${shouts.userName}</td>
						</tr>
						<tr>
							<td>${shouts.date}</td>
						</tr>
						<tr>
							<td colspan="2"><textarea rows="5" class="form-control">${shouts.writing}</textarea>
							</td>
						</tr>
						<tr>
							<%-- 内容確認 --%>
							<td>本当に削除しますか？</td>
							<%-- hiddenで情報を送信 --%>
							<td colspan="2"><input type="hidden" name="shoutsId"
								value="${shouts.shoutsId}"><input type="hidden"
								name="loginId" value="${shouts.loginId}"><input
								type="hidden" name="userName" value="${shouts.userName}">
								<input type="hidden" name="icon" value="${shouts.icon}">
								<input type="hidden" name="date" value="${shouts.date}">
								<input type="hidden" name="writing" value="${shouts.writing}">
								<%-- 確認ボタン --%>
								<button type="submit" name="btn" class="btn btn-right btn-sm"
									value="はい">はい</button>
								<button type="submit" name="btn" class="btn btn-left btn-sm"
									value="いいえ">いいえ</button></td>
						</tr>
					</table>
				</form>
			</c:forEach>
		</div>
	</div>
</body>
</html>