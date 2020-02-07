package com.slava.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Name is required")
    private String name;
    @Size(min = 1, max = 10, message = "Surname should be from 1 to 10 symbol")
    private String surname;
    @NotBlank(message = "Email is required")
    @Email
    private String email;
    @Size(min = 7, message = "Minimum 7 symbols")
    private String password;
}
