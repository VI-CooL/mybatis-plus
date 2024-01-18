package com.itheima.mp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.mp.domain.po.User;

import java.util.Collection;
import java.util.List;

public interface UserService extends IService<User> {
    void deleteUserById(Integer id);

    void deleteUserLstByIds(Collection<Integer> ids);

    void saveOrUpdateUserLst(List<User> userLst);

    void updateUser(User user);

    void deleteTest(List<Integer> ids);

    List<User> testSelect(List<Integer> ids);
}
