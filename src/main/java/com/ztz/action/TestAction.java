package com.ztz.action;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Controller;

import com.ztz.dao.NoteDAO;
import com.ztz.dao.UserDAO;
import com.ztz.entity.Note;
import com.ztz.entity.User;

@Controller("testAction")
public class TestAction {

	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory ;

	@Resource(name="userDAOImpl")
	private UserDAO userDAOImpl;

	@Resource(name="noteDAOImpl")
	private NoteDAO noteDAOImpl ;

	public String test(){
//		System.out.println(sessionFactory);
//		Session session = sessionFactory.getCurrentSession();
//		System.out.println("---------输出session");
//		System.out.println(session);
//		User user = new User();
//		user.setId(3);
//		user.setUsername("ztz3");
//		user.setPassword("ztz3");
//		userDAOImpl.update(user);

//		User user = userDAOImpl.findByUserName("ztz");
//		System.out.println("id:"+user.getId()+",username:"+user.getUsername()+",password:"+user.getPassword());

//		User user = new User();
//		user.setPassword("tian");
//		user.setUsername("tian");
//		userDAOImpl.add(user);

		//userDAOImpl.motify(user, user.getId());
		//userDAOImpl.delete(user);

//		User user = new User();
//		user.setId(3);
//		user.setUsername("ztz");
//		user.setPassword("ztz");

//		User user = userDAOImpl.findByUserName("ztz");
//		Note note = new Note();
//		note.setTitle("笔记标题");
//		note.setContent("笔记内容");
//		note.setUser(user);
//		user.getNotes().add(note);
//		
//		noteDAOImpl.add(note);

		User u = userDAOImpl.findByUserName("ztz");
		Note note = noteDAOImpl.findById(2);
		note.setContent("newContent");
		noteDAOImpl.motify(note);

//		System.out.println("-------user :"+u);
//		List<Note> list = u.getNotes();
//		for(Note note:list){
//			System.out.println("-------note :" + note);
//		}

		System.out.println("-------------execute()-------------testAction----------");
		return "success";
	}
}
