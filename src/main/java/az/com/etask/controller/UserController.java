package az.com.etask.controller;

import az.com.etask.model.Company;
import az.com.etask.model.User;
import az.com.etask.security.CustomUserDetailsService;
import az.com.etask.service.inter.UserService;
import az.com.etask.security.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    private UserService userService;

    private JWTUtil jwtUtil;

    private AuthenticationManager authenticationManager;

    private CustomUserDetailsService userDetailsService;

    @Autowired
    public UserController(UserService userService,
                          JWTUtil jwtUtil,
                          AuthenticationManager authenticationManager,
                          CustomUserDetailsService userDetailsService) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/signIn")
    public String creteToken(@RequestParam("username") String username,
                             @RequestParam("password") String password) throws Exception {
        try {
            System.out.println("username : "+username+" password : "+password);
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (BadCredentialsException ex) {
            log.error("Incorrect username or password : "+ex);
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        System.out.println("userDetails(cont) : "+userDetails);
        final String jwt = jwtUtil.generateToken(userDetails);
        System.out.println("jwt : "+jwt);
        return jwt;
    }

    @PostMapping("/signUp")
    public ResponseEntity<?> signUp(@RequestParam("username") String username,
                                    @RequestParam("password") String password,
                                    @RequestParam("fullName") String fullName,
                                    @RequestParam("email") String email,
                                    @RequestParam("companyId") Long companyId) {
        Company company = new Company(companyId, null, null, null, null);
        User user = new User(null, username, password, email, fullName, null, company);
        boolean result = userService.signUp(user);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/addUser")
    public ResponseEntity<?> addUser(@RequestParam("username") String username,
                                    @RequestParam("password") String password,
                                    @RequestParam("fullName") String fullName,
                                    @RequestParam("email") String email,
                                    @RequestParam("companyId") Long companyId) {


        Company company = new Company(companyId, null, null, null, null);
        User user = new User(null, username, password, email, fullName, null, company);
        boolean result = userService.addUser(user);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
