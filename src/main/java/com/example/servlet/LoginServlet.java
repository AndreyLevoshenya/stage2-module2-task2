package com.example.servlet;

import com.example.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final String LOGIN_PAGE = "/login.jsp";
    private static final String HELLO_PAGE = "/user/hello.jsp";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getSession().getAttribute("user") != null) {
            resp.sendRedirect(HELLO_PAGE);
        }
        else {
            req.getRequestDispatcher(LOGIN_PAGE).forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user = req.getParameter("login");
        String password = req.getParameter("password");
        if(!Users.getInstance().getUsers().contains(user) || password == null || password.isEmpty()) {
            req.getRequestDispatcher(LOGIN_PAGE).forward(req, resp);
        }
        else {
            req.getSession().setAttribute("user", "user");
            resp.sendRedirect(HELLO_PAGE);
        }
    }
}
