package pl.mangoteka.booksy.service.rest;

import pl.mangoteka.booksy.service.data.Booking;
import pl.mangoteka.db.service.HotelService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import java.util.ArrayList;
import java.util.List;

@SessionScoped
public class BookingsRestService implements BookingsInterface {
    @EJB
    private HotelService hotel;
    @Context
    private HttpServletRequest httpRequest;


    @PostConstruct
    private void init() {
        System.out.println("INIT REST SERVICE Bookings: " + httpRequest.getContextPath());
        System.out.println("SID: " + httpRequest.getSession().getId());
        System.out.println("HOTEL SERVICE: " + hotel);
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
}
