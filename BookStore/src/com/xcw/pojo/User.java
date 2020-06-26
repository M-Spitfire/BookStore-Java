package com.xcw.pojo;

public class User {
    private int id;//id
    private String username;//用户名
    private String password;//密码
    private String email;//邮箱
    private String code;//验证码
    private String action;//行为，（登录、注册等）

    public User(int id, String username, String password, String email, String code, String action) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.code = code;
        this.action = action;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", code='" + code + '\'' +
                ", action='" + action + '\'' +
                '}';
    }

    public User() {
    }
}
