package it.academy.hotel.model;

import lombok.Data;

@Data
public class HotelModel {

    private String name;

    private String address;

    private Integer rating;

    private Integer contact;

    private Long userId;
}
