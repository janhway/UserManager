package userManager.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseDaoImp<T> implements BaseDao<T> {

	static Logger log = Logger.getLogger(BaseDaoImp.class);
	
	@Autowired	
	private SessionFactory sessionFactory;

	private Class<T> entityClass;

	public BaseDaoImp() {

		Type tt = this.getClass().getGenericSuperclass();

		if (tt instanceof Class) {
			tt = ((Class<?>) tt).getGenericSuperclass();
		}

		ParameterizedType bb = (ParameterizedType) tt;

		entityClass = (Class<T>) bb.getActualTypeArguments()[0];
		
		log.info("entityClass="+entityClass.getName());
	}
	
	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public void create(T entity) {
		sessionFactory.getCurrentSession().save(entity);
	}

	@Override
	public void update(T entity) {
		sessionFactory.getCurrentSession().update(entity);
	}

	@Override
	public void delete(T entity) {
		sessionFactory.getCurrentSession().delete(entity);
	}

	@Override
	public void delete(long id) {
		sessionFactory.getCurrentSession().delete(entityClass.getName(), id);
	}

	@Override
	public T load(int id) {
		return (T)sessionFactory.getCurrentSession().load(entityClass, id);
	}

	@Override
	public T load(long id) {
		return (T)sessionFactory.getCurrentSession().load(entityClass, id);
	}	

	@Override
	public T get(int id) {
		return (T)sessionFactory.getCurrentSession().get(entityClass, id);
	}

	@Override
	public T get(long id) {
		return (T)sessionFactory.getCurrentSession().get(entityClass, id);
	}
	
	public List<T> list() {
		Criteria c = sessionFactory.getCurrentSession().createCriteria(entityClass.getName());
		c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<T> ret = (List<T>) c.list(); 		
		return ret;
	}
	
	public List<T> list(int start, int count) {
		Criteria c = sessionFactory.getCurrentSession().createCriteria(entityClass.getName());
		//c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		c.setFirstResult(start);
		c.setMaxResults(count);
		List<T> ret = (List<T>) c.list(); 		
		return ret;
	}

}
