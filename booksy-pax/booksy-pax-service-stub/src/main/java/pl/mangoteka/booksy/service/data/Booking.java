package pl.mangoteka.booksy.service.data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class Booking {
    long from;
    long to;
    String clientName;
    String clientSurname;
    String pesel;
    int roomNumber;

    public Booking() {
    }

    public Booking(long from, long to, String clientName, String clientSurname, String pesel, int roomNumber) {
        this.from = from;
        this.to = to;
        this.clientName = clientName;
        this.clientSurname = clientSurname;
        this.pesel = pesel;
        this.roomNumber = roomNumber;
    }
}
