package com.pcz.taotao.item.pojo;

import com.pcz.taotao.pojo.TbItem;
import org.apache.commons.lang3.StringUtils;

/**
 * @author picongzhi
 */
public class Item extends TbItem {
    public Item(TbItem tbItem) {
        setId(tbItem.getId());
        setTitle(tbItem.getTitle());
        setSellPoint(tbItem.getSellPoint());
        setPrice(tbItem.getPrice());
        setNum(tbItem.getNum());
        setBarcode(tbItem.getBarcode());
        setImage(tbItem.getImage());
        setCid(tbItem.getCid());
        setStatus(tbItem.getStatus());
        setCreated(tbItem.getCreated());
        setUpdated(tbItem.getUpdated());
    }

    public String[] getImages() {
        if (StringUtils.isNotBlank(getImage())) {
            return getImage().split(",");
        }

        return null;
    }
}
