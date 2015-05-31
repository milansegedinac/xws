package rs.ac.uns.ftn.xws.sessionbeans.common;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

//import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;

public abstract class GenericDaoBean<T, ID extends Serializable> implements
		GenericDao<T, ID> {

	//private static Logger log = Logger.getLogger(GenericDaoBean.class);

	protected Class<T> entityType;

	@PersistenceContext(unitName = "xws")
	protected EntityManager em;
		
	@SuppressWarnings("unchecked")
	public GenericDaoBean() {
		entityType = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public Class<T> getEntityType() {
		return entityType;	
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll() {
		Session session = getHibernateSession();
		Criteria criteria = session.createCriteria(entityType);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);	
		return criteria.list();
	}
		
	@Override
	public T findById(ID id) {
		T entity;
		entity = em.find(entityType, id);
		return entity;
	}
		
	protected Session getHibernateSession(){
		return em.unwrap(org.hibernate.Session.class);
	}
		
	@Override
	@SuppressWarnings("unchecked")
	public List<T> findBy(String query) {		
		Query q = em.createQuery(query);
		List<T> result = q.getResultList();
		return result;
	}
	
	@Override
	public T persist(T entity) throws NoSuchFieldException {
		em.persist(entity);
		return entity;
	}

	@Override
	public T merge(T entity) throws NoSuchFieldException {
		entity = em.merge(entity);
		return entity;
	}
	
	@Override
	public void remove(T entity) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
		try {
			// Logically deletes entities if 'deleted' field exist
			Method setter = entity.getClass().getMethod("setDeleted", boolean.class); 
			setter.invoke(entity, true);
			em.merge(entity);
		} catch (NoSuchMethodException ex) {
			// Physically deletes entities otherwise
			entity = em.merge(entity);
			em.remove(entity);	
		}	
	}
	
	@Override
	public void remove(ID id) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
		T entity = findById(id);
		remove(entity);
	}
	
	@Override
	public void removeAll() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Query q = em.createNativeQuery("DELETE FROM "+ entityType.getSimpleName());
		q.executeUpdate();
	}

	@Override
	public void flush() {
		em.flush();
	}
	
	@Override
	public void clear() {
		em.clear();
	}
	

}
