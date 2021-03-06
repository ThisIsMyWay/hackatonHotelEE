package pl.mangoteka.db.service;

import org.jboss.logging.Logger;
import pl.mangoteka.db.DataBase;
import pl.mangoteka.db.QueryParameter;
import pl.mangoteka.db.model.Booking;
import pl.mangoteka.db.model.Room;
import pl.mangoteka.db.model.User;
import pl.mangoteka.db.qualifiers.OracleDb;

import javax.ejb.Init;
import javax.ejb.Stateful;
import javax.inject.Inject;
import java.sql.Date;
import java.util.List;

@Stateful
public class HotelServiceImp implements HotelService {

    @Inject
    private Logger logger;

    @Inject
    @OracleDb
    private DataBase db;


    @Init
    public void init() {
        logger.debugv("HotelServiceImp INIT DB: {0}", db);
    }


    @Override
    public List<Room> getRooms() {
        return db.getWholeList(Room.class);
    }

    @Override
    public Room getRoom(int roomId) {
        return db.getWholeList(Room.class, new QueryParameter("roomNr", roomId)).get(0);
    }

    @Override
    public int makeBooking(int roomNumber, long dateFrom, long dateTo, int userId) {
        Booking booking = new Booking();
        booking.setDateFrom(new Date(dateFrom));
        booking.setDateTo(new Date(dateTo));
        booking.setRoom(db.getWholeList(Room.class, new QueryParameter("roomNr", roomNumber)).get(0));
        booking.setUser(db.getItemById(User.class, userId));
        Booking newBooking = db.persistI(booking);
        return newBooking.getId();
    }

    @Override
    public boolean deleteBooking(int bookingId) {
        Booking booking = db.getItemById(Booking.class, bookingId);
        if (booking == null) return false;
        db.remove(booking);
        return true;
    }

    @Override
    public List<Booking> getBookings() {
        return db.getWholeList(Booking.class);
    }

    @Override
    public List<User> getUsers() {
        return db.getWholeList(User.class);
    }
}
