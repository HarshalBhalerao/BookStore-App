package com.comp3350_group10.bookstore.persistence.hsqldb;
import com.comp3350_group10.bookstore.Exceptions.PersistenceException;
import com.comp3350_group10.bookstore.objects.User;
import com.comp3350_group10.bookstore.objects.IUser;
import com.comp3350_group10.bookstore.persistence.IUserDatabase;
import com.comp3350_group10.bookstore.persistence.UserType;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDatabase implements IUserDatabase {

    private final String dbPath;
    private final String MANAGER="MANAGER";
    private final String EMPLOYEE="EMPLOYEE";

    private List<IUser> userList;

    /**
     * UserDatabase: constructor that takes dbPath as parameter
     */
    public UserDatabase(final String dbPath){
        this.dbPath = dbPath;
    }

    /**
     * Connection method: Establish connection with the database
     * @return Connection
     * @throws SQLException
     */
    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:"+ dbPath+ ";shutdown=true", "SA", "");
    }

    private User createUser(final ResultSet rs) throws SQLException{
        final String name = rs.getString("name");
        final String userId = rs.getString("userId");
        final String password = rs.getString("password");
        final String position = rs.getString("position");
        return new User(name,userId,password, position.equals(MANAGER) ? UserType.Manager:UserType.Employee);
    }

    /**
     * Searches user from the database with the given ID
     * @param userId
     */
    @Override
    public IUser findUser(String userId) {
        //retrieve getUsers first
        userList = getUsers();
        if(userList.size()==0) {
            return null;
        }
        for(int i=0;i<userList.size();i++){
            if(userList.get(i).getUserID().toLowerCase().equals(userId.toLowerCase()))
                return userList.get(i);
        }
        return null;
    }

    /**
     * Return every user in the database
     */
    public List<IUser> getUsers() {
        final List<IUser> usersInfo = new ArrayList<>();

        try (final Connection conn = connection()){
            final Statement stmt = conn.createStatement();
            final ResultSet rtst = stmt.executeQuery("SELECT * FROM USERS");
            while(rtst.next()){
                final User user = createUser(rtst);
                usersInfo.add(user);
            }
            rtst.close();
            stmt.close();
        }
        catch(final SQLException e){
            throw new PersistenceException("can't get the users.");
        }
        return usersInfo;
    }

    @Override
    /**
     * Insert the input user to the database
     */
    public IUser insertUser(IUser user) {
        try(final Connection conn = connection()) {
            final PreparedStatement pstmt = conn.prepareStatement("INSERT INTO USERS VALUES(?,?,?,?)");
            pstmt.setString(1, user.getRealName());
            pstmt.setString(2, user.getUserID());
            pstmt.setString(3, user.getPassword());
            String position= user.getUserType()==UserType.Employee?EMPLOYEE:MANAGER;
            pstmt.setString(4, position);
            pstmt.executeUpdate();
            return user;
        }
        catch(final SQLException e){
            throw new PersistenceException("can't insert the user.");
        }
    }

    @Override
    /**
     * Updates the database with the updated user as input
     */
    public IUser updateUser(IUser user){
        try (final Connection conn = connection()){
            final PreparedStatement pstmt =
                    conn.prepareStatement("UPDATE USERS SET Name=?,password=?, position=? WHERE userId=?");
            pstmt.setString(1, user.getRealName());
            pstmt.setString(2, user.getPassword());
            String position = user.getUserType()==UserType.Employee?EMPLOYEE:MANAGER;
            pstmt.setString(3, position);
            pstmt.setString(4,user.getUserID());
            pstmt.executeUpdate();
            return user;
        }
        catch(final SQLException e){
            throw new PersistenceException("can't update the user.");
        }
    }

    @Override
    /**
     * Deletes the input user
     */
    public void deleteUser(IUser user){
        try (final Connection conn = connection()){
            final PreparedStatement pstmt =
                    conn.prepareStatement("DELETE FROM USERS WHERE userId=?");
            pstmt.setString(1, user.getUserID());
            pstmt.executeUpdate();
        }
        catch(final SQLException e){
            throw new PersistenceException("can't delete the user");
        }
    }
}
