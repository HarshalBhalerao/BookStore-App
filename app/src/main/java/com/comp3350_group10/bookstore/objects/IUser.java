package com.comp3350_group10.bookstore.objects;

import com.comp3350_group10.bookstore.persistence.UserType;

public interface IUser {
    String getRealName();

    //returns the user type of this user
    UserType getUserType();

    //returns the user ID of this user
    String getUserID();

    //returns the password of this user
    String getPassword();

    //set the ID of this user with the given input
    void setUserID(String userId);

    //set the password of this user with the given input
    void setPassword(String newPassword);

    void setPosition(UserType type);

    void setName(String name);
}
