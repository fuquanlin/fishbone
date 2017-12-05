insert into acct_core(acct_no, create_time, update_time, version, remark, balance, freeze, total, status, type_no, uid, dac) VALUES
  ('1','2017-12-01','2017-12-01',1,'test',0,0,0,1,1,2345,'dac');

insert into acct_core(acct_no, create_time, update_time, version, remark, balance, freeze, total, status, type_no, uid, dac) VALUES
  ('2','2017-12-02','2017-12-02',1,'test1',0,0,0,1,1,1234,'dac');

insert into acct_core(acct_no, create_time, update_time, version, remark, balance, freeze, total, status, type_no, uid, dac) VALUES
  ('3','2017-12-03','2017-12-03',1,'test2',0,0,0,1,1,3456,'dac');

insert into acct_flow(flow_no, create_time, update_time, tran_no, acct_no, acct_no_dest, direction, remark, external_id, balance, freeze, unfreeze, status) VALUES
  ('1','2017-12-04','2017-12-03','1001','1','2',2,'test','test',10,0,0,1);
insert into acct_flow(flow_no, create_time, update_time, tran_no, acct_no, acct_no_dest, direction, remark, external_id, balance, freeze, unfreeze, status) VALUES
  ('2','2017-12-04','2017-12-03','1001','2','1',2,'test','test',10,0,0,1);
insert into acct_flow(flow_no, create_time, update_time, tran_no, acct_no, acct_no_dest, direction, remark, external_id, balance, freeze, unfreeze, status) VALUES
  ('3','2017-12-04','2017-12-03','1001','3','2',2,'test','test',10,0,0,1);
insert into acct_flow(flow_no, create_time, update_time, tran_no, acct_no, acct_no_dest, direction, remark, external_id, balance, freeze, unfreeze, status) VALUES
  ('4','2017-12-04','2017-12-03','1001','1','2',1,'test','test',10,0,0,1);
insert into acct_flow(flow_no, create_time, update_time, tran_no, acct_no, acct_no_dest, direction, remark, external_id, balance, freeze, unfreeze, status) VALUES
  ('5','2017-12-04','2017-12-03','1001','1','3',1,'test','test',10,0,0,1);
insert into acct_flow(flow_no, create_time, update_time, tran_no, acct_no, acct_no_dest, direction, remark, external_id, balance, freeze, unfreeze, status) VALUES
  ('6','2017-12-04','2017-12-03','1001','1','3',2,'test','test',10,0,0,1);
insert into acct_flow(flow_no, create_time, update_time, tran_no, acct_no, acct_no_dest, direction, remark, external_id, balance, freeze, unfreeze, status) VALUES
  ('7','2017-12-04','2017-12-03','1001','3','2',2,'test','test',10,0,0,1);
insert into acct_flow(flow_no, create_time, update_time, tran_no, acct_no, acct_no_dest, direction, remark, external_id, balance, freeze, unfreeze, status) VALUES
  ('8','2017-12-04','2017-12-03','1001','3','1',2,'test','test',10,0,0,1);
insert into acct_flow(flow_no, create_time, update_time, tran_no, acct_no, acct_no_dest, direction, remark, external_id, balance, freeze, unfreeze, status) VALUES
  ('9','2017-12-04','2017-12-03','1001','1','2',2,'test','test',10,0,0,1);
