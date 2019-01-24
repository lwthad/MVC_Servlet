<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>恭喜您${request_username}，登录成功</title>
    <%
        String path = (String)request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path +"/";
    %>
</head>
<body>
<%
    String username = (String)session.getAttribute("session_username");
    if(username == null){
        request.setAttribute("msg","你还没有登陆");
        //request.getRequestDispatcher("/login.jsp").forward(request,response);
        response.sendRedirect( basePath + "login.jsp");
        return;
    }
%>
<h1>恭喜您${session_username}，登录成功</h1>
</body>
</html>
