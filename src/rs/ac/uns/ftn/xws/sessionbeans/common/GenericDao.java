package rs.ac.uns.ftn.xws.sessionbeans.common;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface GenericDao<T, ID extends Serializable> {

	public List<T> findAll();
	
	public T findById(ID id);
		
	public List<T> findBy(String query);
	
	public T persist(T entity) throws NoSuchFieldException;

	public T merge(T entity) throws NoSuchFieldException;

	public void remove(T entity) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException;
	
	public void remove(ID id) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException;

	public void removeAll() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException;
	
	public void flush();

	public void clear();
	
}
