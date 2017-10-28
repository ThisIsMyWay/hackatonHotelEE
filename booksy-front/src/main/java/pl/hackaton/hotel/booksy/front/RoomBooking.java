package pl.hackaton.hotel.booksy.front;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.engines.ApacheHttpClient4Engine;
import pl.mangoteka.booksy.service.data.Room;
import pl.mangoteka.booksy.service.data.User;
import pl.mangoteka.booksy.service.rest.BookingsInterface;
import pl.mangoteka.booksy.service.rest.RoomsInterface;
import pl.mangoteka.booksy.service.rest.UsersInterface;

import javax.ws.rs.core.UriBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RoomBooking {

    @FXML
    private ListView<String> roomsList;
    @FXML
    private ListView<String> clientList;
    @FXML
    private Label roomInfo;

    private ResteasyClient client;
    private UsersInterface usersInterface;
    private BookingsInterface bookingsInterface;
    private RoomsInterface roomsInterface;
    protected ListProperty<String> roomListProperty = new SimpleListProperty<>();
    protected ListProperty<String> userListProperty = new SimpleListProperty<>();

    @FXML
    public void initialize() {

//        createRestConn();
//
//        createProxy();

        displayRoomList();

        displayUserList();
    }

    private void displayRoomList() {
//        List<Room> rooms = roomsInterface.getRooms();

        roomsList.itemsProperty().bind(roomListProperty);

        List<String> roomsMock = new ArrayList<>();
        roomsMock.add("Awaria połączenia z serwisem! Przykładowa lista:");
        roomsMock.add("Nr: 1 Typ: jednoosobowy");
        roomsMock.add("Nr: 2 Typ: dwuosobowy");
        roomsMock.add("Nr: 3 Typ: trzyosobowy");
//        if (!rooms.isEmpty()) {
//            roomListProperty.set(FXCollections.observableArrayList(buildRoomsListToString(rooms)));
//        } else {
            roomListProperty.set(FXCollections.observableArrayList(roomsMock));
//        }
    }

    private void displayUserList() {
//        List<User> users = usersInterface.getUsers();

        clientList.itemsProperty().bind(userListProperty);

        List<String> usersMock = new ArrayList<>();
        usersMock.add("Awaria połączenia z serwisem! Przykładowa lista:");
        usersMock.add("Id:1 | Janusz Warchoł | 123456789");
        usersMock.add("Nr:2 | Grażyna Patriotyczna | 321654987");
        usersMock.add("Nr:3 | Brajan Husarski | 456789123");
//        if (!users.isEmpty()) {
//            userListProperty.set(FXCollections.observableArrayList(buildUsersListToString(users)));
//        } else {
            userListProperty.set(FXCollections.observableArrayList(usersMock));
//        }

    }

    private List<String> buildRoomsListToString(List<Room> rooms) {
        return rooms.stream()
                .map(room -> "Nr: " + room.getRoomNumber() + " Typ: " + room.getType())
                .collect(Collectors.toList());
    }

    private List<String> buildUsersListToString(List<User> users) {
        return users.stream()
                .map(user -> "Id:" + user.getId() + " | " + user.getName() + " " + user.getSurname() + " | " + user.getPesel())
                .collect(Collectors.toList());
    }

    private void createProxy() {
        usersInterface = client.target(UriBuilder.fromPath("http://localhost:8080/pax/rest")).proxy(UsersInterface.class);
        bookingsInterface = client.target(UriBuilder.fromPath("http://localhost:8080/pax/rest")).proxy(BookingsInterface.class);
        roomsInterface = client.target(UriBuilder.fromPath("http://localhost:8080/pax/rest")).proxy(RoomsInterface.class);
    }

    private void createRestConn() {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).build();
        cm.setMaxTotal(200);
        cm.setDefaultMaxPerRoute(20);
        ApacheHttpClient4Engine engine = new ApacheHttpClient4Engine(httpClient);
        client = new ResteasyClientBuilder().httpEngine(engine).build();
    }

}
