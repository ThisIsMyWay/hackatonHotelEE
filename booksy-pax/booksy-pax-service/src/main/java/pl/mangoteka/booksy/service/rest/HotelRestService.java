package pl.mangoteka.booksy.service.rest;

import pl.mangoteka.booksy.service.data.RoomType;
import pl.mangoteka.booksy.service.data.User;
import pl.mangoteka.booksy.service.data.Booking;
import pl.mangoteka.booksy.service.data.Room;
import pl.mangoteka.db.service.HotelService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@SessionScoped
public class HotelRestService implements ClientsInterface, ReservationsInterface, RoomsInterface {

    @Context
    private HttpServletRequest httpRequest;

    @EJB
    private HotelService hotel;

    @PostConstruct
    private void init() {
        System.out.println("INIT REST SERVICE: " + httpRequest.getContextPath());
        System.out.println("SID: " + httpRequest.getSession().getId());
        System.out.println("HOTEL SERVICE: " + hotel);
    }

    @Override
    public List<Room> getRooms() {
        System.out.println("getRooms");
        List<Room> rooms = new ArrayList<>();
        List<pl.mangoteka.db.model.Room> entityRooms = hotel.getRooms();
        for (pl.mangoteka.db.model.Room room : entityRooms) {
            rooms.add(new Room(room.getRoomNr(), RoomType.valueOf(room.getType())));
        }
        return rooms;
    }

    @Override
    public Room getRoom(int roomNumber) {
        System.out.println("getRoom: " + roomNumber);
        pl.mangoteka.db.model.Room entity = hotel.getRoom(roomNumber);
        return new Room(entity.getRoomNr(), RoomType.valueOf(entity.getType()));
    }

    @Override
    public int makeBooking(int roomNumber, long dateFrom, long dateTo, int clientId) {
        System.out.println("makeBooking: " + roomNumber + " dateFrom: " + dateFrom +
                " dateTo: " + dateTo + " clientId: " + clientId);
        return hotel.makeBooking(roomNumber, dateFrom, dateTo, clientId);
    }

    @Override
    public Response deleteBooking(int bookingId) {
        System.out.println("deleteBooking " + bookingId);
        if (hotel.deleteBooking(bookingId)) {
            return Response.created(URI.create("/success")).status(Response.Status.OK).build();
        } else {
            return Response.created(URI.create("/failed")).status(Response.Status.BAD_REQUEST).header("error", "cannot delete booking").build();
        }
    }

    @Override
    public List<Booking> getBookings() {
        System.out.println("getBookings");
        List<pl.mangoteka.db.model.Booking> entities = hotel.getBookings();
        List<Booking> bookings = new ArrayList<>();
        for (pl.mangoteka.db.model.Booking entity : entities) {
            pl.mangoteka.db.model.User u = entity.getUser();
            bookings.add(new Booking(entity.getDateFrom().getTime(), entity.getDateTo().getTime(),
                    u.getName(), u.getSurname(),
                    u.getPesel(), entity.getRoom().getRoomNr()));
        }

        return bookings;
    }

    @Override
    public List<User> getClients() {
        System.out.println("getClients");
        List<pl.mangoteka.db.model.User> entities = hotel.getUsers();
        List<User> users = new ArrayList<>();
        for (pl.mangoteka.db.model.User entity : entities) {
            users.add(new User(entity.getId(), entity.getName(), entity.getSurname(), entity.getPesel()));
        }
        return users;
    }
}
