package example.spring.mvccrud.persistence.dao.common;

import java.io.Serializable;
import java.util.List;

public interface IOperations<T extends Serializable> {

	T findOneById(final int id) throws Throwable;
	
	List<T> findAll() throws Throwable;
	
	void create(final T entity) throws Throwable;
	
	T update(final T entity) throws Throwable;
	
	void delete(final T entity) throws Throwable;
	
	void deleteById(final int entityId) throws Throwable;
}
