package com.ztz.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.context.annotation.Lazy;

@Entity
@Table(name="note")
@Lazy(true)
public class Note {
	@Id
	@GenericGenerator(name="pk",strategy="increment")
	@GeneratedValue(generator="pk")
	@Column(name="id")
	private Integer id ;
	@Column(name="title")
	private String title ;
	@Column(name="content")
	private String content ;
	@Temporal(TemporalType.DATE)
	@Column(name="uploaddate")
	private Date uploadDate ;
	@Column(name="browserCounts")
	private int browserCounts ;
	@Column(name="isShow")
	private int isShow = 1 ; // 等于1表示显示，等于0表示隐藏

	@Column(name="bgColor")
	private String bgColor ;

	//这是笔记，多个笔记对应1个用户，属于多对一
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="user_id")
	private User user ;

	@OneToMany(mappedBy="note",fetch=FetchType.LAZY)
	@Cascade(CascadeType.REFRESH)
	private List<LevelWord> word = new ArrayList<LevelWord>();
	/*
	@OneToMany(mappedBy="note",fetch=FetchType.LAZY)
	@Cascade(CascadeType.ALL)
	private List<NotePic> notepic = new ArrayList<NotePic>() ;
	*/

	@ManyToMany(mappedBy="collectNotes",fetch=FetchType.EAGER)
	private List<User> collectUser = new ArrayList<User>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	public int getBrowserCounts() {
		return browserCounts;
	}

	public void setBrowserCounts(int browserCounts) {
		this.browserCounts = browserCounts;
	}

	public int getIsShow() {
		return isShow;
	}

	public void setIsShow(int isShow) {
		this.isShow = isShow;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

/*	public List<NotePic> getNotepic() {
		return notepic;
	}

	public void setNotepic(List<NotePic> notepic) {
		this.notepic = notepic;
	}*/

	public List<LevelWord> getWord() {
		return word;
	}

	public void setWord(List<LevelWord> word) {
		this.word = word;
	}

	public String getBgColor() {
		return bgColor;
	}

	public void setBgColor(String bgColor) {
		this.bgColor = bgColor;
	}

	public List<User> getCollectUser() {
		return collectUser;
	}

	public void setCollectUser(List<User> collectUser) {
		this.collectUser = collectUser;
	}

	@Override
	public String toString() {
		return "Note [id=" + id + ", 标题=" + title + ", 内容=" + content
				+ "]";
	}
}
