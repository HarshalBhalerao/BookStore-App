package com.comp3350_group10.bookstore.persistence;

import com.comp3350_group10.bookstore.objects.IUser;

public interface IUserDatabase {
    IUser findUser(String userId);
    IUser insertUser(IUser user);
    IUser updateUser(IUser user);
    void deleteUser(IUser user);
}
