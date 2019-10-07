package com.common;

public class User implements java.io.Serializable {
    public String getUserName() {
        return userName;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    private String userName;
    private String passwd;

    public User() {

    }
}
