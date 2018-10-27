create sequence seq_follow_keyword_id;

create table follow_keyword(
  id int not null default nextval('seq_follow_keyword_id') primary key,
  user_id int not null unique,
  keyword varchar(255) not null
);