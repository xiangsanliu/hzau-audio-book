create database audio_book default charset utf8 collate utf8_general_ci;
use audio_book;

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
  name   varchar(100) null,
  bookId int          null,
  constraint book_audio_book_id_fk
    foreign key (bookId) references book (id)
      on update cascade on delete cascade
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
  bookId     int null,
  constraint book_list_book_book_id_fk
    foreign key (bookId) references book (id)
      on update cascade on delete cascade,
  constraint book_list_book_book_list_id_fk
    foreign key (bookListId) references book_list (id)
      on update cascade on delete cascade
)
  comment '书和书单关系';

