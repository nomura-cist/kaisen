package com.example.kaisen.model;

import javax.validation.constraints.Pattern;

public class Authn {

    private String userId;
    private String passphrase;
    private String userName;
    private String userRole;

    public Authn(String userId,String passphrase,String userName,String userRole){
        this.setUserId(userId);
        this.setPassphrase(passphrase);
        this.setUserName(userName);
        this.setUserRole(userRole);
    }


    public Authn(){
        this.setUserId("");
        this.setPassphrase("");
        this.setUserName("");
        this.setUserRole("");
    }


    public String getUserid() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassphrase() {
        return passphrase;
    }

    public void setPassphrase(String passphrase) {
        this.passphrase = passphrase;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
}
