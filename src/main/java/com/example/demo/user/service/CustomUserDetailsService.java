package com.example.demo.user.service;

import com.example.demo.role.repository.RoleRepository;
import com.example.demo.role.service.RoleService;
import com.example.demo.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Spring Security用
 */

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByLoginId(username);
        if(user == null) {
            throw new UsernameNotFoundException("User not found with login id: " + username);
        }

        return new org.springframework.security.core.userdetails.User(user.loginId(), user.password(), getAuthorities(user.roleId()));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Integer roleId) {
        var role = roleService.findById(roleId);
        return List.of(new SimpleGrantedAuthority(role.name().securityRoleName()));
    }
}