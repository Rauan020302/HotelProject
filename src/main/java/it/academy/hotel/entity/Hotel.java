package it.academy.hotel.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "hotel")
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Builder
public class Hotel extends BaseEntity{

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "contact")
    private Integer contact;

    @Column(name = "rating")
    private Integer rating;

    @OneToOne
    @JoinColumn(name="user_id")
    private User user;


}
