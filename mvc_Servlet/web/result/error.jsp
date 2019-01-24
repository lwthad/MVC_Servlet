<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登陆失败</title>
</head>
<body>
<%
    //Java脚本方式获取域内容
    String msg = (String)request.getAttribute("msg");
%>

    <p color="red"><b>①<%=msg %></b></p>

    <br>
    <%--El表达式获取域内容--%>
    登录失败，②${requestScope.msg},请检查用户名和密码后重新登录。
    <br>
    <br>
    <a href="http://localhost:8082/servlet_MVC/login.jsp">回到登陆页面</a>
</body>
</html>
