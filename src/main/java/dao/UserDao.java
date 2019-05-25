package dao;

import model.User;

public interface UserDao extends GenericDao<User> {

    User getUserByLogin(String login);

    boolean isUserExists(User user);

    String getUserRole(User user);

}
