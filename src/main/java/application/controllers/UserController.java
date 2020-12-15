package application.controllers;

import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import application.entities.User;
import application.repositories.UserRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return (List<User>) userRepository.findAll();
    }

    @PostMapping("/users")
    void addUser(@RequestBody User user) {
        userRepository.save(user);
    }
    
    
//    @GetMapping("/users/{id}")
//	public Mono<User> getUser(@PathVariable long id) {
//		return UserRepository.findUserById(id);
//	}
    
    @GetMapping("/users/{id}")
	public Optional<User> getUser(@PathVariable long id) { //Needed Optional<> - only a guess at what it does.
		return userRepository.findById(id);
	}
}
