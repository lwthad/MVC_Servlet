package cn.lwthad.servlet;

import cn.lwthad.entity.User;
import cn.lwthad.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(urlPatterns = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    UserService userService = new UserService();

    @Override
    public void init() throws ServletException {
        System.out.println("已进入LoginServlet...");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //解决中文乱码
        req.setCharacterEncoding("utf-8");

        //校验验证码
        String sessionCode = (String) req.getSession().getAttribute("session_vcode");
        String verifyCode = req.getParameter("verifyCode");
        if(!verifyCode.equalsIgnoreCase(sessionCode)){
            req.setAttribute("msg","验证码输入错误！");
            req.getRequestDispatcher("/login.jsp").forward(req,resp);
            //停止向下执行
            return;
        }

        //获取表单用户信息
        String username =  req.getParameter("username");
        String passwd =  req.getParameter("password");
        User user = new User(username,passwd);

        //表单信息校验
        int i = userService.find(user);
        if(i > 0){
            req.getSession().setAttribute("session_username",username);     //登陆成功，则将用户名存入session域,（如果同一浏览器未关闭登陆多个账号，会进行覆盖；不同浏览器属于不同会话，互相不影响）
            /*cookie值中间不能有空格*/
            Cookie cookie = new Cookie("uname", username);//把用户名保存到cookies中，这样用户再次打开网站，login.jsp可以从Cookies取出用户名放在输入框
            cookie.setMaxAge(60*60*24);     //一天存活期
            resp.addCookie(cookie);
            /*重定向属于新的请求，url需要加项目名*/
            resp.sendRedirect(req.getContextPath()+"/result/success.jsp");  //重定向(url会改变)属于新的请求，如果上面用户名放到request域中，在目标页面请求不到
            return ;
        }else{
            req.setAttribute("msg","用户名不存在或密码错误");     //错误信息一般存到request域
            req.getRequestDispatcher("/result/error.jsp").forward(req,resp);    //进行转发（url不变），转发不属于新的请求request
            return;
        }

    }
}
