package com.solution.lushkov.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(value = "/logout")
public class LogOutServlet extends HttpServlet {
    private static final String indexJsp = "/WEB-INF/view/authorization.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if(session != null) {
            session.invalidate();
        }
        request.getRequestDispatcher(indexJsp).forward(request,response);
    }
}