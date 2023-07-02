package com.namnd.bookingbe.dto;

import com.namnd.bookingbe.model.Role;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
public class UserDTO {
    private String id;

    private String username;

    private String password;

    private String fullName;
}
