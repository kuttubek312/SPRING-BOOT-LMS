package com.peaksoft.springbootlms.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
public class RegisterRequest {

    private String email;

    private String password;

    private String firstName;

}
