#创建数据库
drop database if exists cloudnote ;
create database cloudnote ;
#使用数据库
use cloudnote ;
#创建用户表
drop table if exists user ;
create table user(
	id integer auto_increment,
	name varchar(20) not null ,
	password varchar(20) not null ,
	age integer ,
	sex varchar(4),
	email varchar(30),
	phone varchar(11),
	habit text,
	province varchar(30),
	city varchar(30),
	county varchar(30),
	qqNum varchar(20),
	weChat varchar(30),
	registerDate varchar(20),
	primary  key(id)
) engine=InnoDB auto_increment=18 default charset=utf8 ;

#创建笔记表
create table note(
	id integer auto_increment,
	title varchar(50) not null,
	content text not null,
	uploaddate Date,
	browserCounts integer,
	isShow integer not null,
	user_id integer references user(id),
	primary key(id)
)engine=InnoDB auto_increment=18 default charset=utf8 ;

#创建图片表，存放笔记中的图片
create table notepic(
	id integer auto_increment,
	dirpath text not null,
	note_id integer references note(id),
	primary key(id)
)engine=InnoDB auto_increment=18 default charset=utf8 ;

#创建云笔记留言表
drop table leavewords;
create table leavewords(
	id integer auto_increment,
	content text not null,
	note_id integer references note(id),
	user_id integer references user(id),
	primary key(id)
)engine=InnoDB auto_increment=18 default charset=utf8 ;


drop table if exists collect;
create table collect(
	u_id	integer	  references  user(id),
	n_id	integer	  references  note(id),
	primary key(u_id,n_id)
);
