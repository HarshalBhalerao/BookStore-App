package com.comp3350_group10.bookstore.business;

import com.comp3350_group10.bookstore.Exceptions.ChangePasswordException;
import com.comp3350_group10.bookstore.Exceptions.CreateUserErrorException;
import com.comp3350_group10.bookstore.Exceptions.DifferentPasswordException;
import com.comp3350_group10.bookstore.Exceptions.PersistenceException;
import com.comp3350_group10.bookstore.Exceptions.UserNotFoundException;
import com.comp3350_group10.bookstore.application.Main;
import com.comp3350_group10.bookstore.objects.IUser;
import com.comp3350_group10.bookstore.objects.User;
import com.comp3350_group10.bookstore.persistence.IUserDatabase;
import com.comp3350_group10.bookstore.persistence.UserType;
import com.comp3350_group10.bookstore.persistence.hsqldb.UserDatabase;
import com.comp3350_group10.bookstore.utils.TestUtils;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

public class UserDataHandlerIT extends TestCase {

    private UserDataHandler userDataHandler;
    private File tempDB;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        this.tempDB = TestUtils.copyDB();
        final IUserDatabase persistence = new UserDatabase(this.tempDB.getAbsolutePath().replace(".script",""));
        this.userDataHandler = new UserDataHandler(persistence);
    }
    @After
    public void tearDown() throws Exception {
        this.tempDB.delete();
    }

    @Test
    public void checkDbPath(){
        String MainPath = Main.getDBPath();
        String dbPath = this.tempDB.getAbsolutePath().replace(".script","");
        assertEquals("DB is not set properly",dbPath,MainPath);
    }
    @Test
    public void testGetCurrentUser() {
        UserDataHandler.currentUser = new User("Daniel","duy.than@gihot.com","123456789", UserType.Employee);
        IUser user = userDataHandler.getCurrentUser();
        assertNotNull("the user should not be null",user);
        assertEquals("name should be Daniel","Daniel",user.getRealName());
        assertEquals("userId should be duy.than@gihot.com","duy.than@gihot.com",user.getUserID());
        assertEquals("password should be 123456789","123456789",user.getPassword());
        assertEquals("usertype should be Employee",UserType.Employee,user.getUserType());
    }
    //without throwing exception
    @Test
    public void testLogIn() {
        try{
            userDataHandler.logIn("duy.than@gihot.com","123456789");
        }
        catch (UserNotFoundException exception){
            assertEquals("it shouldn't reach the catch statement",exception.getMessage());
        }
        catch (DifferentPasswordException exception){
            assertEquals("it shouldn't reach the catch statement",exception.getMessage());
        }

    }
    //throw exception for id not found
    @Test
    public void testLogInThrowUserNotFoundException(){
        try{
            userDataHandler.logIn("mybad@outlook.com","123456789");
        }
        catch (UserNotFoundException exception){
            assertEquals("User Not Found",exception.getMessage());
        }
    }
    //throw exception for password not correct
    @Test
    public void testLogInThrowDifferentPasswordException(){
        try{
            userDataHandler.logIn("duy.than@gihot.com","111111111");
        }
        catch (DifferentPasswordException exception){
            assertEquals("Different passwords, couldn't confirm!!",exception.getMessage());
        }
    }
    @Test
    public void testLogOut() {
        userDataHandler.logOut();
        assertNull("the current User should be null",userDataHandler.getCurrentUser());
    }
    @Test
    public void testChangePasswordUserNullException() {
        //ChangePasswordException
        try{
            userDataHandler.changePassword("123456789","111111111","111111111");
        }
        catch (ChangePasswordException exception){
            assertEquals("User must be logged in",exception.getMessage());
        }
    }
    @Test
    public void testChangePasswordWrongOldPasswordException(){
        IUser user = new User("Daniel","duy.than@gihot.com","123456789", UserType.Employee);
        UserDataHandler.currentUser = user;
        try{
            userDataHandler.changePassword("12345678","22222222","33333333");
        }
        catch (ChangePasswordException exception){
            assertEquals("Old password does not match",exception.getMessage());
        }
    }
    @Test
    public void testChangePasswordShortPasswordException(){
        IUser user = new User("Daniel","duy.than@gihot.com","123456789", UserType.Employee);
        UserDataHandler.currentUser = user;
        try{
            userDataHandler.changePassword("123456789","1234567","1234567");
        }
        catch (ChangePasswordException exception){
            assertEquals("Password length must be at least 8 characters",exception.getMessage());
        }
    }
    @Test
    public void testChangePasswordNotMatchNewException(){
        IUser user = new User("Daniel","duy.than@gihot.com","123456789", UserType.Employee);
        UserDataHandler.currentUser = user;
        try{
            userDataHandler.changePassword("123456789","1234567890","1234567");
        }
        catch (ChangePasswordException exception){
            assertEquals("New password does not match with confirmation",exception.getMessage());
        }
    }
    @Test
    public void testChangePassword(){
        try{
            IUser user = new User("Daniel","duy.than@gihot.com","123456789", UserType.Employee);
            UserDataHandler.currentUser = user;
            boolean success = userDataHandler.changePassword("123456789","1234567890","1234567890");
            assertEquals("It should change password successfully",true,success);
        }
        catch (ChangePasswordException exception){
            assertEquals("it shouldn't reach the catch statement",exception.getMessage());
        }

    }
    @Test
    public void testCreateNewUserNameException() {
        try{
            IUser user = userDataHandler.createNewUser("","zigs@gmail.com","123456789",false);
        }
        catch (CreateUserErrorException exception){
            assertEquals("Name cannot be empty",exception.getMessage().trim());
        }
    }
    @Test
    public void testCreateNewUserEmail_1_Exception() {
        try{
            IUser user = userDataHandler.createNewUser("Duy","","123456789",false);
        }
        catch (CreateUserErrorException exception){
            assertEquals("Email cannot be empty",exception.getMessage().trim());
        }
    }
    @Test
    public void testCreateNewUserEmail_2_Exception() {
        try{
            IUser user = userDataHandler.createNewUser("Duy","zigszigs","123456789",false);
        }
        catch (CreateUserErrorException exception){
            assertEquals("Email is not valid",exception.getMessage().trim());
        }
    }
    @Test
    public void testCreateNewUserPassword_1_Exception() {
        try{
            IUser user = userDataHandler.createNewUser("Duy","zig123321@gmail.com","",false);
        }
        catch (CreateUserErrorException exception){
            assertEquals("Password cannot be empty",exception.getMessage().trim());
        }
    }
    @Test
    public void testCreateNewUserPassword_2_Exception() {
        try{
            IUser user = userDataHandler.createNewUser("Duy","zig123321@gmail.com","1234567",false);
        }
        catch (CreateUserErrorException exception){
            assertEquals("Password cannot be shorter than 8 characters",exception.getMessage().trim());
        }
    }
    @Test
    public void testCreateNewUser() {
        try{
            IUser user = userDataHandler.createNewUser("Duy","zig123321@gmail.com","123456789",false);
        }
        catch (CreateUserErrorException exception){
            assertEquals("It shouldn't reach the catch statement",exception.getMessage().trim());
        }
    }

    public void testDeleteUser() {
        try{
            IUser user = userDataHandler.createNewUser("Duy","zig123321@gmail.com","123456789",false);
            userDataHandler.deleteUser("zig123321@gmail.com");
            try {
                userDataHandler.logIn("zig123321@gmail.com", "123456789");
                fail("UserNotFoundException should be thrown");
            }
            catch(UserNotFoundException e){
                assertTrue("User was successfully deleted", true);
            }
        }
        catch(PersistenceException e){
            fail("DeleteUser does not work correctly");
        }
    }
}