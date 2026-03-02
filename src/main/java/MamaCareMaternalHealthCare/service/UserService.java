package MamaCareMaternalHealthCare.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import MamaCareMaternalHealthCare.model.User;
import MamaCareMaternalHealthCare.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        if(userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException(" User with email " + user.getEmail() + " already exists.");
        }
        return userRepository.save(user);

    }
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User with ID " + userId + " not found."));
    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public String updateUser(User user, Long userId) {
        User existingUser = userRepository.findById(userId).orElse(null);
        if (existingUser != null) {
            existingUser.setFullName(user.getFullName());
            existingUser.setEmail(user.getEmail());
            existingUser.setPassword(user.getPassword());
            existingUser.setRole(user.getRole());
            existingUser.setPhoneNumber(user.getPhoneNumber());
            userRepository.save(existingUser);
            return "User updated successfully.";
        } else {
            return "User with ID " + userId + " not found.";
        }
    }
    public String deleteUser(Long userId) {
        User existingUser = userRepository.findById(userId).orElse(null);
        if (existingUser != null) {
            userRepository.delete(existingUser);
            return "User deleted successfully.";
        } else {
            return "User with ID " + userId + " not found.";
        }
    }
}
