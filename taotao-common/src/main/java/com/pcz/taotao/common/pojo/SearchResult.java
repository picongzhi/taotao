package com.pcz.taotao.common.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * @author picongzhi
 */
public class SearchResult implements Serializable {
    private static final long serialVersionUID = -6554968076834667325L;

    private int totalPage;
    private List<SearchItem> itemList;

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<SearchItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<SearchItem> itemList) {
        this.itemList = itemList;
    }
}
