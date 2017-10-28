package pl.mangoteka.db;

import pl.mangoteka.db.model.Booking;
import pl.mangoteka.db.model.Model;
import pl.mangoteka.db.model.Room;
import pl.mangoteka.db.model.User;
import pl.mangoteka.db.qualifiers.MockDb;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@SessionScoped
public class MockDataBase implements DataBase {

    private List<User> users;
    private List<Room> rooms;
    private List<Booking> bookings;

    @PostConstruct
    public void init() {
        System.out.println("MockDataBase init");
        users = new ArrayList<>();
        rooms = new ArrayList<>();
        bookings = new ArrayList<>();
        User u1 = new User();
        u1.setName("Ala");
        u1.setSurname("Kot");
        u1.setPesel("12335434");
        u1.setId(1);
        users.add(u1);
        User u2 = new User();
        u2.setName("Basia");
        u2.setSurname("Nowak");
        u2.setPesel("45454646545");
        u2.setId(2);
        users.add(u2);

        Room r1 = new Room();
        r1.setType("SINGLE");
        r1.setId(1);
        r1.setRoomNr(101);
        rooms.add(r1);

        Room r2 = new Room();
        r2.setType("DOUBLE");
        r2.setId(2);
        r2.setRoomNr(201);
        rooms.add(r2);

        Booking b1 = new Booking();
        b1.setUser(u1);
        b1.setRoom(r1);
        b1.setDateTo(new Date(System.currentTimeMillis()));
        b1.setDateFrom(new Date(System.currentTimeMillis() - 100000));
        bookings.add(b1);

        Booking b2 = new Booking();
        b2.setUser(u1);
        b2.setRoom(r1);
        b2.setDateTo(new Date(System.currentTimeMillis() - 100000));
        b2.setDateFrom(new Date(System.currentTimeMillis() - 300000));
        bookings.add(b2);

        u1.setBookings(bookings);
    }

    @Override
    public <T extends Model> List<T> getWholeList(Class<T> clazz) {
        String name = clazz.getCanonicalName();
        if (name.equals(User.class.getCanonicalName())) {
            return (List<T>) users;
        } else if (name.equals(Room.class.getCanonicalName())) {
            return (List<T>) rooms;
        } else if (name.equals(Booking.class.getCanonicalName())) {
            return (List<T>) bookings;
        }
        return null;
    }

    @Override
    public <T extends Model> List<T> getWholeList(Class<T> clazz, QueryParameter... filters) {
        String name = clazz.getCanonicalName();
        if (name.equals(User.class.getCanonicalName())) {
            return (List<T>) users.get(0);
        } else if (name.equals(Room.class.getCanonicalName())) {
            return (List<T>) rooms.get(0);
        } else if (name.equals(Booking.class.getCanonicalName())) {
            return (List<T>) bookings.get(0);
        }
        return null;
    }

    @Override
    public <T extends Model> T getItemById(Class<T> clazzToRetireve, Integer id) {
        String name = clazzToRetireve.getCanonicalName();
        if (name.equals(User.class.getCanonicalName())) {
            return (T) users.stream().filter(user -> user.getId().equals(id)).findFirst().get();
        } else if (name.equals(Room.class.getCanonicalName())) {
            return (T) rooms.stream().filter(room -> room.getId().equals(id)).findFirst().get();
        } else if (name.equals(Booking.class.getCanonicalName())) {
            return (T) bookings.stream().filter(booking -> booking.getId().equals(id)).findFirst().get();
        }
        return null;

    }

    @Override
    public <T extends Model> T persistI(T entity) {
        return entity;
    }

    @Override
    public <T extends Model> void remove(T entity) {
        // nothing :P
    }
}
