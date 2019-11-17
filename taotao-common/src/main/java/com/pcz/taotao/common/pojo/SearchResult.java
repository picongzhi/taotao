package com.pcz.taotao.common.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * @author picongzhi
 */
public class SearchResult implements Serializable {
    private static final long serialVersionUID = -6554968076834667325L;

    private int totalPages;
    private long recordCount;
    private List<SearchItem> itemList;

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public long getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(long recordCount) {
        this.recordCount = recordCount;
    }

    public List<SearchItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<SearchItem> itemList) {
        this.itemList = itemList;
    }
}
