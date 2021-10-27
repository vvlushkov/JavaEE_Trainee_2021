package com.solution.lushkov.servlets;

import com.solution.lushkov.entityDaoPostgres.JdbcPostgresRoleDao;
import com.solution.lushkov.entityDaoPostgres.JdbcPostgresUserDao;
import com.solution.lushkov.interfacesDaoPostgres.RoleDao;
import com.solution.lushkov.interfacesDaoPostgres.UserDao;
import com.solution.lushkov.tables.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/adminPage")
public class AdminServlet extends HttpServlet {

    private static final String adminJsp = "/WEB-INF/view/administration.jsp";

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
        List<User> usersList = userDao.findAll();
        request.setAttribute("usersList", usersList);
        request.getRequestDispatcher(adminJsp).forward(request, response);
    }
}
