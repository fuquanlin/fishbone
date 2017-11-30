package cn.fql.account.service.impl;

import cn.fql.account.dao.mapper.AcctCoreMapper;
import cn.fql.account.model.domain.AcctCore;
import cn.fql.account.service.AccountQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by fuquanlin on 28/11/2017.
 */
@Service
public class AccountQueryServiceImpl implements AccountQueryService {

    @Autowired
    private AcctCoreMapper acctCoreMapper;

    @Override
    public AcctCore queryAccount(String acctNo) {
        AcctCore acctCore = acctCoreMapper.selectByPrimaryKey(acctNo);
        return acctCore;
    }

    @Override
    public void queryFlow() {

    }
}
