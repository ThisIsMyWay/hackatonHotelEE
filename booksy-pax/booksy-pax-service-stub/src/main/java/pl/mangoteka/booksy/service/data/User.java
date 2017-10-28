package pl.mangoteka.booksy.service.data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class User {
    int id;
    String name;
    String surname;
    String pesel;
}
