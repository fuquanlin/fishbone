package cn.fql.account.model.domain;

import javax.persistence.*;

@Table(name = "acct_type")
public class AcctType {
    @Id
    @Column(name = "type_no")
    private String typeNo;

    @Column(name = "type_name")
    private String typeName;

    private String currency;

    /**
     * @return type_no
     */
    public String getTypeNo() {
        return typeNo;
    }

    /**
     * @param typeNo
     */
    public void setTypeNo(String typeNo) {
        this.typeNo = typeNo == null ? null : typeNo.trim();
    }

    /**
     * @return type_name
     */
    public String getTypeName() {
        return typeName;
    }

    /**
     * @param typeName
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
    }

    /**
     * @return currency
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * @param currency
     */
    public void setCurrency(String currency) {
        this.currency = currency == null ? null : currency.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", typeNo=").append(typeNo);
        sb.append(", typeName=").append(typeName);
        sb.append(", currency=").append(currency);
        sb.append("]");
        return sb.toString();
    }
}