-- apply changes
create table error_env (
  id                            bigint auto_increment not null,
  error_id                      bigint not null,
  app                           varchar(30),
  env                           varchar(30),
  line                          varchar(400),
  when_created                  datetime(6) not null,
  version                       bigint not null,
  constraint pk_error_env primary key (id)
);

create table error_instance (
  id                            bigint auto_increment not null,
  error_id                      bigint not null,
  app                           varchar(30) not null,
  env                           varchar(30) not null,
  line                          varchar(400) not null,
  when_created                  datetime(6) not null,
  version                       bigint not null,
  constraint pk_error_instance primary key (id)
);

create table error_stack (
  id                            bigint auto_increment not null,
  hash                          varchar(50) not null,
  match_line                    varchar(400) not null,
  stack_lines                   longtext,
  when_created                  datetime(6) not null,
  version                       bigint not null,
  constraint pk_error_stack primary key (id)
);

create index ix_error_env_error_id on error_env (error_id);
alter table error_env add constraint fk_error_env_error_id foreign key (error_id) references error_stack (id) on delete restrict on update restrict;

create index ix_error_instance_error_id on error_instance (error_id);
alter table error_instance add constraint fk_error_instance_error_id foreign key (error_id) references error_stack (id) on delete restrict on update restrict;

