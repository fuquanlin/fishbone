package cn.fql.account.model.domain;

import javax.persistence.*;

@Table(name = "acct_transaction")
public class AcctTransaction {
    @Id
    @Column(name = "tran_no")
    private String tranNo;

    @Column(name = "tran_name")
    private String tranName;

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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", tranNo=").append(tranNo);
        sb.append(", tranName=").append(tranName);
        sb.append("]");
        return sb.toString();
    }
}