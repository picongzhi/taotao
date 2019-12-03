package com.pcz.taotao.sso.service;

import com.pcz.taotao.common.pojo.TaotaoResult;
import com.pcz.taotao.pojo.TbUser;

/**
 * @author picongzhi
 */
public interface UserService {
    /**
     * 验证用户是否合法
     *
     * @param data 用户数据
     * @param type 1: 用户名; 2: 电话; 3: 邮箱
     * @return TaotaoResult
     */
    TaotaoResult checkUserValid(String data, int type);

    /**
     * 用户注册
     *
     * @param tbUser 用户数据
     * @return TaotaoResult
     */
    TaotaoResult register(TbUser tbUser);
}
