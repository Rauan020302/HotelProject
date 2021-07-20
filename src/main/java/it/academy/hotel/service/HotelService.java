package it.academy.hotel.service;

import it.academy.hotel.entity.Hotel;
import it.academy.hotel.model.HotelModel;

import java.util.List;

public interface HotelService {
    List<Hotel> getAll();
    Hotel getHotelById(Long id);
    Hotel saveHotel(HotelModel hotelModel);
    Hotel deleteHotelById(Long id);
    Hotel updateHotel(HotelModel hotelModel,Long id);
}
