package example.spring.mvccrud.persistence.dao.common;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Preconditions;

@Transactional
public abstract class AbstractHibernateDao<T extends Serializable> implements IOperations<T> {

	private Class<T> clazz;
	
	@Autowired
	private LocalSessionFactoryBean sessionFactory;
	
	protected final void setClazz(final Class<T> clazzToSet) {
		clazz = Preconditions.checkNotNull(clazzToSet);
	}
	
	@Override
	public final T findOneById(final int id) {
		return (T) getCurrentSession().get(clazz, id);
	}
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public final List<T> findAll() {
		return getCurrentSession().createQuery("from " + clazz.getName()).list();
	}
	
	@Override
	public final void create(final T entity) {
		Preconditions.checkNotNull(entity);
		getCurrentSession().saveOrUpdate(entity);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public final T update(final T entity) {
		Preconditions.checkNotNull(entity);
		return (T) getCurrentSession().merge(entity);
	}
	
	@Override
	public final void delete(final T entity) {
		Preconditions.checkNotNull(entity);
		getCurrentSession().delete(entity);
	}
	
	@Override
	public final void deleteById(final int entityId) {
		final T entity = findOneById(entityId);
		Preconditions.checkState(entity != null);
		delete(entity);
		
	}
	
	protected final Session getCurrentSession() {
		return sessionFactory.getObject().getCurrentSession();
	}
}
