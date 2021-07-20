package it.academy.hotel.service.impl;

import it.academy.hotel.entity.Hotel;
import it.academy.hotel.entity.User;
import it.academy.hotel.exception.ContactException;
import it.academy.hotel.exception.ObjectsNotFoundException;
import it.academy.hotel.model.HotelModel;
import it.academy.hotel.repository.HotelRepository;
import it.academy.hotel.service.HotelService;
import it.academy.hotel.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
public class HotelServiceImpl implements HotelService {
    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private UserService userService;

    @Override
    public List<Hotel> getAll() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel getHotelById(Long id) {
        return hotelRepository.findById(id).orElseThrow(() -> new ObjectsNotFoundException("not found hotel by id - " + id));
    }

    @Override
    public Hotel saveHotel(HotelModel hotelModel) {
        User user = userService.getUserById(hotelModel.getUserId());
        try {
            if (user == null) throw new ObjectsNotFoundException();
            if (hotelModel.getContact().toString().length() != 9) throw new ContactException();

            Hotel hotel = Hotel.builder()
                    .address(hotelModel.getAddress())
                    .contact(hotelModel.getContact())
                    .name(hotelModel.getName())
                    .rating(hotelModel.getRating())
                    .user(user).build();
            return hotelRepository.save(hotel);
        }catch (ObjectsNotFoundException e){
            throw new ObjectsNotFoundException("Hotel not found");
        }catch (ContactException e){
            throw new ContactException("bad number");
        }

    }

    @Override
    public Hotel deleteHotelById(Long id) {
        Hotel hotel = getHotelById(id);
        if (hotel != null){
            hotelRepository.delete(hotel);
            return hotel;
        }
        return null;
    }

    @Override
    public Hotel updateHotel(HotelModel hotelModel, Long id) {
        User user = userService.getUserById(hotelModel.getUserId());
        try {
            if (user == null) throw new ObjectsNotFoundException();
            if (hotelModel.getContact().toString().length() != 9) throw new ContactException();

            Hotel hotel = getHotelById(id);
            hotel.setAddress(hotelModel.getAddress());
            hotel.setContact(hotelModel.getContact());
            hotel.setName(hotelModel.getName());
            hotel.setRating(hotelModel.getRating());
            hotel.setUser(user);
            return hotelRepository.save(hotel);
        }catch (ObjectsNotFoundException e){
            throw new ObjectsNotFoundException("not found Hotel by id - " + id);
        }catch (ContactException e){
            throw new ContactException("bad number");
        }
    }
}
