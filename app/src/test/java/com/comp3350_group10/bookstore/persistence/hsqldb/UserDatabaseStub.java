package com.comp3350_group10.bookstore.persistence.hsqldb;

import com.comp3350_group10.bookstore.objects.User;
import com.comp3350_group10.bookstore.objects.IUser;
import com.comp3350_group10.bookstore.persistence.IUserDatabase;
import com.comp3350_group10.bookstore.persistence.UserType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserDatabaseStub implements IUserDatabase {

    private final List<IUser> userList;

    public UserDatabaseStub(){
        this.userList = new ArrayList<>();

        userList.add(new User("Daniel","duy.than@gihot.com","123456789", UserType.Employee));
        userList.add(new User("Keven","Kevin@gmail.com","987654321", UserType.Manager));
        userList.add(new User("Harshal","Harshal@myumanitoba.ca","111111111", UserType.Manager));
        userList.add(new User("Matt","Matt@yahoo.com","222222222", UserType.Employee));
        userList.add(new User("Animesh","Animesh@outlook.com","333333333", UserType.Employee));
        userList.add(new User("Darwait","Darwait@gmail.com","444444444", UserType.Employee));
    }

    @Override
    /**
     * findUser test method: Iterates through the entire userList and gets the user whose userId is equal to the one we want
     * then we return it. In case the user is not found then we return null.
     */
    public IUser findUser(String userId){
        for(int i = 0;i < userList.size(); i++){
            if(userList.get(i).getUserID().equals(userId)) {
                return userList.get(i);
            }
        }
        return null;
    }

    public List<IUser> getUsers(){
        return Collections.unmodifiableList(userList);
    }

    /**
     * insertUser test method: Adds user to the userList
     * @param user
     * @return
     */
    @Override
    public IUser insertUser(IUser user){
        userList.add(user);
        return user;
    }

    /**
     * updateUser test method: Finds the user which needs to be updated, sets the user and returns user.
     * @param user
     * @return
     */
    @Override
    public IUser updateUser(IUser user){
        int index;

        index = userList.indexOf(user);
        if(index >= 0){
            userList.set(index, user);
        }
        return user;
    }

    /**
     * deleteUser test method: Finds the user to be deleted and removes them from the list
     * @param user
     */
    @Override
    public void deleteUser(IUser user) {
        int index;
        index = userList.indexOf(user);
        if(index >= 0){
            userList.remove(user);
        }
    }
}
