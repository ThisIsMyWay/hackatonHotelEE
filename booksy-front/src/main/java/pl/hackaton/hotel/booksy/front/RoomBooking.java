package pl.hackaton.hotel.booksy.front;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.engines.ApacheHttpClient4Engine;
import pl.mangoteka.booksy.service.data.Room;
import pl.mangoteka.booksy.service.data.RoomType;
import pl.mangoteka.booksy.service.data.User;
import pl.mangoteka.booksy.service.rest.BookingsInterface;
import pl.mangoteka.booksy.service.rest.RoomsInterface;
import pl.mangoteka.booksy.service.rest.UsersInterface;

import javax.ws.rs.core.UriBuilder;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class RoomBooking {

    @FXML
    private ListView<String> roomsList;
    @FXML
    private ListView<String> userList;
    @FXML
    private Label roomInfo;
    @FXML
    private DatePicker dateFrom;
    @FXML
    private DatePicker dateTo;

    private ResteasyClient client;
    private UsersInterface usersInterface;
    private BookingsInterface bookingsInterface;
    private RoomsInterface roomsInterface;
    private List<User> users;
    private List<Room> rooms;
    private User user;
    private Room room;
    private boolean isAvailable;


    @FXML
    public void initialize() {

        createRestConn();

        createProxy();

        displayRoomList();

        displayUserList();
    }

    private void displayRoomList() {
        rooms = roomsInterface.getRooms();
        ListProperty<String> roomListProperty = new SimpleListProperty<>();
        roomsList.itemsProperty().bind(roomListProperty);

        if (!rooms.isEmpty()) {
            roomListProperty.set(FXCollections.observableArrayList(buildRoomsListToString(rooms)));
        } else {
            roomListProperty.set(FXCollections.observableArrayList(getErrorList()));
        }
    }

    private List<String> getErrorList() {
        List<String> errorList = new ArrayList<>();
        errorList.add("Wystąpił nieoczekiwany błąd");
        return errorList;
    }

    private void displayUserList() {
        users = usersInterface.getUsers();

        ListProperty<String> userListProperty = new SimpleListProperty<>();
        userList.itemsProperty().bind(userListProperty);

        if (!users.isEmpty()) {
            userListProperty.set(FXCollections.observableArrayList(buildUsersListToString(users)));
        } else {
            userListProperty.set(FXCollections.observableArrayList(getErrorList()));
        }
    }

    private List<String> buildRoomsListToString(List<Room> rooms) {
        return rooms.stream()
                .map(room -> "Nr:" + room.getRoomNumber() + " Typ: " + room.getType())
                .collect(Collectors.toList());
    }

    private List<String> buildUsersListToString(List<User> users) {
        return users.stream()
                .map(user -> "Id:" + user.getId() + " | " + user.getName() + " " + user.getSurname() + " | " + user.getPesel())
                .collect(Collectors.toList());
    }

    private void createProxy() {
        usersInterface = client.target(UriBuilder.fromPath("http://localhost:8181/pax/rest")).proxy(UsersInterface.class);
        bookingsInterface = client.target(UriBuilder.fromPath("http://localhost:8181/pax/rest")).proxy(BookingsInterface.class);
        roomsInterface = client.target(UriBuilder.fromPath("http://localhost:8181/pax/rest")).proxy(RoomsInterface.class);
    }

    private void createRestConn() {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).build();
        cm.setMaxTotal(200);
        cm.setDefaultMaxPerRoute(20);
        ApacheHttpClient4Engine engine = new ApacheHttpClient4Engine(httpClient);
        client = new ResteasyClientBuilder().httpEngine(engine).build();
    }

    @FXML
    private void showRoomInfo() {
        String tmp = roomsList.getSelectionModel().getSelectedItem();
        roomInfo.setText(tmp);
        String roomId = tmp.substring(3, tmp.indexOf(" "));
        room = setRoomById(Integer.valueOf(roomId));
    }

    private Room setRoomById(int roomId) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomId) {
                return room;
            }
        }
        return null;
    }

    @FXML
    private void selectUser() {
        String tmp = userList.getSelectionModel().getSelectedItem();
        String userId = tmp.substring(3, tmp.indexOf(" "));
        user = setUserById(Integer.valueOf(userId));
    }

    private User setUserById(int userId) {
        for (User user : users) {
            if (user.getId() == userId) {
                return user;
            }
        }
        return null;
    }

    @FXML
    private void checkBooking() {
        LocalDate localDate = dateFrom.getValue();
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        Date date = Date.from(instant);
        long dateFromAsLong = date.getTime();

        localDate = dateTo.getValue();
        instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        date = Date.from(instant);
        long dateToAsLong = date.getTime();

        isAvailable = roomsInterface.;


    }

}
