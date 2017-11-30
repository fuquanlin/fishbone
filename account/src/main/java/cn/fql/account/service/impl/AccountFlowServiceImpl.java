package cn.fql.account.service.impl;

import cn.fql.account.dao.mapper.AcctFlowMapper;
import cn.fql.account.model.domain.AcctFlow;
import cn.fql.account.service.AccountFlowService;
import cn.fql.account.web.feign.IdGenClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;

/**
 * Created by fuquanlin on 30/11/2017.
 */
@Service
public class AccountFlowServiceImpl implements AccountFlowService {

    @Autowired
    private AcctFlowMapper acctFlowMapper;

    @Autowired
    private IdGenClient idGenClient;

    @Transactional(propagation = REQUIRES_NEW)
    @Override
    public String createFlow(AcctFlow acctFlow) {
        //:todo id gen
        String flowNo = idGenClient.getId();
        //acctFlowMapper.insert(acctFlow);
        return flowNo;
    }

    @Transactional
    @Override
    public void updateFlow(AcctFlow acctFlow) {
        acctFlowMapper.updateByPrimaryKey(acctFlow);
    }
}
