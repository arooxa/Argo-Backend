package com.example.Argo.service;

import com.example.Argo.models.Role;
import com.example.Argo.models.User;
//import com.example.Argo.models.UserModel;
import com.example.Argo.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    public UserService(UserRepo userRepository) {
        this.userRepo = userRepository;
    }

    public User saveUser(User user) { return userRepo.save(user); }

    public List<User> getAllUsers() { return userRepo.findAll(); }

    public Optional<User> findUser(String username) {
        return userRepo.findByUsername(username);
    }

    public boolean usernameExists(String username) {
        return userRepo.existsByUsername(username);
    }

//    @Override
//    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
//        Optional<User> user = userRepo.findByUsername(userName);
//        user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + userName));
//        return user.map(UserModel::new).get();
//    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username)
            .orElseThrow(() ->
                    new UsernameNotFoundException("User not found with username or email:" + username));
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
            user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection< ? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
