package cn.fql.settle.batch;

import cn.fql.settle.dao.mapper.SettleMonthReportMapper;
import cn.fql.settle.model.domain.SettleMonthReport;
import cn.fql.settle.service.SettleService;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by fuquanlin on 01/12/2017.
 */
public class SettleMonthReportWriter implements ItemWriter {

    @Autowired
    private SettleService settleService;


    @Override
    public void write(List items) throws Exception {
        settleService.batchInsertSettleMonthReport(items);
    }
}
