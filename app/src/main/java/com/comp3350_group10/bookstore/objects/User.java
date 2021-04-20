package com.comp3350_group10.bookstore.objects;


import com.comp3350_group10.bookstore.persistence.UserType;


import java.io.Serializable;

public class User implements IUser, Serializable
{

    private String realName;
    private String userID; //email
    private String password;
    private UserType position;

    //contructor that sets the variables with parameters
    public User(String name, String userId, String password, UserType type)
    {
        this.realName = name;
        this.userID = userId;
        this.password = password;
        this.position = type;
    }

    //returns the real name of this user
    public String getRealName() { return realName; }
    
    //returns the user type of this user
    public UserType getUserType() { return position; }

    //returns the user ID of this user
    public String getUserID() { return userID; }

    //returns the password of this user
    public String getPassword() { return password; }

    //set the ID of this user with the given input
    public void setUserID(String userId) { this.userID = userId; }

    //set the password of this user with the given input
    public void setPassword(String newPassword) { this.password = newPassword; }

    @Override
    public void setPosition(UserType type) {
        this.position = type;
    }

    @Override
    public void setName(String name) {
        this.realName=name;
    }

}
