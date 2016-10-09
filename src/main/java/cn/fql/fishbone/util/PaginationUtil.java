package cn.fql.fishbone.util;

import cn.fql.fishbone.web.dto.pagination.PageInfo;
import cn.fql.fishbone.web.dto.pagination.PageResult;
import cn.fql.fishbone.web.dto.pagination.PagedQueryParam;

import java.util.List;

/**
 * Created by fuquanlin on 2016/10/8.
 */
public class PaginationUtil {

    public static PageResult assable(PagedQueryParam param, List rows) {
        PageResult pagedResult = new PageResult();
        pagedResult.setRows(rows);

        PageInfo pageInfo = new PageInfo(param.getPageIndex(), param.getPageCount());
        pagedResult.setPageInfo(pageInfo);
        return pagedResult;
    }
}
