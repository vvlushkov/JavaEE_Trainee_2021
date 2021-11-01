package com.solution.lushkov.dbTags;

import com.solution.lushkov.dao.impl.RoleDaoImpl;
import com.solution.lushkov.dao.RoleDao;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.*;

public class GetRoleNameTag extends TagSupport {
    private Long roleId;
    RoleDao roleDao = new RoleDaoImpl();

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        try {
            String roleName = roleDao.findById(roleId).getName();
            out.print(roleName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }
}