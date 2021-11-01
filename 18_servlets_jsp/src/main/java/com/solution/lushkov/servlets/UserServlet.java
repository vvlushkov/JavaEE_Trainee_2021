package com.solution.lushkov.servlets;

import com.solution.lushkov.dao.impl.RoleDaoImpl;
import com.solution.lushkov.dao.impl.UserDaoImpl;
import com.solution.lushkov.dao.RoleDao;
import com.solution.lushkov.dao.UserDao;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/userPage")
public class UserServlet extends HttpServlet {

    private static final String userJsp = "/WEB-INF/view/user.jsp";

    UserDao userDao;
    RoleDao roleDao;

    @Override
    public void init() throws ServletException {
        userDao = new UserDaoImpl();
        roleDao = new RoleDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher(userJsp).forward(request, response);
    }

}
