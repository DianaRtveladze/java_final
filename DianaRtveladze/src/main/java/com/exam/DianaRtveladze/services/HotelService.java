package com.exam.DianaRtveladze.services;

import com.exam.DianaRtveladze.entities.Hotel;
import com.exam.DianaRtveladze.entities.Reservation;
import com.exam.DianaRtveladze.entities.Room;
import com.exam.DianaRtveladze.repositories.HotelRepository;
import com.exam.DianaRtveladze.repositories.ReservationRepository;
import com.exam.DianaRtveladze.repositories.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class HotelService {
    private final HotelRepository hotelRepository;
    private final ReservationRepository reservationRepository;
    private final RoomRepository roomRepository;

    public HotelService(HotelRepository hotelRepository, ReservationRepository reservationRepository, RoomRepository roomRepository) {
        this.hotelRepository = hotelRepository;
        this.reservationRepository = reservationRepository;
        this.roomRepository = roomRepository;
    }

    public Hotel addHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    public Room addHotelRoom(Long hotelId, Room room) {
        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow();
        room.setHotel(hotel);
        return hotelRepository.save(hotel).getRooms().stream()
                .filter(r -> r.getName().equals(room.getName()))
                .findFirst()
                .orElseThrow();
    }

    public Reservation addReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public List<Reservation> getReservations() {
        return reservationRepository.findAll();
    }

    public List<Hotel> getHotels() {
        return hotelRepository.findAll();
    }

    public List<Room> getFreeRooms() {
        return roomRepository.findAll().stream()
                .filter(r -> r.getReservations().isEmpty())
                .collect(Collectors.toList());
    }

    public List<Room> getBusyRooms(List<Long> hotelIds) {
        return roomRepository.findAll().stream()
                .filter(r -> hotelIds.contains(r.getHotel().getId()))
                .filter(r -> !r.getReservations().isEmpty())
                .collect(Collectors.toList());
    }

    public Map<String, Double> getSalesByHotel() {
        return reservationRepository.findAll().stream()
                .collect(Collectors.groupingBy(
                        r -> r.getRoom().getHotel().getName(),
                        Collectors.summingDouble(r -> r.getRoom().getPrice() * r.getRoom().getDuration())
                ));
    }
}

