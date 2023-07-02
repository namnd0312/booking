package com.namnd.bookingbe.service.impl;

import com.namnd.bookingbe.Enum.ErrorCode;
import com.namnd.bookingbe.config.custom.RequestHandler;
import com.namnd.bookingbe.config.custom.RequestType;
import com.namnd.bookingbe.dto.JwtResponseDto;
import com.namnd.bookingbe.dto.RegisterDto;
import com.namnd.bookingbe.dto.RequestApi;
import com.namnd.bookingbe.dto.ResponseApi;
import com.namnd.bookingbe.dto.mapper.RegisterDtoMapper;
import com.namnd.bookingbe.model.Role;
import com.namnd.bookingbe.model.User;
import com.namnd.bookingbe.service.JwtService;
import com.namnd.bookingbe.service.RoleService;
import com.namnd.bookingbe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;
import java.util.Set;


@Service
public class AuthServiceImpl implements RequestHandler {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private RegisterDtoMapper registerDtoMapper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @RequestType(value = "login")
    public ResponseApi<JwtResponseDto> authenticateUser(User req) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        req.getUsername(),
                        req.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtService.generateTokenLogin(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User currentUser = userService.findByUserName(req.getUsername()).get();
        JwtResponseDto result = new JwtResponseDto(currentUser.getId(), jwt, userDetails.getUsername(), currentUser.getFullName(), userDetails.getAuthorities());
        return new ResponseApi().ok(result);
    }

    @RequestType(value = "register")
    public ResponseApi<String> registerUser(RegisterDto req) {
        if(userService.existsByUsername(req.getUsername())) {

            return  new ResponseApi().error(ErrorCode.AUTH_ERROR.getCode(), String.format(ErrorCode.AUTH_ERROR.getMessage(), "Fail -> Username is already taken!"));
        }

        Set<Role> roles = req.getRoles();

        for (Role role: roles) {
            if(roleService.findByName(role.getName()) == null){
                roleService.save(role);
                roleService.flush();
            }else {
                role.setId(roleService.findByName(role.getName()).getId());
            }
        }

        Optional<User> user = this.userService.findByUserName(req.getUsername());

        if(user.isPresent()){
            return  new ResponseApi().error(ErrorCode.AUTH_ERROR.getCode(), String.format(ErrorCode.AUTH_ERROR.getMessage(), "Fail -> Username is already taken!"));
        }

        User user1 = registerDtoMapper.toEntity(req);
        userService.save(user1);

        return new ResponseApi().ok("User registered successfully!");
    }

    @Override
    public String processRequest(RequestApi<?> request) {
        return "sfwef";
    }
}
