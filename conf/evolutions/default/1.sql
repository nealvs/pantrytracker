# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table account (
  account_id                bigint auto_increment not null,
  name                      varchar(255),
  constraint pk_account primary key (account_id))
;

create table item_category (
  item_category_id          bigint auto_increment not null,
  name                      varchar(255),
  created                   datetime,
  deleted                   datetime,
  account_id                bigint,
  constraint pk_item_category primary key (item_category_id))
;

create table item_price (
  item_price_id             bigint auto_increment not null,
  created                   datetime,
  account_id                bigint,
  constraint pk_item_price primary key (item_price_id))
;

create table users (
  user_id                   bigint auto_increment not null,
  email                     varchar(255),
  name                      varchar(255),
  salt                      varchar(255),
  password                  varchar(255),
  first_name                varchar(255),
  last_name                 varchar(255),
  created                   datetime,
  deleted                   datetime,
  last_login                datetime,
  constraint pk_users primary key (user_id))
;


create table account_user (
  account_account_id             bigint not null,
  users_user_id                  bigint not null,
  constraint pk_account_user primary key (account_account_id, users_user_id))
;
alter table item_category add constraint fk_item_category_account_1 foreign key (account_id) references account (account_id) on delete restrict on update restrict;
create index ix_item_category_account_1 on item_category (account_id);
alter table item_price add constraint fk_item_price_account_2 foreign key (account_id) references account (account_id) on delete restrict on update restrict;
create index ix_item_price_account_2 on item_price (account_id);



alter table account_user add constraint fk_account_user_account_01 foreign key (account_account_id) references account (account_id) on delete restrict on update restrict;

alter table account_user add constraint fk_account_user_users_02 foreign key (users_user_id) references users (user_id) on delete restrict on update restrict;

# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table account;

drop table account_user;

drop table item_category;

drop table item_price;

drop table users;

SET FOREIGN_KEY_CHECKS=1;

