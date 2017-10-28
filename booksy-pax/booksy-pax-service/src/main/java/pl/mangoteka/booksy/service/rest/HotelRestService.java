package pl.mangoteka.booksy.service.rest;

import pl.mangoteka.booksy.service.data.Client;
import pl.mangoteka.booksy.service.data.Reservation;
import pl.mangoteka.booksy.service.data.Room;

import javax.ws.rs.core.Response;
import java.util.List;

public class HotelRestService implements ClientsInterface, ReservationsInterface, RoomsInterface {
    @Override
    public List<Room> getRooms() {
        return null;
    }

    @Override
    public Room getRoom(int roomId) {
        return null;
    }

    @Override
    public int makeReservation(int roomId, long dateFrom, long dateTo, int clientId) {
        return 0;
    }

    @Override
    public Response deleteReservation(int reservationId) {
        return null;
    }

    @Override
    public List<Reservation> getReservations() {
        return null;
    }

    @Override
    public List<Client> getClients() {
        return null;
    }
}
