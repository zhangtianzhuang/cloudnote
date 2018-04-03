package com.ztz.dao.impl;
//
//import java.util.List;
//
//import org.hibernate.Query;
//import org.hibernate.Session;
//import org.hibernate.Transaction;
//
//import com.ztz.dao.UserDAO;
//import com.ztz.entity.User;
//import com.ztz.hibernate.HibernateUtil;
//
public class UserDaoImpl  {
//
//	public static final int FLAG_SAVE = 0;
//	public static final int FLAG_FIND_BY_ID = 1;
//	public static final int FLAG_FIND_BY_USER = 2;
//	public static final int FLAG_UPDATE = 3;
//	public static final int FLAG_DELETE_BY_USER = 4;
//	public static final int FLAG_DELETE_BY_ID = 5;
//	public static final int FLAG_FIND_ALL_USER = 6;
//
//	// 创建Session实例
//	private Session session;
//	// 获取Transaction实例
//	private Transaction tc;
//
//	public static UserDAO getInstance(){
//		return new UserDaoImpl() ;
//	}
//
//	@Override
//	public int insert(User user) {
//		return doTask(user, -1, FLAG_SAVE);
//	}
//
//	@Override
//	public int delete(int id) {
//		return 0;
//	}
//
//	@Override
//	public int delete(User user) {
//		return 0;
//	}
//
//	@Override
//	public int update(User user) {
//		return 0;
//	}
//
//	@Override
//	public User findById(int id) {
//		return doTaskGetUser(null, id, FLAG_FIND_BY_ID);
//	}
//
//	@Override
//	public User findByUser(User user) {
//		return doTaskGetUser(user, -1, FLAG_FIND_BY_USER);
//	}
//
//	@Override
//	public List<User> findAllUser() {
//		return null;
//	}
//
//	private int doTask(User user,int id,int type) {
//		session = HibernateUtil.getsSession();
//		tc = session.beginTransaction();
//		try {
//			switch (type) {
//			//插入数据
//			case FLAG_SAVE:
//				session.save(user);// 将user插入到数据库
//				break;
//			}
//			tc.commit();// 提交事务
//			return 0 ;
//		} catch (Exception e) {
//			e.printStackTrace();
//			tc.rollback(); // 事务回滚
//			return -1 ;
//		} finally {
//			HibernateUtil.closeSession(); // 关闭是session连接
//		}
//	}
//
//	private User doTaskGetUser(User user,int id,int type){
//		session = HibernateUtil.getsSession();
//		tc = session.beginTransaction();
//		User u = null ;
//		try {
//			switch (type) {
//			//通过UserName查询User
//			case FLAG_FIND_BY_USER :
//				String hql = "from User where username = ? " ;
//				Query query = session.createQuery(hql);
//				query.setString(0, user.getUsername());
//				u = (User) query.uniqueResult();
//				break ;
//			case FLAG_UPDATE :
//				session.update(user);
//				break ;
//			}
//			tc.commit();// 提交事务
//			return u ;
//		} catch (Exception e) {
//			e.printStackTrace();
//			tc.rollback(); // 事务回滚
//		} finally {
//			HibernateUtil.closeSession(); // 关闭是session连接
//		}
//		return null ;
//	}

}