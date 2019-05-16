package dao;

import model.User;

import java.util.List;

public interface UserDao {

    void add(User user);

    void delete(int id);

    void update(User user);

    User getUserByLogin(String login);

    User getUserById(long id);

    boolean isUserExists(User user);

    String getUserRole(User user);

    List<User> getAllUsers();

}
