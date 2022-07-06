package com.example.Argo.payload;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class SignUpDto {
    private String first_name;
    private String last_name;
    private String username;
    private String password;
}
