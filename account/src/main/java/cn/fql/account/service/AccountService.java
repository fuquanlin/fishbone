package cn.fql.account.service;

import cn.fql.account.model.dto.OpenAccountDTO;
import cn.fql.account.model.dto.TransferDTO;

/**
 * Created by fuquanlin on 28/11/2017.
 */
public interface AccountService {

    void open(OpenAccountDTO openAccountDTO);

    void transfer(TransferDTO transferDTO);

    void freeze();

    void unfreeze();

    void unfreezeAndTransfer();

}
