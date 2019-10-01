<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>変更内容確認</title>
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
            <h1>情報変更</h1>
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
            <form action="./CUI2" method="post">
            <%-- 登録情報 --%>
                <table class="table table-bordered">
                    <tr>
                    <%-- iconをparamで取得し表示 --%>
                    <td class="color-main text-left">アイコン:</td>
                        <td>${param.icon}</td>
                    </tr>
                    <tr>
                    <%-- userNameをparamで取得し表示 --%>
                    <td class="color-main text-left">氏名:</td>
                        <td>${param.userName}</td>
                    </tr>
                    <tr>
                    <%-- loginIdをparamで取得し表示 --%>
                    <td class="color-main text-left">ログインID：</td>
                        <td>${param.loginId}</td>
                    </tr>
                    <tr>
                    <%-- passwordをparamで取得し表示 --%>
                    <td class="color-main text-left">パスワード：</td>
                        <td>${param.password}</td>
                    </tr>
                    <tr>
                    <%-- profileをparamで取得し表示 --%>
                    <td class="color-main text-left">プロフィール：</td>
                        <td>${param.profile}</td>
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