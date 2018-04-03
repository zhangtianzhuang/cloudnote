package com.ztz.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="collect")
public class Collect {
	
	@Id
	@GenericGenerator(name="pk",strategy="increment")
	@GeneratedValue(generator="pk")
	@Column(name="id")
	private Integer id ;
	
	@Column(name="u_id")
	private int u_id ;
	@Column(name="n_id")
	private int n_id ;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getU_id() {
		return u_id;
	}
	public void setU_id(int u_id) {
		this.u_id = u_id;
	}
	public int getN_id() {
		return n_id;
	}
	public void setN_id(int n_id) {
		this.n_id = n_id;
	}
}
