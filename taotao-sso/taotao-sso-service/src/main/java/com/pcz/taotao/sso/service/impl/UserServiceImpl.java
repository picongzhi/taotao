package com.pcz.taotao.sso.service.impl;

import com.pcz.taotao.common.pojo.TaotaoResult;
import com.pcz.taotao.mapper.TbUserMapper;
import com.pcz.taotao.pojo.TbUser;
import com.pcz.taotao.pojo.TbUserExample;
import com.pcz.taotao.sso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author picongzhi
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private TbUserMapper tbUserMapper;

    @Override
    public TaotaoResult checkUserValid(String data, int type) {
        TbUserExample tbUserExample = new TbUserExample();
        if (type == 1) {
            tbUserExample.createCriteria().andUsernameEqualTo(data);
        } else if (type == 2) {
            tbUserExample.createCriteria().andPhoneEqualTo(data);
        } else if (type == 3) {
            tbUserExample.createCriteria().andEmailEqualTo(data);
        } else {
            return TaotaoResult.build(401, "非法请求参数");
        }

        List<TbUser> tbUserList = tbUserMapper.selectByExample(tbUserExample);
        if (tbUserList != null && tbUserList.size() > 0) {
            return TaotaoResult.ok(false);
        }

        return TaotaoResult.ok(true);
    }
}
