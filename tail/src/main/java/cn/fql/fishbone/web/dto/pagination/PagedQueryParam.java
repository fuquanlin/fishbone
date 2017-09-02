package cn.fql.fishbone.web.dto.pagination;

import cn.fql.fishbone.model.domain.common.Base;

/**
 * Created by fuquanlin on 2016/10/3.
 */
public class PagedQueryParam extends Base {

    /**
     * 分布页码，默认值为1
     */
    private int pageIndex = 1;
    /**
     * 每页的条数，默认值为10
     */
    private int pageCount = 10;

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getPageOffset() {
        return (pageIndex - 1) * pageCount;
    }
}
