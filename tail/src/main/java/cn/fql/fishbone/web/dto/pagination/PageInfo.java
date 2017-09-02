package cn.fql.fishbone.web.dto.pagination;

/**
 * Created by fuquanlin on 2016/9/27.
 */
public class PageInfo {

    /**
     * 页码
     */
    public int pageIndex = 1;

    /**
     * 每页数据量
     */
    public int pageCount = 20;

    public PageInfo(int pageIndex, int pageCount) {
        this.pageIndex = pageIndex;
        this.pageCount = pageCount;
    }

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
}
