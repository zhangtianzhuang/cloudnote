package com.ztz.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.context.annotation.Lazy;


//图片类
@Entity
@Table(name="user")
@Lazy(true)
public class NotePic {
	@Id
	@GenericGenerator(name="pk",strategy="increment")
	@GeneratedValue(generator="pk")
	@Column(name="id")
	private Integer id ;
	@Column(name="dirpath")
	private String dirpath ;//图片路径

	/*@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="note_id")
	@Cascade(CascadeType.ALL)
	private Note note ;*/

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDirpath() {
		return dirpath;
	}
	public void setDirpath(String dirpath) {
		this.dirpath = dirpath;
	}
/*	public Note getNote() {
		return note;
	}
	public void setNote(Note note) {
		this.note = note;
	}*/

	@Override
	public String toString() {
		return "NotePic [id=" + id + ", 照片路径=" + dirpath + "]";
	}
}