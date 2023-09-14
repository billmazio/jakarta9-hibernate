
package gr.aueb.cf.schoolapp.service;


import gr.aueb.cf.schoolapp.dao.UserDAO;
import gr.aueb.cf.schoolapp.dao.exceptions.UserDAOException;
import gr.aueb.cf.schoolapp.dto.UserDTO;
import gr.aueb.cf.schoolapp.model.User;
import gr.aueb.cf.schoolapp.service.exceptions.UserNotFoundException;

import gr.aueb.cf.schoolapp.security.SecUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class UserServiceImpl implements UserService {
    @Inject
    private UserDAO userDAO;

    @Override
    public boolean registerUser(UserDTO userDTO) throws UserNotFoundException, UserDAOException {
        // Validate userDTO data
        if (!isInputValid(userDTO)) {
            throw new UserNotFoundException("Invalid user input.");
        }

        // Check if username already exists
        if (userDAO.isUserExists(userDTO.getUsername())) {
            throw new UserNotFoundException("Username already exists. Please choose a different username.");
        }

        // Hash the user's password for secure storage
        String hashedPassword = SecUtil.hashPassword(userDTO.getPassword());
        userDTO.setPassword(hashedPassword);

        // Convert UserDTO to User entity if needed
        User userEntity = convertToUserEntity(userDTO);

        // Register the user using the DAO
        try {
            return userDAO.registerUser(userEntity);
        } catch (UserDAOException e) {
            throw new UserNotFoundException("Error while registering user.", e);
        }
    }


    private User convertToUserEntity(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword()); // Remember to hash the password before setting it
        // Set other properties if needed

        return user;
    }

    @Override
    public boolean isUserValid(String username, String password) throws UserNotFoundException {
        // Validate input
        if (username == null || username.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            throw new UserNotFoundException("Invalid username or password.");
        }

        // Hash the provided password with the same method used during registration
        // String hashedPassword = hashPassword(password);

        // Validate the user using the DAO
        try {
            return userDAO.isUserValid(username, password);
        } catch (UserDAOException e) {
            throw new UserNotFoundException("Error while validating user.", e);
        }
    }




    private boolean isInputValid(UserDTO userDTO) {
        // Add your validation logic here
        // This is a very basic example: check that the username and password are not null or empty
        return userDTO.getUsername() != null && !userDTO.getUsername().trim().isEmpty()
                && userDTO.getPassword() != null && !userDTO.getPassword().trim().isEmpty();
    }
}