package com.itheima.mp.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.mp.domain.po.User;
import com.itheima.mp.mapper.UserMapper;
import com.itheima.mp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * @description:
 * @author: VICooL
 * @create: 2024-01-11 09:10
 **/

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void deleteUserById(Integer id) {
        if (Objects.nonNull(id)) {
            baseMapper.delete(Wrappers.lambdaQuery(User.class).eq(User::getId, id));
        }
    }

    @Override
    public void deleteUserLstByIds(Collection<Integer> ids) {
        if (ids.isEmpty()) {
            return;
        }
        baseMapper.delete(Wrappers.lambdaQuery(User.class).in(User::getId, ids));
    }

    @Override
    public void addGroupTagRelDefLst(List<User> userLst) {
        if (!userLst.isEmpty()) {
            super.saveOrUpdateBatch(userLst);
        }
    }
}
