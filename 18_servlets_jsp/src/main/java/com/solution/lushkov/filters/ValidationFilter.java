package com.solution.lushkov.filters;

import com.solution.lushkov.entityDaoPostgres.JdbcPostgresRoleDao;
import com.solution.lushkov.entityDaoPostgres.JdbcPostgresUserDao;
import com.solution.lushkov.interfacesDaoPostgres.RoleDao;
import com.solution.lushkov.interfacesDaoPostgres.UserDao;
import com.solution.lushkov.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebFilter(urlPatterns = {"/adminPage", "/updateUser", "/createUser"})
public class ValidationFilter implements Filter {

    private static final String errorJsp = "/WEB-INF/view/valError.jsp";

    RoleDao roleDao;
    UserDao userDao;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        roleDao = new JdbcPostgresRoleDao();
        userDao = new JdbcPostgresUserDao();
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpSession session = request.getSession(false);
        final String login = (String)session.getAttribute("login");
        final String password = (String)session.getAttribute("password");

        if (validateUser(login, password)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else if (!validateUser(login, password)) {
            servletRequest.setAttribute("errMsg", "User not valid. Page is available only for admins.");
            RequestDispatcher rd = servletRequest.getRequestDispatcher(errorJsp);
            rd.include(servletRequest, servletResponse);
        }
    }

    private boolean validateUser(String login, String password) {
        List<User> usersList = userDao.findAll();
        for (User user : usersList) {
            if ((user.getLogin().equals(login))
                    && (user.getPassword().equals(password))) {
                Long roleId = userDao.findByLogin(login).getRoleId();
                if ((roleDao.findById(roleId).getName().equalsIgnoreCase("admin"))
                        || (roleDao.findById(roleId).getName()
                        .equalsIgnoreCase("administrator")))
                return true;
            }
        }
        return false;
    }
}
