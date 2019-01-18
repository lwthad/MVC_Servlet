package cn.lwthad.servlet;

import cn.lwthad.entity.User;
import cn.lwthad.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        /**
         * 校验验证码
         */
        String sessionCode = (String) req.getSession().getAttribute("session_vcode");
        String verifyCode = req.getParameter("verifyCode");
        if(!verifyCode.equalsIgnoreCase(sessionCode)){
            req.setAttribute("msg","验证码输入错误！");
            req.getRequestDispatcher("/login.jsp").forward(req,resp);
            //停止向下运行
            return;
        }

        /**
         * 获取表单用户信息
         */
        String username =  req.getParameter("username");
        String passwd =  req.getParameter("password");
        User user = new User(username,passwd);

        int i = userService.find(user);
        if(i > 0){
            req.setAttribute("username",username);
            req.getRequestDispatcher("/success.jsp").forward(req,resp);
        }else{
            req.getRequestDispatcher("/error.jsp").forward(req,resp);
        }
    }
}
