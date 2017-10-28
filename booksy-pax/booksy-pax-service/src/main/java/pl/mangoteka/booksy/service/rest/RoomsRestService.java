package pl.mangoteka.booksy.service.rest;

import pl.mangoteka.booksy.service.data.Room;
import pl.mangoteka.booksy.service.data.RoomType;
import pl.mangoteka.db.model.Booking;
import pl.mangoteka.db.service.HotelService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@SessionScoped
public class RoomsRestService implements RoomsInterface {

    @Context
    private HttpServletRequest httpRequest;

    @EJB
    private HotelService hotel;

    @PostConstruct
    private void init() {
        System.out.println("INIT REST SERVICE Rooms: " + httpRequest.getContextPath());
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
    public int makeBooking(int roomNumber, long dateFrom, long dateTo, int userId) {
        System.out.println("makeBooking: " + roomNumber + " dateFrom: " + dateFrom +
                " dateTo: " + dateTo + " clientId: " + userId);
        return hotel.makeBooking(roomNumber, dateFrom, dateTo, userId);
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
}
