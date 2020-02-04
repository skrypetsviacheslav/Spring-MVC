package com.slava.model;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class User {
    @NotBlank(message = "Name is required")
    private String name;
    @Size(min = 1, max = 10, message = "Surname should be from 1 to 10 symbol")
    private String surname;
    @Email
    private String email;
}
