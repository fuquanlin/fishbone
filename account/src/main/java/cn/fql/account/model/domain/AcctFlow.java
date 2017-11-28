package cn.fql.account.model.domain;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "acct_flow")
public class AcctFlow {
    @Id
    @Column(name = "flow_no")
    private String flowNo;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "tran_no")
    private String tranNo;

    private String remark;

    @Column(name = "external_id")
    private String externalId;

    private BigDecimal balance;

    private BigDecimal freeze;

    private BigDecimal unfreeze;

    private Integer status;

    /**
     * @return flow_no
     */
    public String getFlowNo() {
        return flowNo;
    }

    /**
     * @param flowNo
     */
    public void setFlowNo(String flowNo) {
        this.flowNo = flowNo == null ? null : flowNo.trim();
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
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * @return tran_no
     */
    public String getTranNo() {
        return tranNo;
    }

    /**
     * @param tranNo
     */
    public void setTranNo(String tranNo) {
        this.tranNo = tranNo == null ? null : tranNo.trim();
    }

    /**
     * @return remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * @return external_id
     */
    public String getExternalId() {
        return externalId;
    }

    /**
     * @param externalId
     */
    public void setExternalId(String externalId) {
        this.externalId = externalId == null ? null : externalId.trim();
    }

    /**
     * @return balance
     */
    public BigDecimal getBalance() {
        return balance;
    }

    /**
     * @param balance
     */
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    /**
     * @return freeze
     */
    public BigDecimal getFreeze() {
        return freeze;
    }

    /**
     * @param freeze
     */
    public void setFreeze(BigDecimal freeze) {
        this.freeze = freeze;
    }

    /**
     * @return unfreeze
     */
    public BigDecimal getUnfreeze() {
        return unfreeze;
    }

    /**
     * @param unfreeze
     */
    public void setUnfreeze(BigDecimal unfreeze) {
        this.unfreeze = unfreeze;
    }

    /**
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", flowNo=").append(flowNo);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", tranNo=").append(tranNo);
        sb.append(", remark=").append(remark);
        sb.append(", externalId=").append(externalId);
        sb.append(", balance=").append(balance);
        sb.append(", freeze=").append(freeze);
        sb.append(", unfreeze=").append(unfreeze);
        sb.append(", status=").append(status);
        sb.append("]");
        return sb.toString();
    }
}