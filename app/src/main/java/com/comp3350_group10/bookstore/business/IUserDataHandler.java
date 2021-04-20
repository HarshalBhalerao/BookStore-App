package com.comp3350_group10.bookstore.business;

import com.comp3350_group10.bookstore.Exceptions.ChangePasswordException;
import com.comp3350_group10.bookstore.Exceptions.CreateUserErrorException;
import com.comp3350_group10.bookstore.Exceptions.DifferentPasswordException;
import com.comp3350_group10.bookstore.Exceptions.PersistenceException;
import com.comp3350_group10.bookstore.Exceptions.UserNotFoundException;
import com.comp3350_group10.bookstore.objects.IUser;


public interface IUserDataHandler {
    //function to logout the current user
    void logOut();

    void logIn(String email, String password) throws UserNotFoundException, DifferentPasswordException;

    //function to changePassword for the logged in user
    boolean changePassword(String oldPw, String newPw, String confirmNewPw) throws ChangePasswordException;

    IUser createNewUser(String name, String email, String password, boolean isManager) throws CreateUserErrorException;

    IUser getCurrentUser();

    void deleteUser(String deleteID) throws PersistenceException;

    void forgotPassword(String email) throws UserNotFoundException;
}
