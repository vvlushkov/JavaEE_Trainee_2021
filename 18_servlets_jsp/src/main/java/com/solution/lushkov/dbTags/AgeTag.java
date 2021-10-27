package com.solution.lushkov.dbTags;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class AgeTag extends TagSupport {

    final static Logger LOG = LogManager.getLogger(AgeTag.class);
    private Date age;
    public void setAge(Date age) {
        this.age = age;
    }

    public int doStartTag() {
        JspWriter out = pageContext.getOut();
        try {

            out.print(getAge(age));
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
        return SKIP_BODY;
    }

    private int getAge(Date date) {
        return (int) ChronoUnit.YEARS.between(
                date.toLocalDate(), LocalDate.now());
    }
}
