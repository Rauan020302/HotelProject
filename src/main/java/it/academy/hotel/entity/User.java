package it.academy.hotel.entity;

import it.academy.hotel.enums.Gender;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class User extends BaseEntity{

    @Column(name = "login")
    private String username;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "password")
    private String password;

    @Column(name = "status")
    private Long status = 1L;

    @Column(name = "gender")
    @Enumerated(EnumType.ORDINAL)
    private Gender gender;

    @Column(name = "contact")
    private Integer contact;

    @Column(name = "birth_day")
    private LocalDate birthDay;
}
