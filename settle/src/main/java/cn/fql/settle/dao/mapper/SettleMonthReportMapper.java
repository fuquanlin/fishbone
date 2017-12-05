package cn.fql.settle.dao.mapper;

import cn.fql.settle.model.domain.SettleMonthReport;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SettleMonthReportMapper extends Mapper<SettleMonthReport> {


    @Insert({
            "<script>",
            "insert into settle_month_report(report_no,acct_no,report_date,create_time,income,outcome)",
            "values",
            "<foreach collection='settleMonthReports' item='r' separator=',' >",
            "(#{r.reportNo}, #{r.acctNo}, #{r.reportDate}, #{r.createTime}, #{r.income}, #{r.outcome})",
            "</foreach>",
            "</script>"
    })
    int batchInsert(@Param("settleMonthReports") List<SettleMonthReport> settleMonthReports);
}