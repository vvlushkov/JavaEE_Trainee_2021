package com.solution.lushkov.servlets;

import com.solution.lushkov.entityDaoPostgres.JdbcPostgresRoleDao;
import com.solution.lushkov.entityDaoPostgres.JdbcPostgresUserDao;
import com.solution.lushkov.interfacesDaoPostgres.RoleDao;
import com.solution.lushkov.interfacesDaoPostgres.UserDao;
import com.solution.lushkov.entity.User;
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
    private static final String indexJsp = "/WEB-INF/view/authorization.jsp";
    UserDao userDao;
    RoleDao roleDao;
    boolean isValidate;

    @Override
    public void init() throws ServletException {
        userDao = new JdbcPostgresUserDao();
        roleDao = new JdbcPostgresRoleDao();
        isValidate = true;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("isValidate", isValidate);
        request.getRequestDispatcher(indexJsp).forward(request, response);
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
            Long roleId = userDao.findByLogin(login).getRoleId();
            if ((roleDao.findById(roleId).getName().equalsIgnoreCase("admin"))
                    || (roleDao.findById(roleId).getName()
                    .equalsIgnoreCase("administrator"))) {
                response.sendRedirect("/adminPage");
            } else {
                response.sendRedirect("/userPage");
            }
        } else {
            isValidate = false;
            request.setAttribute("isValidate", isValidate);
            request.getRequestDispatcher(indexJsp).forward(request, response);
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
