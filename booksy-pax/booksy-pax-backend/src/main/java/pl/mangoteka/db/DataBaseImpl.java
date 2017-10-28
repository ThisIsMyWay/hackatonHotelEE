package pl.mangoteka.db;


import org.jboss.logging.Logger;
import pl.mangoteka.db.model.Model;
import pl.mangoteka.db.qualifiers.OracleDb;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
@OracleDb
@SessionScoped
public class DataBaseImpl implements DataBase {

    @Inject
    Logger log;

    @PersistenceContext(unitName = "oracle")
    private EntityManager em;

    @Override
    public <T extends Model> List<T> getWholeList(Class<T> clazz) {
        return getWholeList(clazz, new QueryParameter[0]);
    }

    @Override
    public <T extends Model> List<T> getWholeList(Class<T> clazz, QueryParameter... filters) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> q = cb.createQuery(clazz);

        Root<T> model = q.from(clazz);
        q.select(model);

        for (QueryParameter paramToFiltr : filters){
            q.where(cb.equal(model.get(paramToFiltr.getName()), paramToFiltr.getValue()));
        }

        try {
            return em.createQuery(q).getResultList();
        } catch(NoResultException e) {
            log.warn("findByField: no result for " + clazz.getName(), e);

            return null;
        }
    }

    @Override
    public <T extends Model> T getItemById(Class<T> clazzToRetireve, Integer id) {
        return em.find(clazzToRetireve, id);
    }

    @Override
    public <T extends Model> T persistI(T entity) {
        entity = em.merge(entity);
        em.flush();
        log.info("Peresist " + entity.getClass().getName() + " ( id: " + entity.getId() + " )");
        return entity;    }

    @Override
    public <T extends Model> void remove(T entity) {
        em.remove(em.contains(entity) ? entity : em.merge(entity));
        log.info("Remove " + entity.getClass().getName() + " ( id: " + entity.getId() + " )");
    }
}
