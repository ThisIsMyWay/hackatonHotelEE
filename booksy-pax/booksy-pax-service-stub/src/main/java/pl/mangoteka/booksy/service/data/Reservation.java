package pl.mangoteka.booksy.service.data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class Reservation {
    long from;
    long to;
    String clientName;
    String clientSurname;
    long pesel;
    int roomNumber;
}
