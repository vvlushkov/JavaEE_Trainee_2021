package com.solution.lushkov.servlets;

import com.solution.lushkov.entityDaoPostgres.JdbcPostgresRoleDao;
import com.solution.lushkov.entityDaoPostgres.JdbcPostgresUserDao;
import com.solution.lushkov.interfacesDaoPostgres.RoleDao;
import com.solution.lushkov.interfacesDaoPostgres.UserDao;
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
        userDao = new JdbcPostgresUserDao();
        roleDao = new JdbcPostgresRoleDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher(userJsp).forward(request, response);
    }

}
