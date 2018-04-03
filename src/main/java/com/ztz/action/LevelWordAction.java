package com.ztz.action;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.ztz.entity.LevelWord;
import com.ztz.entity.Note;
import com.ztz.entity.User;
import com.ztz.service.LevelWordService;
import com.ztz.service.NoteService;
import com.ztz.utils.Json;

@Controller("levelWordAction")
public class LevelWordAction {

	private int id ;
	private String content ;

	@Resource(name="noteServiceImpl")
	private NoteService noteServiceImpl;

	@Resource(name="levelWordServiceImpl")
	private LevelWordService levelWordService ;
	//留言
	public String level(){
		System.out.println("---------------执行level()方法:"+content);
		HttpSession session = ServletActionContext.getRequest().getSession();
		User user = (User) session.getAttribute("user"); //哪个用户进行评论
		Note note = noteServiceImpl.select(id); //获取要评论的id

		LevelWord word = new LevelWord();
		word.setContent(content);
		word.setUser(user);//设置user
		word.setNote(note); //设置note
		word.setLevel_date(new Date());

		levelWordService.insert(word); //存入留言
		return null;
	}

	private int id2 ;

	public String select(){
		HttpServletResponse response = ServletActionContext.getResponse();
		List<LevelWord> result = levelWordService.select(id2);
		String json = Json.toJson(result,"note","user");
		System.out.println("------> json :" + json);
		Json.pritnTo(response, "json", json);
		return null ;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId2() {
		return id2;
	}

	public void setId2(int id2) {
		this.id2 = id2;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
