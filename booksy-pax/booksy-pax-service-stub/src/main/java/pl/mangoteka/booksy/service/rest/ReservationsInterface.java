package pl.mangoteka.booksy.service.rest;

import pl.mangoteka.booksy.service.data.Reservation;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.Serializable;
import java.util.List;


@Path("/reservations")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface ReservationsInterface extends Serializable {


    @Path("/")
    @GET
    List<Reservation> getReservations();
}
