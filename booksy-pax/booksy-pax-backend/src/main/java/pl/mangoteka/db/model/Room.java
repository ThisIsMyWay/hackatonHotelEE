package pl.mangoteka.db.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ROOM")
public class Room extends Model {

    @Column (name = "ROOM_NR")
    private Integer roomNr;

    @Column (name = "TYPE")
    private String type;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "room")
    private List<Booking> bookings;

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public Integer getRoomNr() {
        return roomNr;
    }

    public void setRoomNr(Integer roomNr) {
        this.roomNr = roomNr;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}