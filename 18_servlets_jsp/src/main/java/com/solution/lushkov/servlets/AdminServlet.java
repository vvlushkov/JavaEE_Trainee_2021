package com.solution.lushkov.servlets;

import com.solution.lushkov.dao.impl.RoleDaoImpl;
import com.solution.lushkov.dao.impl.UserDaoImpl;
import com.solution.lushkov.dao.RoleDao;
import com.solution.lushkov.dao.UserDao;
import com.solution.lushkov.entity.User;
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
        userDao = new UserDaoImpl();
        roleDao = new RoleDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<User> usersList = userDao.findAll();
        request.setAttribute("usersList", usersList);
        request.getRequestDispatcher(adminJsp).forward(request, response);
    }
}
