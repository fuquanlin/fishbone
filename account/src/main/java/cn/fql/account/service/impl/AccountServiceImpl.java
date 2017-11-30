package cn.fql.account.service.impl;

import cn.fql.account.dao.mapper.AcctCoreMapper;
import cn.fql.account.dao.mapper.AcctFlowMapper;
import cn.fql.account.model.domain.AcctCore;
import cn.fql.account.model.domain.AcctFlow;
import cn.fql.account.model.dto.OpenAccountDTO;
import cn.fql.account.model.dto.TransferDTO;
import cn.fql.account.service.AccountFlowService;
import cn.fql.account.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

/**
 * Created by fuquanlin on 28/11/2017.
 */
@Slf4j
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AcctCoreMapper acctCoreMapper;

    @Autowired
    private AccountFlowService accountFlowService;


    @Transactional
    @Override
    public void open(OpenAccountDTO openAccountDTO) {
        log.info("account service open->{}",openAccountDTO);
        AcctFlow acctFlow = new AcctFlow();
        //acctFlow.set
        accountFlowService.createFlow(acctFlow);
        AcctCore acctCore = new AcctCore();

        acctCoreMapper.insert(acctCore);
        //acctFlow.setStatus();
        accountFlowService.updateFlow(acctFlow);


    }

    @Transactional
    @Override
    public void transfer(TransferDTO transferDTO) {
        AcctFlow acctFlow = new AcctFlow();
        //acctFlow.set
        accountFlowService.createFlow(acctFlow);
        AcctCore acctCore1 = new AcctCore();

        Example example = new Example(AcctCore.class);

        acctCoreMapper.updateByExample(acctCore1,example);

        AcctCore acctCore2 = new AcctCore();

        example = new Example(AcctCore.class);

        acctCoreMapper.updateByExample(acctCore2,example);

        //acctFlow.setStatus();
        accountFlowService.updateFlow(acctFlow);
    }

    @Override
    public void freeze() {

    }

    @Override
    public void unfreeze() {

    }

    @Override
    public void unfreezeAndTransfer() {

    }
}
