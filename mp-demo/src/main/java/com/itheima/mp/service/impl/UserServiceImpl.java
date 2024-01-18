package com.itheima.mp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.mp.domain.po.User;
import com.itheima.mp.mapper.UserMapper;
import com.itheima.mp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;
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
    public void saveOrUpdateUserLst(List<User> userLst) {
        if (!userLst.isEmpty()) {
            super.saveOrUpdateBatch(userLst);
        }
    }

    @Override
    public void updateUser(User user) {
        User updateUser = new User();
        updateUser.setId(user.getId());
        updateUser.setBalance(user.getBalance());
        userMapper.updateById(updateUser);
    }

    @Override
    @Transactional
    public void deleteTest(List<Integer> ids) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<User>()
                .in(User::getId,ids);
        // userMapper 可以实现删除  对应sql语句 UPDATE tb_user SET is_deleted=1 WHERE is_deleted=0 AND (id IN (?,?,?))
        // userMapper.delete(wrapper);

        // userMapper 也可以实现删除  对应sql语句 UPDATE tb_user SET is_deleted=1 WHERE is_deleted=0 AND (id IN (?,?,?))
        baseMapper.delete(wrapper);
    }

    @Override
    public List<User> testSelect(List<Integer> ids) {
        if(ids.isEmpty()){
            return Collections.emptyList();
        }
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<User>()
                .in(User::getId,ids);

        return userMapper.selectList(wrapper);
    }
}
