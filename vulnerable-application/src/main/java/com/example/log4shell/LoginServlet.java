package com.example.log4shell;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet(name = "loginServlet", value = "/login")
public class LoginServlet extends HttpServlet {

    Logger logger = LogManager.getLogger(LoginServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String userName = req.getParameter("uname");
        String password = req.getParameter("password");

        // vulnerable code
        String agent = req.getHeader("User-Agent");
        logger.error(agent);
        logger.info("Log some info here");
        logger.warn("Log some warn here");

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        if (userName.equals("admin") && password.equals("password")) {
            out.println("Welcome Back Admin");
        } else {
            out.println("<code> the password you entered was invalid, <u> we will log your information </u> </code>");
        }
    }

    public void destroy() {
    }
}