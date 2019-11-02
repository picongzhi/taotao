package com.pcz.taotao.common.pojo;

import java.io.Serializable;

/**
 * @author picongzhi
 */
public class EasyUiTreeNode implements Serializable {
    private static final long serialVersionUID = -2225796961559984167L;

    private long id;
    private String text;
    private String state;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
