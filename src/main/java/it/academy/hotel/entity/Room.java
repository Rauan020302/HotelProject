package it.academy.hotel.entity;

import it.academy.hotel.enums.RoomType;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "hotel_room")
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Builder
public class Room extends BaseEntity{
    @Column(name = "type")
    @Enumerated(EnumType.ORDINAL)
    private RoomType roomType;

    @Column(name = "number")
    private Integer number;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
