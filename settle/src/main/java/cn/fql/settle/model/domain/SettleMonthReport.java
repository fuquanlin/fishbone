package cn.fql.settle.model.domain;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "settle_month_report")
public class SettleMonthReport {
    @Id
    @Column(name = "report_no")
    private String reportNo;

    @Column(name = "acct_no")
    private String acctNo;

    @Column(name = "report_date")
    private String reportDate;

    @Column(name = "create_time")
    private Date createTime;

    private BigDecimal income;

    private BigDecimal outcome;

    /**
     * @return report_no
     */
    public String getReportNo() {
        return reportNo;
    }

    /**
     * @param reportNo
     */
    public void setReportNo(String reportNo) {
        this.reportNo = reportNo == null ? null : reportNo.trim();
    }

    /**
     * @return acct_no
     */
    public String getAcctNo() {
        return acctNo;
    }

    /**
     * @param acctNo
     */
    public void setAcctNo(String acctNo) {
        this.acctNo = acctNo == null ? null : acctNo.trim();
    }

    /**
     * @return report_date
     */
    public String getReportDate() {
        return reportDate;
    }

    /**
     * @param reportDate
     */
    public void setReportDate(String reportDate) {
        this.reportDate = reportDate == null ? null : reportDate.trim();
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return income
     */
    public BigDecimal getIncome() {
        return income;
    }

    /**
     * @param income
     */
    public void setIncome(BigDecimal income) {
        this.income = income;
    }

    /**
     * @return outcome
     */
    public BigDecimal getOutcome() {
        return outcome;
    }

    /**
     * @param outcome
     */
    public void setOutcome(BigDecimal outcome) {
        this.outcome = outcome;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", reportNo=").append(reportNo);
        sb.append(", acctNo=").append(acctNo);
        sb.append(", reportDate=").append(reportDate);
        sb.append(", createTime=").append(createTime);
        sb.append(", income=").append(income);
        sb.append(", outcome=").append(outcome);
        sb.append("]");
        return sb.toString();
    }
}