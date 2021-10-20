package com.solution.lushkov.dbTags;

import com.solution.lushkov.entityDaoPostgres.JdbcPostgresRoleDao;
import com.solution.lushkov.interfacesDaoPostgres.RoleDao;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.*;

public class GetRoleNameTag extends TagSupport {
    private Long role_Id;
    RoleDao roleDao = new JdbcPostgresRoleDao();

    public void setRole_Id(Long role_Id) {
        this.role_Id = role_Id;
    }

    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        try {
            String roleName = roleDao.findById(role_Id).getName();
            out.print(roleName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }
}