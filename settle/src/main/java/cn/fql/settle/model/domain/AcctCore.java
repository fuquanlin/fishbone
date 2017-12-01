package cn.fql.settle.model.domain;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "acct_core")
public class AcctCore {
    @Id
    @Column(name = "acct_no")
    private String acctNo;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    private Integer version;

    private String remark;

    private BigDecimal balance;

    private BigDecimal freeze;

    private BigDecimal total;

    private Integer status;

    @Column(name = "type_no")
    private Integer typeNo;

    private Long uid;

    private String dac;

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
     * @return version
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * @param version
     */
    public void setVersion(Integer version) {
        this.version = version;
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
     * @return total
     */
    public BigDecimal getTotal() {
        return total;
    }

    /**
     * @param total
     */
    public void setTotal(BigDecimal total) {
        this.total = total;
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

    /**
     * @return type_no
     */
    public Integer getTypeNo() {
        return typeNo;
    }

    /**
     * @param typeNo
     */
    public void setTypeNo(Integer typeNo) {
        this.typeNo = typeNo;
    }

    /**
     * @return uid
     */
    public Long getUid() {
        return uid;
    }

    /**
     * @param uid
     */
    public void setUid(Long uid) {
        this.uid = uid;
    }

    /**
     * @return dac
     */
    public String getDac() {
        return dac;
    }

    /**
     * @param dac
     */
    public void setDac(String dac) {
        this.dac = dac == null ? null : dac.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", acctNo=").append(acctNo);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", version=").append(version);
        sb.append(", remark=").append(remark);
        sb.append(", balance=").append(balance);
        sb.append(", freeze=").append(freeze);
        sb.append(", total=").append(total);
        sb.append(", status=").append(status);
        sb.append(", typeNo=").append(typeNo);
        sb.append(", uid=").append(uid);
        sb.append(", dac=").append(dac);
        sb.append("]");
        return sb.toString();
    }
}