<%--
  Created by IntelliJ IDEA.
  User: tao
  Date: 2019/1/18
  Time: 1:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登陆页面</title>
    <%
        String message = "";
        String msg = (String) request.getAttribute("msg");
        if(msg != null){
            message = msg;
        }
    %>
    <script src="http://libs.baidu.com/jquery/2.1.4/jquery.min.js"></script>
    <script>
        $(document).ready(function () {
            var a = document.getElementById('a');
            a.onclick = function () {
                var imgEle = document.getElementById("img")
                imgEle.src = "VerifyCodeServlet?a=" + new Date().getTime();
            }
        })
        window.onpageshow=function (ev) {
            var imgEle = document.getElementById("img")
            imgEle.src = "VerifyCodeServlet?a=" + new Date().getTime();
        }
    </script>
</head>
<body><%--<%=uname%>--%>
    <font color="red" ><b><%=message%></b></font>

    <form action="LoginServlet" method="post">
        用户名：<input type="text" name="username" value="" /><br/>
        密&nbsp;&nbsp;&nbsp;码：<input type="password" name="password" /><br/>
        验证码：<input type="text" name="verifyCode" size="3" />
        <img id="img" src="VerifyCodeServlet">
        <a href="javascript:;" id="a">看不清？换一张</a><br>
        <input type="submit" value="登录">
    </form>
<script>
    var a = document.getElementById('a');
    a.onclick = function () {
        var imgEle = document.getElementById("img")
        imgEle.src = "VerifyCodeServlet?a=" + new Date().getTime();
    }
</script>

</body>
</html>
