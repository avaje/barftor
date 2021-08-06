-- apply changes
create table error_env (
  id                            integer not null,
  error_id                      integer not null,
  app                           varchar(30),
  env                           varchar(30),
  line                          varchar(400),
  when_created                  timestamp not null,
  version                       integer not null,
  constraint pk_error_env primary key (id),
  foreign key (error_id) references error_stack (id) on delete restrict on update restrict
);

create table error_instance (
  id                            integer not null,
  error_id                      integer not null,
  app                           varchar(30) not null,
  env                           varchar(30) not null,
  line                          varchar(400) not null,
  when_created                  timestamp not null,
  version                       integer not null,
  constraint pk_error_instance primary key (id),
  foreign key (error_id) references error_stack (id) on delete restrict on update restrict
);

create table error_stack (
  id                            integer not null,
  hash                          varchar(50) not null,
  match_line                    varchar(400) not null,
  stack_lines                   clob,
  when_created                  timestamp not null,
  version                       integer not null,
  constraint pk_error_stack primary key (id)
);

