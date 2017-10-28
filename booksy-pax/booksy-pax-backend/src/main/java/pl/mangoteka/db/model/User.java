package pl.mangoteka.db.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Users")
public class User extends Model {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE_BOOKING")
    @SequenceGenerator(name = "SEQUENCE_BOOKING", sequenceName = "SEQUENCE_BOOKING", allocationSize = 1, initialValue = 1)
    private Integer id;

    @Column(name = "NAME")
    String name;

    @Column(name = "SURNAME")
    String surname;

    @Column(name = "PESEL")
    String pesel;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Booking> bookings;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }
}