package danielh1307.jee.example.util.access;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import danielh1307.jee.example.enums.LifeCycleType;
import danielh1307.jee.example.util.BaseEntity;

@Stateless
@Local
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class GenericAccess {

	@PersistenceContext(unitName = "jee-pu", type = PersistenceContextType.TRANSACTION)
	private EntityManager entityManager;
	
	public <T extends BaseEntity> T save (T entity) {
		if (entity.getLifeCycle() == LifeCycleType.INAKTIV) {
			throw new RuntimeException("You cannot persist [" + entity + "] because the lifecycle is INAKIV");
		}
		entityManager.persist(entity);
		return entity;
	}
	
	public <T extends BaseEntity> T get (int id, Class<T> clazz) {
		return entityManager.find(clazz, id);
	}
	
	public <T extends BaseEntity> T update(T entity) {
		if (entity.getLifeCycle() == LifeCycleType.INAKTIV) {
			throw new RuntimeException("You cannot update [" + entity + "] because the lifecycle is INAKIV");
		}
		
		return entityManager.merge(entity);
	}
	
	public <T> void delete (T entity) {
		entityManager.remove(entity);
	}
	
	public BaseEntity singleResultQuery(String sqlQuery, Map<String, Object> parameters) {
		return (BaseEntity) createQuery(sqlQuery, parameters).getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<BaseEntity> multiResultQuery(String sqlQuery, Map<String, Object> parameters) {
		return createQuery(sqlQuery, parameters).getResultList();
	}
	
	private Query createQuery(String sqlQuery, Map<String, Object> parameters) {
		Query query = entityManager.createQuery(sqlQuery);
		if (parameters != null) {
			for (Map.Entry<String, Object> entry : parameters.entrySet()) {
				query.setParameter(entry.getKey(), entry.getValue());
			}
		}
		return query;
	}
	
}
