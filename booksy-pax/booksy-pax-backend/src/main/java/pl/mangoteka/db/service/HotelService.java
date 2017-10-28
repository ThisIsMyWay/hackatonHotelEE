package pl.mangoteka.db.service;

import pl.mangoteka.db.model.Booking;
import pl.mangoteka.db.model.Room;
import pl.mangoteka.db.model.User;

import javax.ejb.Local;
import java.util.List;

@Local
public interface HotelService {

    List<Room> getRooms();

    Room getRoom(int roomId);

    int makeBooking(int roomNumber, long dateFrom, long dateTo, int clientId);

    boolean deleteBooking(int reservationId);

    List<Booking> getBookings();

    List<User> getUsers();
}
