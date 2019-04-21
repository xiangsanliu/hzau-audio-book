create database audio_book default charset utf8 collate utf8_general_ci;
use audio_book;

create table activity
(
    id             int auto_increment
        primary key,
    name           varchar(50)                          null,
    `desc`         varchar(255)                         null,
    uploadTime     timestamp  default CURRENT_TIMESTAMP null,
    posterUploaded tinyint(1) default 0                 null comment '是否已上传海报'
)
    comment '濞茶?濮';

create table book
(
    id             int auto_increment
        primary key,
    name           varchar(50)                          null,
    author         varchar(50)                          null,
    picPath        varchar(255)                         null,
    `desc`         varchar(255)                         null,
    addTime        timestamp  default CURRENT_TIMESTAMP null,
    posterUploaded tinyint(1) default 0                 null comment '是否已上传海报'
);

create table book_audio
(
    id     int auto_increment
        primary key,
    name   varchar(100)  null,
    bookId int           null,
    amount int default 0 null comment '鎾?斁閲',
    constraint book_audio_book_id_fk
        foreign key (bookId) references book (id)
            on update cascade on delete cascade
);

create table book_list
(
    id   int auto_increment
        primary key,
    name varchar(50) null
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
    comment '娑旓箑鎷版稊锕?礋閸忓磭閮';

create table user
(
    openid        varchar(50)          not null
        primary key,
    name          varchar(50)          null,
    majorAndClass varchar(50)          null,
    qq            varchar(20)          null,
    phoneNum      varchar(20)          null,
    avatarUrl     varchar(255)         null,
    gender        int                  null,
    graduated     tinyint(1) default 0 null comment '本科生或研究生'
);

create table comment_book_audio
(
    id          int auto_increment
        primary key,
    bookAudioId int                                  null,
    content     varchar(255)                         null comment '评论内容',
    openid      varchar(50)                          null,
    commentTime timestamp  default CURRENT_TIMESTAMP null,
    checked     tinyint(1) default 0                 null,
    approved    tinyint(1) default 0                 null,
    constraint comment_book_audio_short_audio_id_fk
        foreign key (bookAudioId) references book_audio (id)
            on update cascade on delete cascade,
    constraint comment_book_audio_user_openid_fk
        foreign key (openid) references user (openid)
            on update cascade on delete cascade
);

create table favourite_book_audio
(
    openid      varchar(50) null,
    bookAudioId int         null,
    constraint favourite_book_audio_book_audio_id_fk
        foreign key (bookAudioId) references book_audio (id)
            on update cascade on delete cascade,
    constraint favourite_book_audio_user_openid_fk
        foreign key (openid) references user (openid)
            on update cascade on delete cascade
);

create table short_audio
(
    id         int auto_increment
        primary key,
    title      varchar(20)                          null,
    actId      int                                  null,
    actName    varchar(20)                          null,
    content    varchar(255)                         null,
    openid     varchar(50)                          null,
    fileName   varchar(20)                          null,
    approved   tinyint(1) default 0                 null,
    checked    tinyint(1) default 0                 null,
    uploadTime timestamp  default CURRENT_TIMESTAMP null,
    amount     int        default 0                 null comment '鎾?斁閲',
    reason     varchar(20)                          null,
    constraint short_audio_activity_id_fk
        foreign key (actId) references activity (id)
            on update cascade on delete cascade,
    constraint short_audio_user_openid_fk
        foreign key (openid) references user (openid)
            on update cascade on delete cascade
)
    comment '鐭?煶棰';

create table comment_short_audio
(
    id           int auto_increment
        primary key,
    shortAudioId int                                  null,
    content      varchar(100)                         null comment '璇勮?鍐呭?',
    openid       varchar(50)                          null,
    commentTime  timestamp  default CURRENT_TIMESTAMP null,
    checked      tinyint(1) default 0                 null,
    approved     tinyint(1) default 0                 null,
    constraint comment_short_audio_short_audio_id_fk
        foreign key (shortAudioId) references short_audio (id)
            on update cascade on delete cascade,
    constraint comment_short_audio_user_openid_fk
        foreign key (openid) references user (openid)
            on update cascade on delete cascade
);

create table favourite_short_audio
(
    openid       varchar(50) null,
    shortAudioId int         null,
    constraint favourite_short_audio_short_audio_id_fk
        foreign key (shortAudioId) references short_audio (id)
            on update cascade on delete cascade,
    constraint favourite_short_audio_user_openid_fk
        foreign key (openid) references user (openid)
            on update cascade on delete cascade
);

create table thumb_book_audio
(
    openid      varchar(50) not null,
    bookAudioId int         not null,
    primary key (openid, bookAudioId),
    constraint thumb_book_audio_book_audio_id_fk
        foreign key (bookAudioId) references book_audio (id)
            on update cascade on delete cascade,
    constraint thumb_book_audio_user_openid_fk
        foreign key (openid) references user (openid)
            on update cascade on delete cascade
);

create table thumb_comment_book_audio
(
    openid             varchar(50) not null,
    commentBookAudioId int         not null,
    primary key (openid, commentBookAudioId),
    constraint table_name_comment_book_audio_id_fk
        foreign key (commentBookAudioId) references comment_book_audio (id)
            on update cascade on delete cascade,
    constraint thumb_comment_book_audio_user_openid_fk
        foreign key (openid) references user (openid)
            on update cascade on delete cascade
);

create table thumb_comment_short_audio
(
    openid              varchar(50) null,
    commentShortAudioId int         null,
    constraint table_name_comment_short_audio_id_fk
        foreign key (commentShortAudioId) references comment_short_audio (id)
            on update cascade on delete cascade,
    constraint thumb_comment_short_audio_user_openid_fk
        foreign key (openid) references user (openid)
            on update cascade on delete cascade
);

create table thumb_short_audio
(
    openid       varchar(50) not null,
    shortAudioId int         not null,
    primary key (openid, shortAudioId),
    constraint thumb_short_audio_short_audio_id_fk
        foreign key (shortAudioId) references short_audio (id)
            on update cascade on delete cascade,
    constraint thumb_short_audio_user_openid_fk
        foreign key (openid) references user (openid)
            on update cascade on delete cascade
);

insert ignore into activity(id, name) VALUE (0, '非活动');

insert ignore into user(openid, name) value ('admin', '');