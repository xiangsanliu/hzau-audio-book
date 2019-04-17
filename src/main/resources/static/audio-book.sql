create database audio_book default charset utf8 collate utf8_general_ci;
use audio_book;

create table activity
(
  id int auto_increment
    primary key,
  name varchar(20) null,
  `desc` varchar(200) null,
  uploadTime timestamp default CURRENT_TIMESTAMP null
)
  comment '娲诲姩';

create table book
(
  id int auto_increment
    primary key,
  name varchar(20) null,
  author varchar(20) null,
  picPath varchar(100) null,
  `desc` varchar(100) null,
  source varchar(20) null,
  addTime timestamp default CURRENT_TIMESTAMP null
);

create table book_audio
(
  id int auto_increment
    primary key,
  name varchar(100) null,
  bookId int null,
  amount int default 0 null comment '播放量',
  constraint book_audio_book_id_fk
    foreign key (bookId) references book (id)
      on update cascade on delete cascade
);

create table book_list
(
  id int auto_increment
    primary key,
  name varchar(20) null
);

create table book_list_book
(
  bookListId int null,
  bookId int null,
  constraint book_list_book_book_id_fk
    foreign key (bookId) references book (id)
      on update cascade on delete cascade,
  constraint book_list_book_book_list_id_fk
    foreign key (bookListId) references book_list (id)
      on update cascade on delete cascade
)
  comment '涔﹀拰涔﹀崟鍏崇郴';

create table short_audio
(
  id int auto_increment
    primary key,
  actId int null,
  actName varchar(20) null,
  content varchar(100) null,
  openid varchar(20) null,
  fileName varchar(20) null,
  approved tinyint(1) default 0 null,
  checked tinyint(1) default 0 null,
  uploadTime timestamp default CURRENT_TIMESTAMP null,
  amount int default 0 null comment '播放量',
  reason varchar(20) null,
  constraint short_audio_activity_id_fk
    foreign key (actId) references activity (id)
      on update cascade on delete cascade
)
  comment '短音频';

create table user
(
  openid varchar(50) not null
    primary key,
  name varchar(20) null,
  majorAndClass varchar(20) null,
  qq varchar(20) null,
  phoneNum varchar(20) null,
  avatarUrl varchar(100) null,
  gender int null
);

create table comment_book_audio
(
  id int auto_increment
    primary key,
  bookAudioId int null,
  content varchar(100) null comment '评论内容',
  openid varchar(20) null comment '评论人',
  commentTime timestamp default CURRENT_TIMESTAMP null,
  checked tinyint(1) default 0 null,
  approved tinyint(1) default 0 null,
  constraint comment_book_audio_short_audio_id_fk
    foreign key (bookAudioId) references book_audio (id)
      on update cascade on delete cascade,
  constraint comment_book_audio_user_openid_fk
    foreign key (openid) references user (openid)
      on update cascade on delete cascade
);

create table comment_short_audio
(
  id int auto_increment
    primary key,
  shortAudioId int null,
  content varchar(100) null comment '评论内容',
  openid varchar(20) null comment '评论人',
  commentTime timestamp default CURRENT_TIMESTAMP null,
  checked tinyint(1) default 0 null,
  approved tinyint(1) default 0 null,
  constraint comment_short_audio_short_audio_id_fk
    foreign key (shortAudioId) references short_audio (id)
      on update cascade on delete cascade,
  constraint comment_short_audio_user_openid_fk
    foreign key (openid) references user (openid)
      on update cascade on delete cascade
);

create table favourite_book_audio
(
  openid varchar(50) null,
  bookAudioId int null,
  constraint favourite_book_audio_book_audio_id_fk
    foreign key (bookAudioId) references book_audio (id)
      on update cascade on delete cascade,
  constraint favourite_book_audio_user_openid_fk
    foreign key (openid) references user (openid)
      on update cascade on delete cascade
);

create table favourite_short_audio
(
  openid varchar(50) null,
  shortAudioId int null,
  constraint favourite_short_audio_short_audio_id_fk
    foreign key (shortAudioId) references short_audio (id)
      on update cascade on delete cascade,
  constraint favourite_short_audio_user_openid_fk
    foreign key (openid) references user (openid)
      on update cascade on delete cascade
);

create table thumb_book_audio
(
  openid varchar(50) null,
  bookAudioId int null,
  constraint thumb_book_audio_book_audio_id_fk
    foreign key (bookAudioId) references book_audio (id)
      on update cascade on delete cascade,
  constraint thumb_book_audio_user_openid_fk
    foreign key (openid) references user (openid)
      on update cascade on delete cascade
);

create table thumb_comment_book_audio
(
  openid varchar(50) null,
  commentBookAudioId int null,
  constraint table_name_comment_book_audio_id_fk
    foreign key (commentBookAudioId) references comment_book_audio (id)
      on update cascade on delete cascade,
  constraint thumb_comment_book_audio_user_openid_fk
    foreign key (openid) references user (openid)
      on update cascade on delete cascade
);

create table thumb_comment_short_audio
(
  openid varchar(50) null,
  commentShortAudioId int null,
  constraint table_name_comment_short_audio_id_fk
    foreign key (commentShortAudioId) references comment_short_audio (id)
      on update cascade on delete cascade,
  constraint thumb_comment_short_audio_user_openid_fk
    foreign key (openid) references user (openid)
      on update cascade on delete cascade
);

create table thumb_short_audio
(
  openid varchar(50) null,
  shortAudioId int null,
  constraint thumb_short_audio_short_audio_id_fk
    foreign key (shortAudioId) references short_audio (id)
      on update cascade on delete cascade,
  constraint thumb_short_audio_user_openid_fk
    foreign key (openid) references user (openid)
      on update cascade on delete cascade
);

insert into activity(id, name) VALUE (0, '非活动');