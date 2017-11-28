package cn.fql.account.model.domain;

import javax.persistence.*;

@Table(name = "acct_transaction")
public class AcctTransaction {
    @Id
    @Column(name = "tran_no")
    private String tranNo;

    @Column(name = "tran_name")
    private String tranName;

    @Column(name = "acct_no")
    private String acctNo;

    @Column(name = "acct_no_dest")
    private String acctNoDest;

    private Integer direction;

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
     * @return tran_name
     */
    public String getTranName() {
        return tranName;
    }

    /**
     * @param tranName
     */
    public void setTranName(String tranName) {
        this.tranName = tranName == null ? null : tranName.trim();
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
     * @return acct_no_dest
     */
    public String getAcctNoDest() {
        return acctNoDest;
    }

    /**
     * @param acctNoDest
     */
    public void setAcctNoDest(String acctNoDest) {
        this.acctNoDest = acctNoDest == null ? null : acctNoDest.trim();
    }

    /**
     * @return direction
     */
    public Integer getDirection() {
        return direction;
    }

    /**
     * @param direction
     */
    public void setDirection(Integer direction) {
        this.direction = direction;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", tranNo=").append(tranNo);
        sb.append(", tranName=").append(tranName);
        sb.append(", acctNo=").append(acctNo);
        sb.append(", acctNoDest=").append(acctNoDest);
        sb.append(", direction=").append(direction);
        sb.append("]");
        return sb.toString();
    }
}