package pl.mangoteka.booksy.service.rest;

import pl.mangoteka.booksy.service.data.Room;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.util.List;

@Path("/rooms")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface RoomsInterface extends Serializable {

    @Path("/")
    @GET
    List<Room> getRooms();

    @Path("/{room_id}")
    @GET
    Room getRoom(@PathParam("room_id") int roomId);

    @Path("/{room_id}")
    @POST
    int makeReservation(@PathParam("room_id") int roomId, @MatrixParam("from") long dateFrom,
                        @MatrixParam("to") long dateTo, @MatrixParam("client_id") int clientId);

    @Path("/{reservation_id}")
    @DELETE
    Response deleteReservation(@PathParam("reservation_id") int reservationId);

}
