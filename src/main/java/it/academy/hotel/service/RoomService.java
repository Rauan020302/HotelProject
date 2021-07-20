package it.academy.hotel.service;

import it.academy.hotel.entity.Room;
import it.academy.hotel.model.RoomModel;

import java.util.List;

public interface RoomService {
    List<Room> getAll();
    Room getRoomById(Long id);
    Room saveRoom(RoomModel roomModel);
    Room deleteRoomById(Long id);
    Room updateRoomById(Long id,RoomModel roomModel);
}
