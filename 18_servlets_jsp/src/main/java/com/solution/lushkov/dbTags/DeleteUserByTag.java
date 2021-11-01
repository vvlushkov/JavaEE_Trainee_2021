package com.solution.lushkov.dbTags;

import com.solution.lushkov.entityDaoPostgres.JdbcPostgresUserDao;
import com.solution.lushkov.interfacesDaoPostgres.UserDao;
import com.solution.lushkov.entity.User;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class DeleteUserByTag extends TagSupport {
    private Long user_Id;

    UserDao userDao = new JdbcPostgresUserDao();

    public void setUser_Id(Long user_Id) {
        this.user_Id = user_Id;
    }

    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        try {
            User user = new User();
            user.setId(user_Id);
            userDao.remove(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }
}