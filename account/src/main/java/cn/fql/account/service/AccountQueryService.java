package cn.fql.account.service;

import cn.fql.account.model.domain.AcctCore;

/**
 * Created by fuquanlin on 28/11/2017.
 */
public interface AccountQueryService {

    AcctCore queryAccount(String acctNo);

    void queryFlow();
}
