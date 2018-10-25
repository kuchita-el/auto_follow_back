create sequence seq_follow_keyword_id;

create table follow_keyword(
  id int not null default nextval('seq_follow_keyword_id') primary key,
  userid varchar(255) not null,
  providerid varchar(255) not null,
  keyword varchar(255) not null,
  unique(userid, providerid)
);