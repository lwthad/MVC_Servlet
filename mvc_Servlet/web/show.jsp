<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title></title>
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
      <%--用户名: ${user.username}<br>--%>
      <%--密  码：${user.passwd}<br>--%>
      <%
          String session_username = (String) pageContext.getSession().getAttribute("session_username");
      %>
      <%=session_username %>

       您已经登陆，${sessionScope.get("session_username")}
  </body>
</html>