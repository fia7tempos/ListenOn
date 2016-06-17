SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS tb_album;
DROP TABLE IF EXISTS tb_artist;
DROP TABLE IF EXISTS tb_song;

DROP TABLE IF EXISTS tb_composition;
DROP TABLE IF EXISTS tb_friend;
DROP TABLE IF EXISTS tb_lyricist;
DROP TABLE IF EXISTS tb_recommend;

DROP TABLE IF EXISTS tb_user;




/* Create Tables */

CREATE TABLE tb_album
(
	album_no int NOT NULL AUTO_INCREMENT,
	album_key int NOT NULL,
	artist_key int NOT NULL,
	album_name text NOT NULL,
	average_score float,
	issue_date text,
	album_cover text,
	PRIMARY KEY (album_no)
);

CREATE TABLE tb_artist
(
	artist_no int NOT NULL AUTO_INCREMENT,
	artist_key int NOT NULL,
	genre_names text,
	act_type_name text,
	artist_sex text,
	artist_name text NOT NULL,
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
	album_no int NOT NULL,
	song_no int NOT NULL,
	artist_no int NOT NULL,
	star_point double NOT NULL,
	comment text,
	PRIMARY KEY (recommend_no)
);


CREATE TABLE tb_song
(
	song_no int NOT NULL AUTO_INCREMENT,
	song_key int NOT NULL,
	album_key int NOT NULL,
	song_name text,
	play_time text ,
	issue_date text ,
	is_title_song boolean ,
	is_adult boolean,
	is_hit_song boolean,
	is_free boolean,
	lyrics text,
	compostion text,
	lyricist text,
	PRIMARY KEY (song_no)
);

CREATE TABLE tb_user
(
   user_no int NOT NULL AUTO_INCREMENT,
   user_id text NOT NULL,
   user_pw text,
   user_gender int,
   user_nick text,
   user_img text,
   user_uuid text,
   PRIMARY KEY (user_no)
);
commit

ALTER TABLE tb_artist
ADD artist_img text;

ALTER TABLE tb_song
add COLUMN composition int;

ALTER TABLE tb_user
add COLUMN user_birth int;

select * from tb_user;


, album_no, song_no, star_point

select arti.*, recom.star_point
from (select genreNames, artist_key, artist_sex, artist_sex, artistName
	  from  tb_artist	
	  where artist_no in (select artist_no
	 					  from tb_recommend
					      where user_no = 1) arti) join tb_recommend recom
where recom.

describe tb_song;

select * from tb_song;

select count(song_key)
from tb_song
where song_key = 3680767;

select * from tb_song;

insert into tb_song(song_key, album_key,song_name,play_time,issue_date,is_title_song,is_adult,is_hit_song,is_free)
values(#{song_key},#{album_key},#{song_name},#{play_time},#{issue_date},#{is_title_song},#{is_adult},#{is_hit_song},#{is_free})


select count(*) from tb_user
where user_id = 'test1';

select * from tb_user

delete from tb_user;

select * from tb_recommend;

select * from tb_song
where song_key = 5652904;

select * from tb_song
where song_id;

(select song_key 
from tb_recommend
where user_no = 3)

select *
from tb_song;


select * from tb_recommend;
where user_no = 5;

select artist_name, count(artist_name) as cnt
from tb_song
where song_key in (select song_key
				   from tb_recommend
				   where user_no = 633)
group by artist_name
order by cnt desc

select * from tb_user
update tb_user set user_no=3 where user_id = 'test'
select * from tb_recommend

select * from tb_user;

rollback;

update tb_user set user_no = 3 where user_id = 'test1';

select artist_name, artist_key, count(artist_name) as cnt
from tb_song
where song_key in (select song_key
				   from tb_recommend
				   where user_no = 3)
group by artist_name, artist_key
order by cnt desc
limit 10