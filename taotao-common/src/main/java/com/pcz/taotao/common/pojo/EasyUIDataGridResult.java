package com.pcz.taotao.common.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * @author picongzhi
 */
public class EasyUIDataGridResult implements Serializable {
    private static final long serialVersionUID = 8849923457763146323L;

    private long total;
    private List rows;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }
}
