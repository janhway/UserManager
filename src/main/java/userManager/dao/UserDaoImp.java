package userManager.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import userManager.dao.UserDao;
import userManager.entity.User;

@Repository
//@Transactional
public class UserDaoImp extends BaseDaoImp<User> implements UserDao {

	static Logger log = Logger.getLogger(UserDaoImp.class);

//	@Autowired
//	public UserDaoImp() {
//		super();
//	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.otv.dao.UserDao#addUser(com.otv.UserEntity.User)
	 */
//	@Override
//	public void addUser(User user) {
//		try {
//			Session session = sessionFactory.getCurrentSession();
//			session.save(user);
//		} catch (Exception e) {
//			log.error("e.getStackTrace():" + e.getStackTrace());
//		}
//	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.otv.dao.UserDao#getUser(java.lang.String)
	 */
	@Override
	public List<User> getUsers(String userName) {

		log.info("can you see me?-->" + userName);

		List<User> userList = (List<User>) getCurrentSession()
				.createQuery(
						"select p from User p where p.userName like :pUserName")
				.setParameter("pUserName", "%" + userName + "%").list();

		return userList;
	}

//	@Override
//	public List<User> getUsers() {
//
//		Session session = sessionFactory.getCurrentSession();
//
//		List<User> userList = (List<User>) session.createQuery("select p from User p").list();
//
//		return userList;
//	}

//	@Override
//	public User load(int id) {
//		// TODO Auto-generated method stub
//		return (User)sessionFactory.getCurrentSession().load(User.class, id);
//	}

	@Override
	public User getUser(String userName) {
		log.info("can you see me?-->" + userName);

		List<User> userList = (List<User>) getCurrentSession()
				.createQuery(
						"select p from User p where p.userName = :pUserName")
				.setParameter("pUserName", userName ).list();

		return userList.size()>0?userList.get(0):null;
	}

}
