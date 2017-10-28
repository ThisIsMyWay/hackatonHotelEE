package pl.mangoteka.db;

import pl.mangoteka.db.model.Model;
import pl.mangoteka.db.qualifiers.MockDb;

import javax.enterprise.context.SessionScoped;
import java.util.List;

@SessionScoped
@MockDb
public class MockDataBase implements DataBase{
    @Override
    public <T extends Model> List<T> getWholeList(Class<T> clazz) {
        return null;
    }

    @Override
    public <T extends Model> T getItemById(Class<T> clazzToRetireve, Integer id) {
        return null;
    }

    @Override
    public <T extends Model> T persistI(T entity) {
        return null;
    }

    @Override
    public <T extends Model> void remove(T entity) {

    }
}
