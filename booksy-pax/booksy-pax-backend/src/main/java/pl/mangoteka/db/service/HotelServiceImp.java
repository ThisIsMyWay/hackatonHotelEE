package pl.mangoteka.db.service;

import org.jboss.logging.Logger;
import pl.mangoteka.db.DataBase;
import pl.mangoteka.db.model.Booking;
import pl.mangoteka.db.model.Room;
import pl.mangoteka.db.model.User;
import pl.mangoteka.db.qualifiers.MockDb;

import javax.ejb.Init;
import javax.ejb.Stateful;
import javax.inject.Inject;
import java.util.List;

@Stateful
public class HotelServiceImp implements HotelService {

    @Inject
    private Logger logger;

    @Inject
    @MockDb
    private DataBase db;


    @Init
    public void init() {
        logger.debugv("HotelServiceImp INIT DB: {0}", db);
    }


    @Override
    public List<Room> getRooms() {
        return null;
    }

    @Override
    public Room getRoom(int roomId) {
        return null;
    }

    @Override
    public int makeBooking(int roomNumber, long dateFrom, long dateTo, int clientId) {
        return 0;
    }

    @Override
    public boolean deleteBooking(int reservationId) {
        return false;
    }

    @Override
    public List<Booking> getBookings() {
        return null;
    }

    @Override
    public List<User> getUsers() {
        return null;
    }
}
