package com.example.reflectiondemo.bean;

/**
 * Created by Administrator on 2018/5/11.
 */

public class User {

    private String userName;
    private int useAge;

    public User(String userName, int useAge) {
        this.userName = userName;
        this.useAge = useAge;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUseAge() {
        return useAge;
    }

    public void setUseAge(int useAge) {
        this.useAge = useAge;
    }

    @Override
    public String toString() {
        return "User : name = " + userName + " age = " + useAge;
    }
}
