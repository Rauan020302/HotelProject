package it.academy.hotel.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "food")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Food extends BaseEntity{

    @Column(name = "name",nullable = false,unique = true)
    private String name;

    @Column(name = "price",nullable = false)
    private BigDecimal price;

    @Column(name = "description",length = 500,nullable = false)
    private String description;

    @OneToOne
    @JoinColumn(name = "image")
    private Image image;
}
