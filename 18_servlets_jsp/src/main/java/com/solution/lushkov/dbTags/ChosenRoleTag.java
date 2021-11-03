package com.solution.lushkov.dbTags;

import com.solution.lushkov.dao.impl.RoleDaoImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class ChosenRoleTag extends TagSupport {

    final static Logger LOG = LogManager.getLogger(ChosenRoleTag.class);

    private String roleName;
    private Long userRoleId;
    private final RoleDaoImpl roleDao = new RoleDaoImpl();

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public void setUserRoleId(Long userRoleId) {
        this.userRoleId = userRoleId;
    }

    public int doStartTag() {
        JspWriter out = pageContext.getOut();
        try {
            String result;
            String tempRole = roleDao.findById(userRoleId).getName();

            if (tempRole.equals(roleName)) {
                result = "selected";
            } else {
                result = "";
            }
            out.print(result);
        } catch (IOException e) {
            LOG.info(e.getMessage(), e);
        }
        return SKIP_BODY;
    }
}
