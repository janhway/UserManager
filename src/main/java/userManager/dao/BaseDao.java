package userManager.dao;

import java.util.List;

import org.hibernate.Session;

public interface BaseDao<T> {
	
	 /**
	  * 功能：增加记录
	  * 
	  * @param entity
	  */
	 public void create(T entity);

	 /**
	  * 功能：修改记录
	  * 
	  * @param entity
	  */
	 public void update(T entity);

	 /**
	  * 功能：删除记录
	  * 
	  * @param entity
	  */
	 public void delete(T entity);

	 /**
	  * 功能：删除数据
	  * 
	  * @param id
	  */
	 public void delete(long id);


	 /**
	  * 功能：通过主键查询记录
	  * 
	  * @param id
	  * @return T
	  */

	 public T load(int id);

	 /**
	  * 功能：通过主键查询记录
	  * 
	  * @param id
	  * @return T
	  */
	 public T load(long id);
	 
	 /**
	  * 功能：通过主键查询记录
	  * 
	  * @param id
	  * @return T
	  */

	 public T get(int id);

	 /**
	  * 功能：通过主键查询记录
	  * 
	  * @param id
	  * @return T
	  */
	 public T get(long id);
	 
	 
	 public List<T> list();
	 public List<T> list(int start, int count);
	 
	}
