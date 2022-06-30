package com.example.Argo.service;

import com.example.Argo.models.User;
import com.example.Argo.models.UserModel;
import com.example.Argo.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    public User saveUser(User user) { return userRepo.save(user); }

    public List<User> getAllUsers() { return userRepo.findAll(); }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> user = userRepo.findByUserName(userName);
        user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + userName));
        return user.map(UserModel::new).get();

    }
}
