package com.namnd.bookingbe.dto.mapper;


import com.namnd.bookingbe.dto.RegisterDto;
import com.namnd.bookingbe.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class RegisterDtoMapper {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User toEntity(RegisterDto dto) {
        if (dto == null) {
            return null;
        }

        User user = new User();

        BeanUtils.copyProperties(dto, user);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        return user;
    }
}
