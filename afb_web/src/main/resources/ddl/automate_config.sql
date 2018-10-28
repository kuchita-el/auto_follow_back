--create sequence seq_automate_user_id;

create table automate_user(
  id int not null default nextval('seq_automate_user_id') primary key,
  user_id int not null unique
);