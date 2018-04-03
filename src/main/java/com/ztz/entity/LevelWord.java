package com.ztz.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="leavewords")
public class LevelWord {
	//*********************** id
	@Id
	@GenericGenerator(name="pk",strategy="increment")
	@GeneratedValue(generator="pk")
	@Column(name="id")
	private Integer id ;

	//*********************** 内容
	@Column(name="content")
	private String content ;
	@Temporal(TemporalType.DATE)
	@Column(name="level_date")
	private Date level_date ;

	//*********************** note
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="note_id")
	@Cascade(CascadeType.ALL)
	private Note note ;  //哪一个笔记的留言

	//*********************** user
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="user_id")
	@Cascade(CascadeType.ALL)
	private User user ;  //哪一个用户的留的言

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Note getNote() {
		return note;
	}
	public void setNote(Note note) {
		this.note = note;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Date getLevel_date() {
		return level_date;
	}
	public void setLevel_date(Date level_date) {
		this.level_date = level_date;
	}
	@Override
	public String toString() {
		return "LevelWord [id=" + id + ", 留言=" + content + "]";
	}
}