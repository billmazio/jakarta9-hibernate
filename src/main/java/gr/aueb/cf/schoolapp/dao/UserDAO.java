package gr.aueb.cf.schoolapp.dao;


import gr.aueb.cf.schoolapp.dao.exceptions.UserDAOException;
import gr.aueb.cf.schoolapp.model.User;

public interface UserDAO {
    boolean isUserValid(String username, String password) throws UserDAOException;
    boolean registerUser(User user) throws UserDAOException;

    boolean isUserExists(String username) throws UserDAOException;
}
