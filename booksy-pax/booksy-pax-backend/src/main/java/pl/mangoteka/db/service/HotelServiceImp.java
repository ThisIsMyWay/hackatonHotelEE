package pl.mangoteka.db.service;

import org.jboss.logging.Logger;
import pl.mangoteka.db.DataBase;

import javax.ejb.Init;
import javax.ejb.Stateful;
import javax.inject.Inject;

@Stateful
public class HotelServiceImp implements HotelService {

    @Inject
    private Logger logger;

    @Inject
    private DataBase db;


    @Init
    public void init() {
        logger.debugv("HotelServiceImp INIT DB: {0}", db);
    }


}
