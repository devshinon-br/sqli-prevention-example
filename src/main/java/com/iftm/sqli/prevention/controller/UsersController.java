package com.iftm.sqli.prevention.controller;

import com.iftm.sqli.prevention.model.Users;
import com.iftm.sqli.prevention.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    @Autowired
    private UsersRepository usersRepository;

    @GetMapping
    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    @GetMapping("/search")
    public List<Users> getUsersByUsernameAndComment(
        @RequestParam String username,
        @RequestParam String comment) {
        return usersRepository.findByUsernameAndComment(username, comment);
    }

    @PostMapping
    public Users createUser(@RequestBody Users user) {
        return usersRepository.save(user);
    }

    @GetMapping("/{id}")
    public Users getUserById(@PathVariable Long id) {
        return usersRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    @PutMapping("/{id}")
    public Users updateUser(@PathVariable Long id, @RequestBody Users updatedUser) {
        return usersRepository.findById(id)
            .map(user -> {
                user.setUsername(updatedUser.getUsername());
                user.setComment(updatedUser.getComment());
                return usersRepository.save(user);
            })
            .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        usersRepository.deleteById(id);
        return "User deleted successfully with id: " + id;
    }
}