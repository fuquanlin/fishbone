package cn.fql.account.service;

import cn.fql.account.model.domain.AcctFlow;

/**
 * Created by fuquanlin on 30/11/2017.
 */
public interface AccountFlowService {

    String createFlow(AcctFlow acctFlow);

    void updateFlow(AcctFlow acctFlow);
}
