<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>削除完了</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
</head>
<body>
    <%--登録完了を表示 --%>
    <div class="bg-success padding-y-5">
        <div class="container padding-y-5 text-center">
            <h1>削除完了しました</h1>
        </div>
    </div>
    <%--トップページに戻る --%>
    <form action="./UD2" method="post">
            <div class="padding-y-5 text-center">
                <input class="btn" type="submit" value="戻る" />
            </div>
    </form>


</body>
</html>