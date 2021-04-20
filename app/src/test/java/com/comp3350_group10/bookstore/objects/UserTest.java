package com.comp3350_group10.bookstore.objects;

import com.comp3350_group10.bookstore.persistence.UserType;

import junit.framework.TestCase;

public class UserTest extends TestCase {

    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {
    }

    /**
     * testGetRealName method: We enter a new user and check if the user is null or not.
     * If not
     */
    public void testGetRealName() {
        IUser user = new User("Daniel","duy.than@gihot.com"
                ,"111", UserType.Employee);
        assertNotNull(user);
        assertEquals("Daniel",user.getRealName());
    }

    public void testGetUserType() {
        IUser user = new User("Daniel","duy.than@gihot.com"
                ,"111", UserType.Employee);
        assertNotNull(user);
        assertEquals(UserType.Employee,user.getUserType());
    }

    public void testGetUserID() {
        IUser user = new User("Daniel","duy.than@gihot.com"
                ,"111", UserType.Employee);
        assertNotNull(user);
        assertEquals("duy.than@gihot.com",user.getUserID());
    }

    public void testGetPassword() {
        IUser user = new User("Daniel","duy.than@gihot.com"
                ,"111", UserType.Employee);
        assertNotNull(user);
        assertEquals("111",user.getPassword());
    }

    public void testSetUserID() {
        IUser user = new User("Daniel","duy.than@gihot.com"
                ,"111", UserType.Employee);
        assertNotNull(user);
        user.setUserID("duy.than@gmail.com");
        assertEquals("duy.than@gmail.com",user.getUserID());
    }

    public void testSetPassword() {
        IUser user = new User("Daniel","duy.than@gihot.com"
                ,"111", UserType.Employee);
        assertNotNull(user);
        user.setPassword("222");
        assertEquals("222",user.getPassword());
    }

    public void testSetPosition() {
        IUser user = new User("Daniel","duy.than@gihot.com"
                ,"111", UserType.Employee);
        assertNotNull(user);
        user.setPosition(UserType.Manager);
        assertEquals(UserType.Manager,user.getUserType());
    }

    public void testTestSetName() {
        IUser user = new User("Daniel","duy.than@gihot.com"
                ,"111", UserType.Employee);
        assertNotNull(user);
        user.setName("Duy Than");
        assertEquals("Duy Than",user.getRealName());
    }
}