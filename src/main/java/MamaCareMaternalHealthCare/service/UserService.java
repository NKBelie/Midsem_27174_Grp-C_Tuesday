package MamaCareMaternalHealthCare.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.stereotype.Service;

import MamaCareMaternalHealthCare.model.User;
import MamaCareMaternalHealthCare.repository.UserRepository;

import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import MamaCareMaternalHealthCare.model.EUserType;

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
    public Page<User> getUsersPagination(Pageable pageable){
        return userRepository.findAll(pageable);
    }

    public List<User> getUsersSorted(String field){
        return userRepository.findAll(Sort.by(Sort.Direction.ASC, field));
    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public List<User> userByRole(EUserType role) {
        List<User> usersByRole = userRepository.findByRole(role);
        if(usersByRole != null && !usersByRole.isEmpty()){
            return usersByRole;
        }else{
            throw new RuntimeException("No users found with role: " + role);
        }
    }
    public Optional<User> findByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            return user;
        }else{
            throw new RuntimeException("User with email " + email + " not found.");
        }
    }
    public Optional<User> findByEmailAndPassword(String email, String password) {
        List<User> user = userRepository.findByEmailAndPassword(email, password);
        if (!user.isEmpty()) {
            return Optional.of(user.get(0));
        } else {
            return Optional.empty();
        }
    }
    public List<User> userByFullNameAndRole(String fullName, EUserType role) {
        List<User> usersByFullNameAndRole = userRepository.findByFullNameAndRole(fullName, role);
        if(usersByFullNameAndRole != null && !usersByFullNameAndRole.isEmpty()){
            return usersByFullNameAndRole;
            }else{
            throw new RuntimeException("No users found with full name: " + fullName + " and role: " + role);
            }
    }
    public Optional<User> authenticateUser(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        if (!user.isPresent() || !user.get().getPassword().equals(password)) {
            return Optional.empty();
        }else{
            return user;
        }
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
    public String login(User user){
        User existingUser = userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword()).get(0);
        if (existingUser != null) {
            return "Login successful for " + existingUser.getFullName();
        } else {
            return "Invalid credentials";
        }
}
}