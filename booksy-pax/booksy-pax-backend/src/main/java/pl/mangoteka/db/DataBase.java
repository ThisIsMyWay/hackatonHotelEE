package pl.mangoteka.db;

import pl.mangoteka.db.model.Model;

import java.io.Serializable;
import java.util.List;

public interface DataBase extends Serializable{

    public <T extends Model> List<T> getWholeList(Class<T> clazz);

    public <T extends Model> List<T> getWholeList(Class<T> clazz, QueryParameter...filters);

    public <T extends Model> T getItemById(Class<T> clazzToRetireve, Integer id);

    public <T extends  Model> T persistI(T entity);

    public <T extends  Model> void remove(T entity);

}
