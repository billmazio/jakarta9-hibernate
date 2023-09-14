package gr.aueb.cf.schoolapp.service;


import gr.aueb.cf.schoolapp.dao.exceptions.UserDAOException;
import gr.aueb.cf.schoolapp.dto.UserDTO;
import gr.aueb.cf.schoolapp.service.exceptions.UserNotFoundException;

public interface UserService {

    boolean registerUser(UserDTO userDTO) throws UserNotFoundException, UserDAOException;

    boolean isUserValid(String username, String password) throws UserNotFoundException;
}
