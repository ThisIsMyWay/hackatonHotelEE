package pl.mangoteka.booksy.service.data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;

@XmlAccessorType(XmlAccessType.FIELD)
public class Room implements Serializable {

    private int roomNumber;
    private RoomType type;

    public Room() {
    }

    public Room(int roomNumber, RoomType type) {
        this.roomNumber = roomNumber;
        this.type = type;
    }
}
