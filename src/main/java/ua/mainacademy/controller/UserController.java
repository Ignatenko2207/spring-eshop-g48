package ua.mainacademy.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.mainacademy.model.User;
import ua.mainacademy.service.UserService;

import java.util.List;

@Slf4j
@Profile("prod")
@RestController
@RequestMapping("user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PutMapping
    public ResponseEntity<User> create(@RequestBody User user) {
        try {
            return new ResponseEntity<>(userService.create(user), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<User> update(@RequestBody User user) {
        try {
            return new ResponseEntity<>(userService.update(user), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findOneById(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(userService.findOneById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("find-by-filters")
    public ResponseEntity<List<User>> findAllByFilter(
            @RequestParam(required = false) String login,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String phone
    ) {
        return new ResponseEntity<>(
                userService.findAllByFilter(
                        login,
                        email,
                        firstName,
                        lastName,
                        phone
                ), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @DeleteMapping("/id")
    public ResponseEntity<Void> deleteById(Integer id) {
        try {
            userService.deleteById(id);
        } catch (Exception e) {
            log.warn("Delete method was processed with exception for user with id {}", id);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
