package com.solution.lushkov.servlet;

import com.solution.lushkov.dao.impl.RoleDaoImpl;
import com.solution.lushkov.dao.impl.UserDaoImpl;
import com.solution.lushkov.util.HibernateUtil;
import com.solution.lushkov.dao.RoleDao;
import com.solution.lushkov.dao.UserDao;
import com.solution.lushkov.entity.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/authorization")
public class SignInServlet extends HttpServlet {

    private static final String authorizationJsp = "/WEB-INF/view/authorization.jsp";

    private UserDao userDao;
    private RoleDao roleDao;

    @Override
    public void init() throws ServletException {
        HibernateUtil.getSessionFactory();
        userDao = new UserDaoImpl();
        roleDao = new RoleDaoImpl();
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("isValid", true);
        request.getRequestDispatcher(authorizationJsp).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        final String login = request.getParameter("login");
        final String password = request.getParameter("password");
        HttpSession session = request.getSession();
        session.setAttribute("login", login);
        session.setAttribute("password", password);
        User user = userDao.findByLogin(login);
        if (validateUser(login, password)) {
            session.setAttribute("userFirstName", user.getFirstName());
            session.setAttribute("userLastName", user.getLastName());
            Long roleId = userDao.findByLogin(login).getRole().getRole_id();
            if ((roleDao.findById(roleId).getRole_name().equalsIgnoreCase("admin"))
                    || (roleDao.findById(roleId).getRole_name()
                    .equalsIgnoreCase("administrator"))) {
                response.sendRedirect("/adminPage");
            } else {
                response.sendRedirect("/userPage");
            }
        } else {
            request.setAttribute("isValid", false);
            request.getRequestDispatcher(authorizationJsp).forward(request, response);
        }
    }

    private boolean validateUser(String login, String password) {
        List<User> usersList = userDao.findAll();
        User user = userDao.findByLogin(login);
        if ((user != null) && (usersList.contains(user))) {
            return user.getPassword().equals(password);
        }
        return false;
    }
}