SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS tb_album;
DROP TABLE IF EXISTS tb_artist;
DROP TABLE IF EXISTS tb_composition;
DROP TABLE IF EXISTS tb_friend;
DROP TABLE IF EXISTS tb_lyricist;
DROP TABLE IF EXISTS tb_recommend;
DROP TABLE IF EXISTS tb_song;
DROP TABLE IF EXISTS tb_user;




/* Create Tables */

CREATE TABLE tb_album
(
	album_no int NOT NULL AUTO_INCREMENT,
	album_key int NOT NULL,
	album_name text,
	artist_key int,
	artist_name text,
	average_score float,
	issue_date text,
	album_img text,
	PRIMARY KEY (album_no)
);


CREATE TABLE tb_artist
(
	artist_no int NOT NULL AUTO_INCREMENT,
	artist_key int NOT NULL,
	artist_name text,
	sex text,
	nationality_name text,
	act_type_name text,
	genre_names text,
	artist_img text,
	PRIMARY KEY (artist_no)
);


CREATE TABLE tb_composition
(
	composition_no int NOT NULL AUTO_INCREMENT,
	composition_name text,
	composition_img text,
	PRIMARY KEY (composition_no)
);


CREATE TABLE tb_friend
(
	friend_no int NOT NULL AUTO_INCREMENT,
	user_no int NOT NULL,
	friend_user_no int NOT NULL,
	PRIMARY KEY (friend_no)
);


CREATE TABLE tb_lyricist
(
	lyricist_no int NOT NULL AUTO_INCREMENT,
	lyricist_name text,
	lyricist_img text,
	PRIMARY KEY (lyricist_no)
);


CREATE TABLE tb_recommend
(
	recommend_no int NOT NULL AUTO_INCREMENT,
	user_no int NOT NULL,
	album_key int,
	song_key int,
	artist_key int,
	star_point double,
	comment text,
	PRIMARY KEY (recommend_no)
);


CREATE TABLE tb_song
(
	song_no int NOT NULL AUTO_INCREMENT,
	song_key int NOT NULL,
	song_name text,
	album_key text,
	artist_key int,
	artist_name text,
	play_time int,
	issue_date text,
	is_title_song boolean,
	is_hit_song boolean,
	is_adult boolean,
	is_free boolean,
	composition text,
	lyricist text,
	album_name text,
	PRIMARY KEY (song_no)
);


CREATE TABLE tb_user
(
	user_no int NOT NULL AUTO_INCREMENT,
	user_id text NOT NULL,
	user_pw text NOT NULL,
	user_gender int,
	user_nick text,
	user_img text,
	PRIMARY KEY (user_no)
);

CREATE TABLE tb_genre
(	
	genre_no int NOT NULL AUTO_INCREMENT,
	genre_key text NOT NULL,
	genre_name text NOT NULL,
	PRIMARY KEY (genre_no)
);

DROP TABLE IF EXISTS tb_genre;

select * from tb_user;

insert into tb_genre(genre_name, genre_key)
values('중국음악', 'DP1300');

select * from tb_genre;
select * from tb_song;
select * from tb_artist;
select * from tb_album;
select * from tb_user;
select * from tb_user where user_id='test1';
delete from tb_user where user_id='test1';

select * from tb_recommend;
delete from tb_recommend;

insert into tb_user(user_id, user_pw, user_gender, user_nick, user_img) values('test1','b16ed7d24b3ecbd4164dcdad374e08c0ab7518aa07f9d3683f34c2b3c67a15830268cb4a56c1ff6f54c8e54a795f5b87c08668b51f82d0093f7baee7d2981181','2','최유정','00aa2d2bfc18486181c66a683d4fed04.png'); 

select *  from tb_song; 
select * from tb_song where album_img is null;

select * from tb_recommend;
select * from tb_user;
select user_no from tb_user where user_id = '16' ;

update tb_user set user_no=3 
where user_id = 'test1'

select star_point, count(user_no) as cnt 
	from tb_recommend
	where user_no = 16
	group by star_point 
	order by cnt desc;

select max(count(user_no)) from tb_recommend
where user_no = 16;

select count(user_no) as cnt from tb_recommend
where user_no = 16;

select * from tb_song where album_img is null;

update tb_song set album_img =  CONCAT('http://70.12.110.69/cdnimg/image/album/' ,album_key)
where album_img is null;




