<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en"
      xmlns:th="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="../static/css/bootstrap.min.css" th:href="@{/css/bootstrap.min.css}"></link>


    <title>用户登录</title>

    <style>
        .bgColor{
            background-color:rgba(243,66,111,0.15)
        }
        .divBorder{
            border: solid 1px rgba(12,24,255,0.15);
            padding: 10px;
            margin-top: 10px;
            border-radius: 10px;
            text-align: center;
            vertical-align: middle;
        }
        .h4font{
            margin-top: 0px;
            font-family: 微软雅黑;
            font-weight: 500;
        }

        .center {
           padding: 20% 0;
        }



    </style>

</head>
<body>

<div class="container">
    <div class="row center">
        <div class="divBorder col-sm-offset-3 col-sm-6">
            <h3 class="panel panel-heading h4font">
                用户登录
            </h3>
            <h4 th:text="${msg}"></h4>
            <form class="form-horizontal" th:action="@{/login}" method="post">
                <div class="input-group input-group-lg">
                    <span class="input-group-addon"><i class="glyphicon glyphicon-user" aria-hidden="true"></i></span>
                    <input type="text" class="form-control" id="userName" name="userName" placeholder="请输入用户名称" th:value="${userName}"/>
                </div>
                <br>
                <div class="input-group input-group-lg">
                    <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                    <input type="password" class="form-control" id="password" name="password" placeholder="请输入密码"/>
                </div>


                <br>
                <input type="submit" class="btn btn-lg btn-block btn-info" value="登 录">
            </form>
        </div>


    </div>

</div>




</body>
</html>