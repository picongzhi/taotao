package com.pcz.taotao.sso.service.impl;

import com.pcz.taotao.common.pojo.TaotaoResult;
import com.pcz.taotao.common.utils.JsonUtils;
import com.pcz.taotao.jedis.JedisClient;
import com.pcz.taotao.mapper.TbUserMapper;
import com.pcz.taotao.pojo.TbUser;
import com.pcz.taotao.pojo.TbUserExample;
import com.pcz.taotao.sso.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author picongzhi
 */
@Service
public class UserServiceImpl implements UserService {
    @Value("${USER_SESSION}")
    private String USER_SESSION;
    @Value("${SESSION_EXPIRE}")
    private Integer SESSION_EXPIRE;

    @Autowired
    private TbUserMapper tbUserMapper;
    @Autowired
    private JedisClient jedisClient;

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

    @Override
    public TaotaoResult register(TbUser tbUser) {
        if (StringUtils.isBlank(tbUser.getUsername())) {
            return TaotaoResult.build(400, "用户名不能为空");
        }

        TaotaoResult taotaoResult = checkUserValid(tbUser.getUsername(), 1);
        if (!(boolean) taotaoResult.getData()) {
            return TaotaoResult.build(400, "用户名重复");
        }

        if (StringUtils.isBlank(tbUser.getPassword())) {
            return TaotaoResult.build(400, "密码不能为空");
        }

        if (StringUtils.isNotBlank(tbUser.getPhone())) {
            taotaoResult = checkUserValid(tbUser.getPhone(), 2);
            if (!(boolean) taotaoResult.getData()) {
                return TaotaoResult.build(400, "手机号码重复");
            }
        }

        if (StringUtils.isNotBlank(tbUser.getEmail())) {
            taotaoResult = checkUserValid(tbUser.getEmail(), 3);
            if (!(boolean) taotaoResult.getData()) {
                return TaotaoResult.build(400, "邮箱重复");
            }
        }

        tbUser.setCreated(new Date());
        tbUser.setUpdated(new Date());
        tbUser.setPassword(DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes()));

        tbUserMapper.insert(tbUser);

        return TaotaoResult.ok();
    }

    @Override
    public TaotaoResult login(String username, String password) {
        TbUserExample tbUserExample = new TbUserExample();
        tbUserExample.createCriteria().andUsernameEqualTo(username);

        List<TbUser> tbUserList = tbUserMapper.selectByExample(tbUserExample);
        if (tbUserList == null || tbUserList.size() == 0) {
            return TaotaoResult.build(400, "用户名或密码不正确");
        }

        TbUser tbUser = tbUserList.get(0);
        if (!DigestUtils.md5DigestAsHex(password.getBytes()).equals(tbUser.getPassword())) {
            return TaotaoResult.build(400, "用户名或密码不正确");
        }

        tbUser.setPassword(null);
        String token = UUID.randomUUID().toString();
        String key = USER_SESSION + ":" + token;
        jedisClient.set(key, JsonUtils.objectToJson(tbUser));
        jedisClient.expire(key, SESSION_EXPIRE);

        return TaotaoResult.ok(token);
    }
}
