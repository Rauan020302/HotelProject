package it.academy.hotel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SignUpModel {

    private String username;

    private String password;

    private String fullName;
}
