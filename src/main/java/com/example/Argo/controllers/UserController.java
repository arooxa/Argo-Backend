package com.example.Argo.controllers;

import com.example.Argo.models.User;
//import com.example.Argo.models.UserModel;
import com.example.Argo.payload.LoginDto;
import com.example.Argo.payload.SignUpDto;
import com.example.Argo.repos.RoleRepo;
import com.example.Argo.repos.UserRepo;
import com.example.Argo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping("/user/getAll")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

//    @PostMapping("/auth/register")
//    public User registerUser(@RequestBody User user) {
//        return userService.saveUser(user);
//    }
//
//    @PostMapping("/auth/login")
//    public ResponseEntity<HttpStatus> loginUser(@RequestBody UserModel userModel) throws Exception {
//        Authentication authObject;
//        try {
//            authObject = authentication.authenticate(new UsernamePasswordAuthenticationToken(userModel.getUsername(), userModel.getPassword()));
//            SecurityContextHolder.getContext().setAuthentication(authObject);
//        } catch (BadCredentialsException e) {
//            throw new Exception("Credentials Invalid");
//        }
//        return new ResponseEntity<HttpStatus>(HttpStatus.OK);
//    }

    @PostMapping("/auth/login")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsername(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>("User signed-in successfully!.", HttpStatus.OK);
    }

    @PostMapping("/auth/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto){

        // add check for username exists in a DB
        if(userService.usernameExists(signUpDto.getUsername())){
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
        }

        // create user object
        User user = new User();
        user.setFirst_name(signUpDto.getFirst_name());
        user.setLast_name(signUpDto.getLast_name());
        user.setUsername(signUpDto.getUsername());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

        //Role roles = roleRepo.findByName("ROLE_ADMIN").get();
        //user.setRoles(Collections.singleton(roles));

        userService.saveUser(user);

        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);

    }

        @GetMapping("/auth/user")
        @ResponseBody
        public User currentUserName() throws UsernameNotFoundException {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (!(authentication instanceof AnonymousAuthenticationToken)) {
                String currentUserName = authentication.getName();
                User currentUser = userService.findUser(currentUserName)
                        .orElseThrow(() ->
                                new UsernameNotFoundException("User not found with username or email:" + currentUserName));
                currentUser.setPassword("");
                return currentUser;
            }
            return null;
        }
}
