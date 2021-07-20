package it.academy.hotel.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "user_role")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class UserRole extends BaseEntity{

    @Column(name = "role_name")
    private String roleName;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
