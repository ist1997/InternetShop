package dao;

import db.DatabaseConnector;
import model.Role;
import model.User;
import org.apache.log4j.Logger;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    private static final String GET_USER_METHOD_QUERY = "SELECT * FROM internetshop.users WHERE login=? AND password=?";
    private static final String IS_USER_EXIST_METHOD_QUERY = "SELECT * FROM internetshop.users WHERE login=?";
    private static final String ADD_METHOD_QUERY = "INSERT INTO internetshop.users (login, password, email, role_id) VALUES (?, ?, ?, ?)";
    private static final String GET_USER_ROLE_METHOD_QUERY = "SELECT * FROM internetshop.roles WHERE id=?";
    private static final String DELETE_METHOD_QUERY = "DELETE FROM internetshop.users WHERE id=?";
    private static final String GET_ALL_USERS_METHOD_QUERY = "SELECT * FROM internetshop.users";
    private static final String DATABASE_NAME = "internetshop";
    private static final Logger logger = Logger.getLogger(UserDao.class);

    public static void delete(int id) {
        try (Connection connection = DatabaseConnector.getConnection(DATABASE_NAME)){
            PreparedStatement ps = connection.prepareStatement(DELETE_METHOD_QUERY);
            ps.setInt(1, id);
            ps.executeUpdate();
            logger.debug("SQL query for delete method: " + DELETE_METHOD_QUERY);
        } catch (SQLException e) {
            logger.error("Can`t connect to database", e);
        }
    }

    public User getUserFromDatabase(String login, String password) {
        User user = new User();
        try (Connection connection = DatabaseConnector.getConnection(DATABASE_NAME)) {
            PreparedStatement ps = connection.prepareStatement(GET_USER_METHOD_QUERY);
            ps.setString(1, login);
            ps.setString(2, password);
            logger.debug("SQL query for getUserFromDatabase method: " + GET_USER_METHOD_QUERY);
            ResultSet getUser = ps.executeQuery();
            if (getUser.next()) {
                long num = getUser.getLong(5);
                user = new User(
                        Long.valueOf(getUser.getString(1)),
                        getUser.getString(2),
                        getUser.getString(3),
                        getUser.getString(4),
                        Role.values()[(int) num - 1]);
            }
        } catch (SQLException e) {
            logger.error("Can`t connect to database", e);
        }
        return user;
    }

    public boolean isUserExistsInDatabase(User user) {
        try (Connection connection = DatabaseConnector.getConnection(DATABASE_NAME)) {
            PreparedStatement ps = connection.prepareStatement(IS_USER_EXIST_METHOD_QUERY);
            ps.setString(1, user.getLogin());
            logger.debug("SQL query for isUserExistsInDatabase method: " + IS_USER_EXIST_METHOD_QUERY);
            ResultSet getUser = ps.executeQuery();
            if (getUser.next()) {
                return true;
            }
        } catch (SQLException e) {
            logger.error("Can`t connect to database", e);
        }
        return false;
    }

    public static void addUserToDatabase(User user) {
        try (Connection connection = DatabaseConnector.getConnection(DATABASE_NAME)) {
            PreparedStatement ps = connection.prepareStatement(ADD_METHOD_QUERY);
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());
            ps.setLong(4, user.getRole().ordinal() + 1);
            logger.debug("SQL query for addUserToDatabase method: " + ADD_METHOD_QUERY);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can`t connect to database", e);
        }
    }

    public static String getUserRole(User user) {
        String role = "";
        try (Connection connection = DatabaseConnector.getConnection(DATABASE_NAME)) {
            PreparedStatement ps = connection.prepareStatement(GET_USER_ROLE_METHOD_QUERY);
            ps.setLong(1, user.getRole().ordinal() + 1);
            logger.debug("SQL query for getUserRole method: " + GET_USER_ROLE_METHOD_QUERY);
            ResultSet getRole = ps.executeQuery();
            if (getRole.next()) {
                role = getRole.getString(2);
            }
        } catch (SQLException e) {
            logger.error("Can`t connect to database", e);
        }
        return role;
    }

    public static List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection connection = DatabaseConnector.getConnection(DATABASE_NAME)) {
            PreparedStatement ps = connection.prepareStatement(GET_ALL_USERS_METHOD_QUERY);
            logger.debug("SQL query for getAllUsers method: " + GET_ALL_USERS_METHOD_QUERY);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User(
                        rs.getLong("id"),
                        rs.getString("login"),
                        rs.getString("password"),
                        rs.getString("email"),
                        Role.values()[rs.getInt("role_id") - 1]
                );
                users.add(user);
            }
        } catch (SQLException e) {
            logger.error("Can`t connect to database", e);
        }
        return users;
    }
}
