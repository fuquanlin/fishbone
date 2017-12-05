package cn.fql.settle.service.impl;

import cn.fql.settle.dao.mapper.AcctCoreMapper;
import cn.fql.settle.dao.mapper.AcctFlowMapper;
import cn.fql.settle.dao.mapper.SettleMonthReportMapper;
import cn.fql.settle.model.domain.AcctCore;
import cn.fql.settle.model.domain.AcctFlow;
import cn.fql.settle.model.domain.SettleMonthReport;
import cn.fql.settle.service.SettleService;
//import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageHelper;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by fuquanlin on 04/12/2017.
 */
@Service
public class SettleServiceImpl implements SettleService {
    @Autowired
    private SettleMonthReportMapper settleMonthReportMapper;

    @Autowired
    private AcctCoreMapper acctCoreMapper;

    @Autowired
    private AcctFlowMapper acctFlowMapper;

    @Autowired
    private JobLauncher jobLauncher;

    @Resource(name = "generateBill")
    private Job generateBill;


    @Override
    public List<AcctCore> queryAcctCoreWithPagination(int page, int size) {
        PageHelper.startPage(page, size);
        List<AcctCore> acctCores = acctCoreMapper.selectAll();
        return acctCores;
    }

    @Override
    public List<AcctFlow> queryAcctFlow(String acctNo, Date startTime, Date endTime) {
        Example example = new Example(AcctFlow.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("acctNo", acctNo);
        //criteria.andBetween("")
        Example.Criteria or = example.or();
        or.andEqualTo("acctNoDest", acctNo);

        List<AcctFlow> acctFlows = acctFlowMapper.selectByExample(example);
        return acctFlows;
    }

    @Override
    public void batchInsertSettleMonthReport(List<SettleMonthReport> settleMonthReports) {
        settleMonthReportMapper.batchInsert(settleMonthReports);
    }

    Random test = new Random();

    @Override
    public void generateMonthReport(int year, int month) {
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("settleDate",test.nextInt(1000)+"")
                .toJobParameters();

        try {
            JobExecution jobExecution = jobLauncher.run(generateBill, jobParameters);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
