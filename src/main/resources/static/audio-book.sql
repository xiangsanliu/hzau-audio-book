create schema audio_book collate utf8_general_ci;

create table book
(
  id      int auto_increment
    primary key,
  name    varchar(20)  null,
  author  varchar(20)  null,
  picPath varchar(100) null,
  `desc`  varchar(100) null
);

create table book_audio
(
  id     int auto_increment
    primary key,
  path   varchar(100) null,
  bookId int          null
);

create table book_list
(
  id   int auto_increment
    primary key,
  name varchar(20) null
);

create table book_list_book
(
  bookListId int null,
  bookId     int null
)
  comment '书和书单关系';

