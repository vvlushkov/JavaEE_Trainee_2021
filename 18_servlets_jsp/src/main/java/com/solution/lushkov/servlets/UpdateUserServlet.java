package com.solution.lushkov.servlets;

import com.solution.lushkov.dao.impl.RoleDaoImpl;
import com.solution.lushkov.dao.impl.UserDaoImpl;
import com.solution.lushkov.dao.RoleDao;
import com.solution.lushkov.dao.UserDao;
import com.solution.lushkov.entity.Role;
import com.solution.lushkov.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet(value = "/updateUser")
public class UpdateUserServlet extends HttpServlet {

    private static final String updateJsp = "/WEB-INF/view/updateUser.jsp";

    UserDao userDao;
    RoleDao roleDao;
    Long user_Id;

    @Override
    public void init() throws ServletException {
        userDao = new UserDaoImpl();
        roleDao = new RoleDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        user_Id = Long.parseLong(request.getParameter("userId"));
        User user = userDao.findById(user_Id);
        request.setAttribute("user", user);

        HttpSession session = request.getSession();
        session.setAttribute("userToUpdate", user);

        List<Role> roleList = roleDao.findAll();
        request.setAttribute("roleList", roleList);

        request.getRequestDispatcher(updateJsp).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        final User user = (User)session.getAttribute("userToUpdate");

        user.setEmail(request.getParameter("email"));
        if (request.getParameter("password") != null) {
            user.setPassword(request.getParameter("password"));
        }
        user.setFirstName(request.getParameter("firstName"));
        user.setLastName(request.getParameter("lastName"));
        Date date = Date.valueOf(request.getParameter("birthday"));
        user.setBirthday(date);
        user.setRoleId(Long.parseLong(request.getParameter("chosenRole")));
        userDao.update(user);
        if (session.getAttribute("login").equals(user.getLogin())) {
            session.setAttribute("password", user.getPassword());
            session.setAttribute("userFirstName", user.getFirstName());
            session.setAttribute("userLastName", user.getLastName());
        }
        response.sendRedirect("/adminPage");
    }
}
