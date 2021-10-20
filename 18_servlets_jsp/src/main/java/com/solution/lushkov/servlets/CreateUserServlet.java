package com.solution.lushkov.servlets;

import com.solution.lushkov.entityDaoPostgres.JdbcPostgresRoleDao;
import com.solution.lushkov.entityDaoPostgres.JdbcPostgresUserDao;
import com.solution.lushkov.interfacesDaoPostgres.RoleDao;
import com.solution.lushkov.interfacesDaoPostgres.UserDao;
import com.solution.lushkov.tables.Role;
import com.solution.lushkov.tables.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet(value = "/createUser")
public class CreateUserServlet extends HttpServlet {

    private static final String createJsp = "/WEB-INF/view/createUser.jsp";

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

        List<Role> roleList = roleDao.findAll();
        request.setAttribute("roleList", roleList);

        request.getRequestDispatcher(createJsp).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User user = new User();
        user.setLogin(request.getParameter("newLogin"));
        user.setPassword(request.getParameter("password"));
        user.setEmail(request.getParameter("email"));
        user.setFirstName(request.getParameter("firstName"));
        user.setLastName(request.getParameter("lastName"));
        Date date = Date.valueOf(request.getParameter("birthday"));
        user.setBirthday(date);
        String roleName = request.getParameter("chosenRole");
        Role role = roleDao.findByName(roleName);
        user.setRoleId(role.getId());

        userDao.create(user);
        response.sendRedirect("http://localhost:8080/adminPage");
    }
}
