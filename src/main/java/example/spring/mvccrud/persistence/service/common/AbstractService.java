package example.spring.mvccrud.persistence.service.common;

import java.io.Serializable;
import java.util.List;

import example.spring.mvccrud.persistence.dao.common.IOperations;

public abstract class AbstractService<T extends Serializable> implements IOperations<T> {

	@Override
	public T findOneById(final int id) throws Throwable {
		return getDao().findOneById(id);
	}
	
	@Override
	public List<T> findAll() throws Throwable {
		return getDao().findAll();
	}
	
	@Override
	public void create(final T entity) throws Throwable {
		getDao().create(entity);
	}
	
	@Override
	public T update(final T entity) throws Throwable {
		return getDao().update(entity);
	}
	
	@Override
	public void delete(final T entity) throws Throwable {
		getDao().delete(entity);
	}
	
	@Override
	public void deleteById(final int entityId) throws Throwable {
		getDao().deleteById(entityId);
	}
	
	protected abstract IOperations<T> getDao();
}
