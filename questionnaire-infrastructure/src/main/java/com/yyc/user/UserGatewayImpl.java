package com.yyc.user;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yyc.domain.gateway.UserGateway;
import com.yyc.dto.UserInsertCmd;
import com.yyc.dto.UserQry;
import lombok.NonNull;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户
 *
 * @author yuchengyao
 */
@Component
public class UserGatewayImpl implements UserGateway {

    @Resource
    private UserMapper userMapper;

    @Override
    public void insert(UserInsertCmd userInsertCmd) {
        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(userInsertCmd, userDO);
        userMapper.insert(userDO);
    }

    @Override
    public boolean getByOpenId(String openId) {

        UserQry userQry = new UserQry();

        userQry.setOpenId(openId);

        List<UserDO> userDOS = userMapper.selectList(buildQueryWrapper(userQry));

        return checkReturn(userDOS);

    }

    private Wrapper buildQueryWrapper(@NonNull UserQry userQry) {

        Wrapper wrapper = new QueryWrapper()
                .eq(userQry.getOpenId() != null, "open_id", userQry.getOpenId());

        return wrapper;
    }

    /**
     * 判断用户是否存在
     *
     * @param userDOS
     * @return
     */
    private boolean checkReturn(List<UserDO> userDOS) {
        if (userDOS == null
                || userDOS.isEmpty()) {
            return false;
        }
        return true;
    }
}
