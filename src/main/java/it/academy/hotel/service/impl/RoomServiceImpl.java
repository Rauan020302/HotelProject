package it.academy.hotel.service.impl;

import it.academy.hotel.entity.Hotel;
import it.academy.hotel.entity.Room;
import it.academy.hotel.entity.User;
import it.academy.hotel.exception.ObjectsNotFoundException;
import it.academy.hotel.model.RoomModel;
import it.academy.hotel.repository.RoomRepository;
import it.academy.hotel.service.HotelService;
import it.academy.hotel.service.RoomService;
import it.academy.hotel.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private HotelService hotelService;
    @Autowired
    private UserService userService;



    @Override
    public List<Room> getAll() {
        return roomRepository.findAll();
    }

    @Override
    public Room getRoomById(Long id) {
        return roomRepository.findById(id).orElseThrow(() -> new ObjectsNotFoundException("not found Room by id - " + id));
    }

    @Override
    public Room saveRoom(RoomModel roomModel) {
        Hotel hotel = hotelService.getHotelById(roomModel.getHotelId());
        User user = userService.getUserById(roomModel.getUserId());
        try {
            if (user == null || hotel == null) throw new ObjectsNotFoundException();


            Room room = Room.builder()
                    .roomType(roomModel.getRoomType())
                    .hotel(hotel)
                    .number(roomModel.getNumber())
                    .user(user).build();
            return roomRepository.save(room);
        }catch (ObjectsNotFoundException e){
            throw new ObjectsNotFoundException("not found Room ");
        }
    }

    @Override
    public Room deleteRoomById(Long id) {
        Room room = getRoomById(id);
        if (room != null){
            roomRepository.delete(room);
            return room;
        }
        return null;
    }

    @Override
    public Room updateRoomById(Long id, RoomModel roomModel) {
        User user = userService.getUserById(roomModel.getUserId());
        Hotel hotel = hotelService.getHotelById(roomModel.getHotelId());
        try {
            if (user == null || hotel == null) throw new ObjectsNotFoundException();

            Room room = getRoomById(id);
            room.setRoomType(roomModel.getRoomType());
            room.setNumber(roomModel.getNumber());
            room.setUser(user);
            room.setHotel(hotel);

            return roomRepository.save(room);

        }catch (ObjectsNotFoundException e){
            throw new ObjectsNotFoundException("not found Room by id - " + id);
        }
    }
}
