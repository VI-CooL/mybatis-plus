package com.itheima.mp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.mp.domain.po.User;

import java.util.Collection;
import java.util.List;

public interface UserService extends IService<User> {
    void deleteUserById(Integer id);

    void deleteUserLstByIds(Collection<Integer> ids);

    void addGroupTagRelDefLst(List<User> userLst);
}
