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
import pl.mangoteka.booksy.service.data.RoomType;
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
    private ListView<String> userList;
    @FXML
    private Label roomInfo;

    private ResteasyClient client;
    private UsersInterface usersInterface;
    private BookingsInterface bookingsInterface;
    private RoomsInterface roomsInterface;
    private List<User> users;
    private List<Room> rooms;
    private User user;
    private Room room;

    @FXML
    public void initialize() {

        createRestConn();

        createProxy();

        displayRoomList();

        displayUserList();
    }

    private void displayRoomList() {
        List<Room> rooms = roomsInterface.getRooms();
        ListProperty<String> roomListProperty = new SimpleListProperty<>();
        roomsList.itemsProperty().bind(roomListProperty);

//        List<Room> roomsMock = new ArrayList<>();
//        Room room = new Room(1, RoomType.SINGLE);
//        roomsMock.add(room);
//        room = new Room(2, RoomType.DOUBLE);
//        roomsMock.add(room);
//        room = new Room(3, RoomType.DOUBLE);
//        roomsMock.add(room);
//        rooms = roomsMock;
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
        List<User> users = usersInterface.getUsers();

        ListProperty<String> userListProperty = new SimpleListProperty<>();
        userList.itemsProperty().bind(userListProperty);

//        List<User> usersMock = new ArrayList<>();
//        User user = new User(1, "Janusz", "Warchoł", "123456789");
//        usersMock.add(user);
//        user = new User(2, "Grażyna", "Patriotyczna", "321654987");
//        usersMock.add(user);
//        user = new User(3, "Brajan", "Husarski", "456789123");
//        usersMock.add(user);
//        users = usersMock;
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

}
