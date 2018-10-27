-- create sequence seq_user_id;

create table sns_user(
  id int not null default nextval('seq_user_id') primary key,
	sns_user_id varchar(255) unique,
	display_name varchar(255),
	access_token varchar(512) not null,
	secret varchar(512) not null
);