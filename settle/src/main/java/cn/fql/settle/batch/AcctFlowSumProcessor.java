package cn.fql.settle.batch;

import cn.fql.settle.constants.FlowDirectionEnum;
import cn.fql.settle.model.domain.AcctCore;
import cn.fql.settle.model.domain.AcctFlow;
import cn.fql.settle.model.domain.SettleMonthReport;
import cn.fql.settle.service.SettleService;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created by fuquanlin on 01/12/2017.
 */
public class AcctFlowSumProcessor implements ItemProcessor{

    @Autowired
    private SettleService settleService;


    @Override
    public Object process(Object item) throws Exception {
        AcctCore acctCore = (AcctCore) item;
        //:todo use date
        List<AcctFlow> acctFlows = settleService.queryAcctFlow(acctCore.getAcctNo(),null,null);
        Optional<BigDecimal> resultIncome = acctFlows.stream().filter(a->(a.getDirection().equals(FlowDirectionEnum.IN.getCode())&&acctCore.getAcctNo().equals(a.getAcctNo()))||
                (a.getDirection().equals(FlowDirectionEnum.OUT.getCode()) &&acctCore.getAcctNo().equals(a.getAcctNoDest()))).map(AcctFlow::getBalance)
                .reduce(BigDecimal::add);
        Optional<BigDecimal> resultOutcome = acctFlows.stream().filter(a->(a.getDirection().equals(FlowDirectionEnum.OUT.getCode())&&acctCore.getAcctNo().equals(a.getAcctNo()))||
                (a.getDirection().equals(FlowDirectionEnum.IN.getCode()) &&acctCore.getAcctNo().equals(a.getAcctNoDest()))).map(AcctFlow::getBalance)
                .reduce(BigDecimal::add);
        SettleMonthReport settleMonthReport = new SettleMonthReport();
        settleMonthReport.setAcctNo(acctCore.getAcctNo());
        settleMonthReport.setCreateTime(new Date());
        settleMonthReport.setReportDate("ALL");
        if(resultIncome.isPresent()) {
            settleMonthReport.setIncome(resultIncome.get());
        }else{
            settleMonthReport.setIncome(BigDecimal.ZERO);
        }
        if(resultOutcome.isPresent()) {
            settleMonthReport.setOutcome(resultOutcome.get());
        }else{
            settleMonthReport.setOutcome(BigDecimal.ZERO);
        }
        //:todo id gen
        settleMonthReport.setReportNo(acctCore.getAcctNo());
        return settleMonthReport;
    }

}
