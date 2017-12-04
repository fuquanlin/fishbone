package cn.fql.settle.service;

import cn.fql.settle.model.domain.AcctCore;
import cn.fql.settle.model.domain.AcctFlow;
import cn.fql.settle.model.domain.SettleMonthReport;

import java.util.Date;
import java.util.List;

/**
 * Created by fuquanlin on 04/12/2017.
 */
public interface SettleService {


    List<AcctCore> queryAcctCoreWithPagination(int page, int size);

    List<AcctFlow> queryAcctFlow(String acctNo, Date startTime, Date endTime);

    void batchInsertSettleMonthReport(List<SettleMonthReport> settleMonthReports);


    void generateMonthReport(int year, int month);


}
