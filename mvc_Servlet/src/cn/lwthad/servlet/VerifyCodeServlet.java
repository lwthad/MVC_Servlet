package cn.lwthad.servlet;

import cn.lwthad.utils.VerifyCode;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

@WebServlet(urlPatterns = "/VerifyCodeServlet")
public class VerifyCodeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        VerifyCode code = new VerifyCode();
        BufferedImage image = code.createImage();
        req.getSession().setAttribute("session_vcode",code.getText());

        VerifyCode.output(image, resp.getOutputStream());
    }
}
