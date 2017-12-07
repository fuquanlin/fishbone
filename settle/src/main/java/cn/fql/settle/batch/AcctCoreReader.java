package cn.fql.settle.batch;

import cn.fql.settle.model.domain.AcctCore;
import cn.fql.settle.service.SettleService;
import org.springframework.batch.item.database.AbstractPagingItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by fuquanlin on 01/12/2017.
 */
public class AcctCoreReader extends AbstractPagingItemReader {


    @Autowired
    private SettleService settleService;


    @Override
    protected void doReadPage() {
        if (results == null) {
            results = new CopyOnWriteArrayList<AcctCore>();
        } else {
            results.clear();
        }

        results.addAll(settleService.queryAcctCoreWithPagination(getPage()+1,getPageSize()));
    }

    @Override
    protected void doJumpToPage(int itemIndex) {

    }
}
