package com.ztz.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Service;

import com.ztz.bean.PageBean;
import com.ztz.dao.NoteDAO;
import com.ztz.dao.UserDAO;
import com.ztz.entity.Note;
import com.ztz.entity.User;
import com.ztz.service.NoteService;
import com.ztz.utils.Utils;

@Service("noteServiceImpl")
public class NoteServiceImpl implements NoteService{

	@Resource(name="noteDAOImpl")
	private NoteDAO noteDAOImpl ;

	@Resource(name="userDAOImpl")
	private UserDAO userDAOImpl ;

	@Override
	public int publishNote(Note note) {
		String currentUsername = Utils.getCurrentUser().getUsername();//取得当前用户名
		User cUser = userDAOImpl.findByUserName(currentUsername);//查找到当前用户
		if(cUser==null){
			return -1 ;
		}
		note.setUploadDate(new Date(System.currentTimeMillis())); //设置发布日期
		cUser.getNotes().add(note);
		note.setUser(cUser);
		noteDAOImpl.add(note);
		System.out.println("----------发布云笔记成功**********");
		return 0;
	}

	@Override
	public int motifyNote(Note note) {
		System.out.println(note.getUser()+"------------xiugai");
		noteDAOImpl.motify(note);

		System.out.println("--------NoteService-----motifyNote()");
		return 0;
	}

	@Override
	public int deleteNote(Note note) {
		return 0;
	}

	@Override
	public int collectNote(Note note) {

		return 0;
	}

	@Override
	public int shareNote(Note note) {
		return 0;
	}

	@Override
	public List<Note> select(int pageIndex, int pageSize, String hql) {
		int counts = noteDAOImpl.getCounts("select count(*) "+hql);
		System.out.println("--------查询到符合条件的笔记有"+counts+"条");
		PageBean<Note> pageBean = new PageBean<Note>(counts,pageSize);
		System.out.println("页码索引："+pageBean.getPageIndex());
		System.out.println("是否有前一页？"+pageBean.isHasPreviousPage());
		System.out.println("是否有后一页？"+pageBean.isHasNextPage());
		HttpSession session = ServletActionContext.getRequest().getSession();
		session.setAttribute("pageBean", pageBean);
		System.out.println("将pageBean存储到session中");
		List<Note> noteList = noteDAOImpl.findByPage(pageIndex, pageSize,hql);
		return noteList;
	}

	@Override
	public Note select(int id) {
		return noteDAOImpl.findById(id);
	}
}
