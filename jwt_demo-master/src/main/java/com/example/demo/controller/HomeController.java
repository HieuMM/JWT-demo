package com.example.demo.controller;

import com.example.demo.entiy.Role;
import com.example.demo.entiy.User;
import com.example.demo.jwt.JwtTokenProvider;
import com.example.demo.payload.LoginRequest;
import com.example.demo.repository.CustormDetailService;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.Set;



@RestController
@RequestMapping("/authen")
public class HomeController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserService userService;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtTokenProvider jwtTokenProvider;
    @Autowired
    UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()));
        CustormDetailService userDetails = (CustormDetailService) authentication.getPrincipal();

        String jwt =jwtTokenProvider.generateToken(userDetails);
       // return ResponseEntity.ok(jwt);
        return ResponseEntity.ok().body("name :"+userDetails.getUser().getUsername()+"\n"
                +"Password: "+userDetails.getUser().getPassword()+"\n"  +"Token:"+jwt
                +"\n"  +"Role:"+userDetails.getAuthorities());
    }
    @GetMapping("/adduser")
    public String addNewAccount(){
        Role role=roleRepository.getById(1L);
        User user = new User(null,"hieumm",passwordEncoder.encode("hieumm"), Set.of(role));
        userRepository.save(user);
        return null;
    }
    @GetMapping("/addrole")
    public String addNewRole(){
        Role role=new Role(null,"user");
       roleRepository.save(role);
        return null;
    }

}
