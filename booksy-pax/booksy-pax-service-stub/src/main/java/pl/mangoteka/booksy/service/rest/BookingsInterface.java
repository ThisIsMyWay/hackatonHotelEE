package pl.mangoteka.booksy.service.rest;

import pl.mangoteka.booksy.service.data.Booking;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.Serializable;
import java.util.List;


@Path("/bookings")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface BookingsInterface extends Serializable {


    @Path("/")
    @GET
    List<Booking> getBookings();
}
