package cn.fql.settle.batch;

import cn.fql.settle.model.domain.AcctCore;
import org.springframework.batch.item.database.AbstractPagingItemReader;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by fuquanlin on 01/12/2017.
 */
public class AcctCoreReader extends AbstractPagingItemReader {

    private int fetchSize;

    //private


    public void setFetchSize(int fetchSize) {
        this.fetchSize = fetchSize;
    }

    @Override
    protected void doReadPage() {
        if (results == null) {
            results = new CopyOnWriteArrayList<AcctCore>();
        }
        else {
            results.clear();
        }

        //results.addAll(helper.readPage(getPage(), getPageSize(), fetchSize, parameterValues));
    }

    @Override
    protected void doJumpToPage(int itemIndex) {

    }
}
