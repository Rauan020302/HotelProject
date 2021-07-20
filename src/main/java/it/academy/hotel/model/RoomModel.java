package it.academy.hotel.model;

import it.academy.hotel.entity.Hotel;
import it.academy.hotel.entity.User;
import it.academy.hotel.enums.RoomType;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Data
public class RoomModel {

    private RoomType roomType;

    private Integer number;

    private Long hotelId;

    private Long userId;
}
