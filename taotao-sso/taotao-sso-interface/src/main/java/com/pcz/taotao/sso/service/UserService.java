package com.pcz.taotao.sso.service;

import com.pcz.taotao.common.pojo.TaotaoResult;
import com.pcz.taotao.pojo.TbUser;
import org.apache.commons.lang3.StringUtils;

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

    /**
     * 用户登录
     *
     * @param username 用户名
     * @param password 密码
     * @return TaotaoResult
     */
    TaotaoResult login(String username, String password);

    /**
     * 根据token获取用户信息
     *
     * @param token token
     * @return TaotaoResult
     */
    TaotaoResult getUserByToken(String token);

    /**
     * 登出
     *
     * @param token token
     * @return TaotaoResult
     */
    TaotaoResult logout(String token);
}
