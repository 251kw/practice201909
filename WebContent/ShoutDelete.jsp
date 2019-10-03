<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>叫び削除</title>
</head>
<body>
<div class="padding-y-5">
        <div style="width: 40%" class="container padding-y-5">
<form action="./D" method="post">
                    <table class="table table-striped table-bordered">
                        <tr>
                            <td rowspan="2" class="text-center"><span
                                class="${shout.icon} pe-3x pe-va"></span></td>
                            <td>${shout.userName}</td>
                        </tr>
                        <tr>
                            <td>${shout.date}</td>
                        </tr>
                        <tr>
                            <td colspan="2"><textarea rows="5" class="form-control">${shout.writing}</textarea>
                            </td>
                        </tr>
                        <tr>
                           <%-- 内容確認 --%>
                        <th>本当に削除しますか？</th>
                        <%-- hiddenで情報を送信 --%>
                        <th><input type="hidden" name="loginId"
                            value="${user3.loginId}"> <input type="hidden"
                            name="password" value="${user3.password}"> <input
                            type="hidden" name="userName" value="${user3.userName}">
                            <input type="hidden" name="icon" value="${user3.icon}"> <input
                            type="hidden" name="profile" value="${user3.profile}">
                            <%-- 確認ボタン --%>
                            <button type="submit" name="btn" class="btn btn-right btn-sm"
                                value="はい">はい</button>
                            <button type="submit" name="btn" class="btn btn-left btn-sm"
                                value="いいえ">いいえ</button></th>
                            </tr>
                    </table>
                </form>
                </div>
                </div>
</body>
</html>