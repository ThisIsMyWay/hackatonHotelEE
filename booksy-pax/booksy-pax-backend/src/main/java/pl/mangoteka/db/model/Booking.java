package pl.mangoteka.db.model;

import javax.persistence.*;
import java.sql.Date;

@Table(name = "BOOKINGS")
@Entity
public class Booking extends Model {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE_BOOKING")
    @SequenceGenerator(name = "SEQUENCE_BOOKING", sequenceName = "SEQUENCE_BOOKING", allocationSize = 1, initialValue = 1)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ROOM_ID", referencedColumnName = "ID")
    private Room room;

    @ManyToOne(optional = false)
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    private User user;

    @Column(name = "DATE_FROM")
    private Date dateFrom;

    @Column(name = "DATE_TO")
    private Date dateTo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }
}