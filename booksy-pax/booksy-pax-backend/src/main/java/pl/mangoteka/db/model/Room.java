package pl.mangoteka.db.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ROOMS")
public class Room extends Model {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE_ROOM")
    @SequenceGenerator(name = "SEQUENCE_ROOM", sequenceName = "SEQUENCE_ROOM", allocationSize = 1, initialValue = 1)
    private Integer id;

    @Column(name = "ROOM_NR")
    private Integer roomNr;

    @Column(name = "TYPE")
    private String type;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "room", fetch = FetchType.EAGER)
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}