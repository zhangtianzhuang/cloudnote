package com.ztz.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;

import com.ztz.dao.CollectDAO;
import com.ztz.entity.Collect;
import com.ztz.entity.Note;
import com.ztz.entity.User;
import com.ztz.service.CollectService;
import com.ztz.service.NoteService;
import com.ztz.service.UserService;
import com.ztz.service.impl.UserServiceImpl;
import com.ztz.utils.Json;

@Controller("noteAction")
public class NoteAction {

	private Note note;// 云笔记
	private String username;// 用户名
	private String keys ; //搜索关键字
	private int id = -1 ;

	private int noteid ;
	private int userid ;

	@Resource(name="userServiceImpl")
	private UserService userServiceImpl ;

	@Resource(name = "noteServiceImpl")
	private NoteService noteServiceImpl;

	@Resource(name="collectServiceImpl")
	private CollectService collectServiceImpl ;

	// 发布云笔记
	public String publish() {
		noteServiceImpl.publishNote(note);
		return "publish-success";
	}

	// 修改云笔记
	public String motify() {
		System.out.println("--------NoteAction-----motify()");
		note.setId(id);
		noteServiceImpl.motifyNote(note);
		return "motify-success";
	}
	// 删除云笔记
	public String delete() {
		return null;
	}

	// 收藏云笔记
	public String collect() {
		HttpSession session = ServletActionContext.getRequest().getSession();
		User user = (User) session.getAttribute("user");
		Note note = noteServiceImpl.select(id);
		user.getCollectNotes().add(note);
		userServiceImpl.motify(user);
		System.out.println("--------重新向session中村user");
		session.setAttribute("user", user);
		System.out.println("----------收藏笔记"+id+"成功");
		return null;
	}

	public String isCollect(){
		HttpSession session = ServletActionContext.getRequest().getSession();
		HttpServletResponse response = ServletActionContext.getResponse();
		/*User u = (User) session.getAttribute("user");
		User user = userServiceImpl.findUserById(u.getId());*/
		User user = (User) session.getAttribute("user");
		if(user==null){
			return null ;
		}
		List<Note> collectNotes = user.getCollectNotes();
		boolean isCollect = false ;
		for(int i=0;i<collectNotes.size();i++){
			System.out.println("-------->  这是我的id"+ collectNotes.get(i).getId());
			if(id==collectNotes.get(i).getId()){
				System.out.println("查询到了********id:"+id+",,,,,,get(i)id");
				isCollect = true ;
				break ;
			}
		}
		if(isCollect){
			Json.pritnTo(response, "plain", "hasCollect");
		}else{
			Json.pritnTo(response, "plain", "noCollect");
		}
		return null ;
	}

	public String selectCollect(){
		HttpSession session = ServletActionContext.getRequest().getSession();
		HttpServletResponse response = ServletActionContext.getResponse();
		System.out.println("-----执行selectCollect（）");
		User user = (User) session.getAttribute("user");
		List<Note> collectNotes = user.getCollectNotes();
		System.out.println("ddddddddddddd查询");
		System.out.println(collectNotes.size());
		for(Note note:collectNotes){
			System.out.println("笔记名称："+note.getTitle());
		}
		String json = Json.toJson(collectNotes,"word","user","collectUser");
		System.out.println(json);
		Json.pritnTo(response, "json", json);
		return null ;
	}

	// 分享云笔记
	public String share() {
		return null;
	}

	private int pageIndex = 1 ;

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	// 查询个人云笔记
	public String select() {
		HttpServletResponse response = ServletActionContext.getResponse();
		System.out.println("请求的id为："+id);
		//如果id为-1，表示要请求是一个列表
		if(id==-1){  // 查询某一用户所有的笔记
			System.out.println("*****************请求note列表");
			HttpSession session = ServletActionContext.getRequest().getSession();
			User user = (User) session.getAttribute("user");
			String hql = "from Note n where n.user.id = " + user.getId();
			//如果有搜索关键字的，添加上
			if(keys!=null){
				hql += " and n.title like "+ "'%"+keys+"%'" ;
			}
			//按上传日期倒叙排
			hql += " order by n.id desc ";
			System.out.println("--------------排序");
			List<Note> select = noteServiceImpl.select(pageIndex,20,hql);
			System.out.println("--------list.size():" + select.size());

			String json = Json.toJson(select,"notepic","word","user","collectUser");
			System.out.println("------> json :" + json);
			Json.pritnTo(response, "json", json);
			//一定要返回null
			return  null;
		}else if(id==-2){ //查询所有用户的云笔记
			String hql = "from Note n where n.isShow = "+1+" order by n.id desc";
			List<Note> select = noteServiceImpl.select(0, 20,hql);
			String json = Json.toJson(select,"notepic","word","user","collectUser");
			Json.pritnTo(response, "json", json);
			System.out.println(json);
			return null ;
		}else if(id==-3){ //根据浏览量查询所有用户笔记
			String hql = "from Note n where n.isShow = "+1+" order by n.browserCounts desc";
			List<Note> select = noteServiceImpl.select(0, 4,hql);
			String json = Json.toJson(select,"notepic","word","user","collectUser");
			Json.pritnTo(response, "json", json);
			System.out.println(json);
			return null ;
		}else if(id==-4){ //查询某一用户其他笔记
			HttpServletRequest request = ServletActionContext.getRequest();
			System.out.println("------noteid = " + noteid);
			System.out.println("-------userid= " + userid);
			int userid = Integer.parseInt(request.getParameter("userid"));
			int noteid = Integer.parseInt(request.getParameter("userid"));
			String hql = "from Note n where n.isShow = "+1+" and n.id <>"+ noteid + " and n.user.id = " + userid+" order by n.id desc " ;
			List<Note> select = noteServiceImpl.select(0, 4,hql);
			String json = Json.toJson(select,"notepic","word","user","collectUser");
			Json.pritnTo(response, "json", json);
			System.out.println(json);
			return null ;
		}else if(id>=1){ //如果id=n(n>=1)表示请求是 id为n的note
			System.out.println("*****************请求单个note");
			HttpSession session = ServletActionContext.getRequest().getSession();
			User user = (User) session.getAttribute("user");
			Note note = noteServiceImpl.select(id);
			if(user!=null && !user.getUsername().equals(note.getUser().getUsername())){ //当登录后且发布人与查看不是同一人时增加访问量
				System.out.println("------------进行更新浏览量");
				int currentBrowser = note.getBrowserCounts(); //获取当前浏览量
				currentBrowser ++ ; // +1
				note.setBrowserCounts(currentBrowser); //设置新的浏览量
				noteServiceImpl.motifyNote(note);
			}
			session.setAttribute("note", note);
			return "select-one-note";
		}
		return null ;
	}

	public Note getNote() {
		return note;
	}

	public void setNote(Note note) {
		this.note = note;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getKeys() {
		return keys;
	}

	public void setKeys(String keys) {
		this.keys = keys;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNoteid() {
		return noteid;
	}

	public void setNoteid(int noteid) {
		this.noteid = noteid;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}
}