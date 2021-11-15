package com.solution.lushkov.servlet;

import com.solution.lushkov.dao.impl.UserDaoImpl;
import com.solution.lushkov.dao.UserDao;
import com.solution.lushkov.entity.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(value = "/delete")
public class DeleteUserServlet extends HttpServlet {

    private static final String errorJsp = "/WEB-INF/view/deleteError.jsp";
    private UserDao userDao;

    @Override
    public void init() throws ServletException {
        userDao = new UserDaoImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        final Long user_Id = Long.parseLong(request.getParameter("userId"));
        HttpSession session = request.getSession();
        final String login = (String)session.getAttribute("login");
        User user = userDao.findById(user_Id);
        if (login.equals(user.getLogin())) {
            request.getRequestDispatcher(errorJsp).forward(request, response);
        } else {
            userDao.remove(user);
            response.sendRedirect("/adminPage");
        }
    }
}
