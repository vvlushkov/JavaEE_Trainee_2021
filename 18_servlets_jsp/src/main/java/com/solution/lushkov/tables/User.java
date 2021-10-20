package com.solution.lushkov.tables;

import java.sql.Date;
import java.util.Objects;

/**
 * Class for table of Users.
 *
 * @author Victor Lushkov
 * @version 1.0
 */
public class User {
    /** ID of User */
    private Long id;
    /** Unique login of User */
    private String login;
    /** User`s Password */
    private String password;
    /** User`s E-mail */
    private String email;
    /** First name */
    private String firstName;
    /** Last name */
    private String lastName;
    /** Date of birthday */
    private Date birthday;
    /** The Foreign Key from Role (entity) */
    private Long roleId;

    /**
     * Basic empty constructor.
     */
    public User() {
    }

    public User(Long id, String login, String password, String email,
                String firstName, String lastName, String birthday, Long roleId) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = Date.valueOf(birthday);
        this.roleId = roleId;
    }

    public User(String login, String password, String email,
                String firstName, String lastName, String birthday, Long roleId) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = Date.valueOf(birthday);
        this.roleId = roleId;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public Date getBirthday() {
        return birthday;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    public Long getRoleId() {
        return roleId;
    }
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", \n\t\t\t\t\t\tfirstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthday=" + birthday +
                ", roleId=" + roleId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(login, user.login)
                && Objects.equals(password, user.password) && Objects.equals(email, user.email)
                && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName)
                && Objects.equals(birthday, user.birthday) && Objects.equals(roleId, user.roleId);
    }

}
