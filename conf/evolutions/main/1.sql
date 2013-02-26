# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table account (
  id                        bigint auto_increment not null,
  name                      varchar(255),
  folder                    varchar(255),
  constraint pk_account primary key (id))
;

create table category (
  id                        bigint auto_increment not null,
  title                     varchar(255),
  done                      tinyint(1) default 0,
  due_date                  datetime,
  assigned_to_user_id       integer,
  folder                    varchar(255),
  account_id                bigint,
  constraint pk_category primary key (id))
;

create table users (
  user_id                   integer auto_increment not null,
  email                     varchar(255),
  name                      varchar(255),
  password                  varchar(255),
  constraint pk_users primary key (user_id))
;


create table account_users (
  account_id                     bigint not null,
  users_user_id                  integer not null,
  constraint pk_account_users primary key (account_id, users_user_id))
;
alter table category add constraint fk_category_assignedTo_1 foreign key (assigned_to_user_id) references users (user_id) on delete restrict on update restrict;
create index ix_category_assignedTo_1 on category (assigned_to_user_id);
alter table category add constraint fk_category_account_2 foreign key (account_id) references account (id) on delete restrict on update restrict;
create index ix_category_account_2 on category (account_id);



alter table account_users add constraint fk_account_users_account_01 foreign key (account_id) references account (id) on delete restrict on update restrict;

alter table account_users add constraint fk_account_users_users_02 foreign key (users_user_id) references users (user_id) on delete restrict on update restrict;

# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table account;

drop table account_users;

drop table category;

drop table users;

SET FOREIGN_KEY_CHECKS=1;

