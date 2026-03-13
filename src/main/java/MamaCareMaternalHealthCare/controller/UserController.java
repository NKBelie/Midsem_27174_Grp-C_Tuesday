package MamaCareMaternalHealthCare.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import MamaCareMaternalHealthCare.model.EUserType;
import MamaCareMaternalHealthCare.model.User;
import MamaCareMaternalHealthCare.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Save User
    @PostMapping("/save")
    public ResponseEntity<?> saveUser(@RequestBody User user){
        User savedUser = userService.saveUser(user);
        return ResponseEntity.ok(savedUser);
    }

    // Get User by ID
    @GetMapping("/displayUserById/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id){
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    // Get All Users
    @GetMapping("/all")
    public ResponseEntity<?> getAllUsers(){
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // Get Users by Role
    @GetMapping("/role/{role}")
    public ResponseEntity<?> getUsersByRole(@PathVariable EUserType role){
        List<User> users = userService.userByRole(role);
        return ResponseEntity.ok(users);
    }

    // Find User by Email
    @GetMapping("/email/{email}")
    public ResponseEntity<?> findByEmail(@PathVariable String email){
        Optional<User> user = userService.findByEmail(email);
        return ResponseEntity.ok(user);
    }

    // Login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user){
        Optional<User> loggedUser = userService.authenticateUser(user.getEmail(), user.getPassword());

        if(loggedUser.isPresent()){
            return ResponseEntity.ok(loggedUser.get());
        }else{
            return ResponseEntity.status(401).body("Invalid email or password");
        }
    }

    // Update User
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable Long id){
        String message = userService.updateUser(user, id);
        return ResponseEntity.ok(message);
    }

    // Delete User
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        String message = userService.deleteUser(id);
        return ResponseEntity.ok(message);
    }
    @GetMapping("/pagination")
    public ResponseEntity<?> getUsers(Pageable pageable){
        return ResponseEntity.ok(
            userService.getUsersPagination(pageable));
    }

    @GetMapping("/sort/{field}")
    public ResponseEntity<?> sortUsers(@PathVariable String field){
        return ResponseEntity.ok(
            userService.getUsersSorted(field));
    }
}