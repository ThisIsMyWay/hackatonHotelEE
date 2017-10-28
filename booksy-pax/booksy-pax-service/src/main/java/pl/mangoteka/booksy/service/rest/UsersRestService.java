package pl.mangoteka.booksy.service.rest;

import pl.mangoteka.booksy.service.data.User;
import pl.mangoteka.db.service.HotelService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import java.util.ArrayList;
import java.util.List;

@SessionScoped
public class UsersRestService implements UsersInterface {

    @EJB
    private HotelService hotel;
    @Context
    private HttpServletRequest httpRequest;


    @PostConstruct
    private void init() {
        System.out.println("INIT REST SERVICE Users: " + httpRequest.getContextPath());
        System.out.println("SID: " + httpRequest.getSession().getId());
        System.out.println("HOTEL SERVICE: " + hotel);
    }

    @Override
    public List<User> getUsers() {
        System.out.println("getUsers");
        List<pl.mangoteka.db.model.User> entities = hotel.getUsers();
        List<User> users = new ArrayList<>();
        for (pl.mangoteka.db.model.User entity : entities) {
            users.add(new User(entity.getId(), entity.getName(), entity.getSurname(), entity.getPesel()));
        }
        return users;
    }
}
