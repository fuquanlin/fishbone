/*账户*/
create table acct_core (
  acct_no VARCHAR(50) not null,
  create_time datetime not null,
  update_time datetime not null,
  version INT(11) not null,
  remark VARCHAR(255),
  balance decimal(20,6) not null,
  freeze decimal(20,6) not null,
  total decimal(20,6) not null,
  status INT(4) not NULL,
  type_no INT(11) not null,
  uid BIGINT(20) not null,
  dac VARCHAR(64) not null,
  PRIMARY KEY (acct_no),
  UNIQUE KEY(type_no,uid)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*流水*/
create table acct_flow (
  flow_no VARCHAR(50) not null,
  create_time datetime not null,
  update_time datetime not null,
  tran_no VARCHAR(50) not null,
  acct_no VARCHAR(50) not null,
  acct_no_dest VARCHAR(50) not null,
  direction INT(1) not null,
  remark VARCHAR(255),
  external_id VARCHAR(255),
  balance decimal(20,6) not null,
  freeze decimal(20,6) not null,
  unfreeze decimal(20,6) not null,
  status INT(4) not NULL,
  PRIMARY KEY (flow_no)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*类型*/
create table acct_type (
  type_no VARCHAR(50) not null,
  type_name VARCHAR(100) not null,
  currency VARCHAR(10) not null,
  PRIMARY KEY (type_no)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*分录常量*/
create table acct_transaction (
  tran_no VARCHAR(50) not null,
  tran_name VARCHAR(100) not null,
  PRIMARY KEY (tran_no)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into acct_type(type_no, type_name, currency) VALUES ('1','资金','rmb');
insert into acct_type(type_no, type_name, currency) VALUES ('2','资金','ffb');

