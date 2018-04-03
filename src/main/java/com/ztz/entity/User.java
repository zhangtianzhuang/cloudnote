package com.ztz.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.context.annotation.Lazy;

@Entity
@Table(name="user")
@Lazy(true)
public class User {
	@Id
	@GenericGenerator(name="pk",strategy="increment")
	@GeneratedValue(generator="pk")
	@Column(name="id")
	private int id ;  //唯一标识
	@Column(name="name")
	private String username ;  //用户名
	@Column(name="password")
	private String password ;  //密码
	@Column(name="sex")
	private String sex ;	   //性别
	@Column(name="age")
	private int age ;		   //年龄
	@Column(name="phone")
	private String phone ;     //手机号码
	@Column(name="email")
	private String email;	   //邮箱
	@Column(name="habit")
	private String habit ;	   //兴趣爱好
	@Column(name="province")
	private String province ;  //省
	@Column(name="city")
	private String city ;	   //城市
	@Column(name="county")
	private String county ;	   //县
	@Column(name="qqNum")
	private String qqNum ;	   //QQ
	@Column(name="weChat")
	private String weChat ;	   //微信
	@Column(name="registerDate")
	private String registerDate;//注册时间

	//用户与笔记属于一对多关系
	@OneToMany(mappedBy="user",fetch=FetchType.EAGER)
	@Cascade(CascadeType.PERSIST)
	private List<Note> notes = new ArrayList<Note>();

	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="collect",
			joinColumns={@JoinColumn(name="u_id")},
			inverseJoinColumns={@JoinColumn(name="n_id")})
	@Cascade(CascadeType.PERSIST)
	private List<Note> collectNotes = new ArrayList<Note>();

	public User(){
	}

	public User(int id, String username, String password, String sex, int age, String phone, String habit,
				String province, String city, String county, String qqNum, String weChat, String registerDate) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.sex = sex;
		this.age = age;
		this.phone = phone;
		this.habit = habit;
		this.province = province;
		this.city = city;
		this.county = county;
		this.qqNum = qqNum;
		this.weChat = weChat;
		this.registerDate = registerDate;
	}

	public List<Note> getCollectNotes() {
		return collectNotes;
	}

	public void setCollectNotes(List<Note> collectNotes) {
		this.collectNotes = collectNotes;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getHabit() {
		return habit;
	}

	public void setHabit(String habit) {
		this.habit = habit;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getQqNum() {
		return qqNum;
	}

	public void setQqNum(String qqNum) {
		this.qqNum = qqNum;
	}

	public String getWeChat() {
		return weChat;
	}

	public void setWeChat(String weChat) {
		this.weChat = weChat;
	}

	public String getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}

	public List<Note> getNotes() {
		return notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public String showInfo() {
		return "User [id=" + id + ", 用户名=" + username + ", 密码="
				+ password + "]";
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password="
				+ password + ", sex=" + sex + ", age=" + age + ", phone="
				+ phone + ", email=" + email + ", habit=" + habit
				+ ", province=" + province + ", city=" + city + ", county="
				+ county + ", qqNum=" + qqNum + ", weChat=" + weChat
				+ ", registerDate=" + registerDate + "]";
	}
}