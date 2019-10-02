<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ユーザー削除</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
</head>
<body><%-- 文字化け対策 --%>
    <%
        request.setCharacterEncoding("UTF-8");
    %>
    <div class="bg-success padding-y-5">
        <div class="container padding-y-5 text-center">
            <h1>ユーザー削除</h1>
        </div>
    </div>
    <div class="padding-y-5 text-center">
        <div style="width: 40%" class="container padding-y-5 text-left">
            <strong>ユーザー情報</strong>
        </div>
    </div>
    <div class="padding-y-5">
        <div style="width: 40%" class="container padding-y-5">
            <%-- action 属性にサーブレットを指定 --%>
            <form action="./UD" method="post">
            <%-- 登録情報 --%>
                <table class="table ">
                    <tr>
                    <%-- iconを取得し表示 --%>
                    <td class="color-main text-left">アイコン:</td>
                        <td>${user3.icon}</td>
                    </tr>
                    <tr>
                    <%-- userNameを取得し表示 --%>
                    <td class="color-main text-left">氏名:</td>
                        <td>${user3.userName}</td>
                    </tr>
                    <tr>
                    <%-- loginIdを取得し表示 --%>
                    <td class="color-main text-left">ログインID：</td>
                        <td>${user3.loginId}</td>
                    </tr>
                    <tr>
                    <%-- passwordを取得し表示 --%>
                    <td class="color-main text-left">パスワード：</td>
                        <td>${user3.password}</td>
                    </tr>
                    <tr>
                    <%-- profileを取得し表示 --%>
                    <td class="color-main text-left">プロフィール：</td>
                        <td>${user3.profile}</td>
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
                            <button type="submit" name="btn" class="btn btn-right"
                                value="はい">はい</button>
                            <button type="submit" name="btn" class="btn btn-left"
                                value="いいえ">いいえ</button></th>

                    </tr>
                </table>
            </form>
        </div>
    </div>
</body>
</html>