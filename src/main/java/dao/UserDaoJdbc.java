package dao;

import db.DatabaseConnector;
import model.Role;
import model.User;
import org.apache.log4j.Logger;
import utils.HashUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJdbc implements UserDao {

    private static final String GET_USER_METHOD_QUERY = "SELECT * FROM internetshop.users WHERE login=?";
    private static final String ADD_METHOD_QUERY = "INSERT INTO internetshop.users (login, password, email, role_id, salt) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE_METHOD_QUERY = "UPDATE internetshop.users SET login=?,password=?,email=?,role_id=?,salt=? WHERE id=?";
    private static final String GET_USER_ROLE_METHOD_QUERY = "SELECT * FROM internetshop.roles WHERE id=?";
    private static final String DELETE_METHOD_QUERY = "DELETE FROM internetshop.users WHERE id=?";
    private static final String GET_ALL_USERS_METHOD_QUERY = "SELECT * FROM internetshop.users";
    private static final String DATABASE_NAME = "internetshop";
    private static final Logger LOGGER = Logger.getLogger(UserDaoJdbc.class);

    @Override
    public void add(User user) {
        try (Connection connection = DatabaseConnector.getConnection(DATABASE_NAME)) {
            PreparedStatement ps = connection.prepareStatement(ADD_METHOD_QUERY);
            ps.setString(1, user.getLogin());
            ps.setString(2, HashUtil.getSHA512SecurePassword(user.getPassword(), user.getSalt()));
            ps.setString(3, user.getEmail());
            ps.setLong(4, user.getRole().ordinal());
            ps.setString(5, user.getSalt())
            LOGGER.debug("SQL query for addUserToDatabase method: " + ADD_METHOD_QUERY);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Can`t connect to database", e);
        }
    }

    @Override
    public void delete(int id) {
        try (Connection connection = DatabaseConnector.getConnection(DATABASE_NAME)) {
            PreparedStatement ps = connection.prepareStatement(DELETE_METHOD_QUERY);
            ps.setInt(1, id);
            ps.executeUpdate();
            LOGGER.debug("SQL query for delete method: " + DELETE_METHOD_QUERY);
        } catch (SQLException e) {
            LOGGER.error("Can`t connect to database", e);
        }
    }

    @Override
    public void update(User user) {
        try (Connection connection = DatabaseConnector.getConnection(DATABASE_NAME)) {
            PreparedStatement ps = connection.prepareStatement(UPDATE_METHOD_QUERY);
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());
            ps.setInt(4, user.getRole().ordinal());
            ps.setString(5, user.getSalt());
            ps.setLong(6, user.getId());
            LOGGER.debug("SQL query for update method: " + UPDATE_METHOD_QUERY);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Can`t connect to database", e);
        }
    }

    @Override
    public User getUserByLogin(String login) {
        User user = new User();
        try (Connection connection = DatabaseConnector.getConnection(DATABASE_NAME)) {
            PreparedStatement ps = connection.prepareStatement(GET_USER_METHOD_QUERY);
            ps.setString(1, login);
            LOGGER.debug("SQL query for getUserFromDatabase method: " + GET_USER_METHOD_QUERY);
            ResultSet getUser = ps.executeQuery();
            if (getUser.next()) {
                user = new User(
                        Long.valueOf(getUser.getString("id")),
                        getUser.getString("login"),
                        getUser.getString("password"),
                        getUser.getString("email"),
                        Role.values()[(int) getUser.getLong("role_id")],
                        getUser.getString("salt"));
            }
        } catch (SQLException e) {
            LOGGER.error("Can`t connect to database", e);
        }
        return user;
    }

    @Override
    public User getUserById(long id) {
        return null;
    }

    @Override
    public boolean isUserExists(User user) {
        try (Connection connection = DatabaseConnector.getConnection(DATABASE_NAME)) {
            PreparedStatement ps = connection.prepareStatement(GET_USER_METHOD_QUERY);
            ps.setString(1, user.getLogin());
            LOGGER.debug("SQL query for isUserExistsInDatabase method: " + GET_USER_METHOD_QUERY);
            ResultSet getUser = ps.executeQuery();
            if (getUser.next()) {
                return true;
            }
        } catch (SQLException e) {
            LOGGER.error("Can`t connect to database", e);
        }
        return false;
    }

    @Override
    public String getUserRole(User user) {
        String role = "";
        try (Connection connection = DatabaseConnector.getConnection(DATABASE_NAME)) {
            PreparedStatement ps = connection.prepareStatement(GET_USER_ROLE_METHOD_QUERY);
            ps.setLong(1, user.getRole().ordinal());
            LOGGER.debug("SQL query for getUserRole method: " + GET_USER_ROLE_METHOD_QUERY);
            ResultSet getRole = ps.executeQuery();
            if (getRole.next()) {
                role = getRole.getString(2);
            }
        } catch (SQLException e) {
            LOGGER.error("Can`t connect to database", e);
        }
        return role;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection connection = DatabaseConnector.getConnection(DATABASE_NAME)) {
            PreparedStatement ps = connection.prepareStatement(GET_ALL_USERS_METHOD_QUERY);
            LOGGER.debug("SQL query for getAllUsers method: " + GET_ALL_USERS_METHOD_QUERY);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User(
                        rs.getLong("id"),
                        rs.getString("login"),
                        rs.getString("password"),
                        rs.getString("email"),
                        Role.values()[rs.getInt("role_id")],
                        rs.getString("salt")
                );
                users.add(user);
            }
        } catch (SQLException e) {
            LOGGER.error("Can`t connect to database", e);
        }
        return users;
    }
}
