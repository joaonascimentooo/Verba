package com.api.verba.dto.auth;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class SignUpRequest {
    private String name;
    private String email;
    private String password;
}
