package com.itheima.mp.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.itheima.mp.domain.po.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void testInsert() {
        User user = new User();
//        user.setId(5L);
        user.setUsername("Taka");
        user.setPassword("123");
        user.setPhone("18688990012");
        user.setBalance(200);
        user.setInfo("{\"age\": 24, \"intro\": \"英文老师\", \"gender\": \"female\"}");
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        userMapper.insert(user);
    }

    @Test
    void testSelectById() {
        User user = userMapper.selectById(6L);
        System.out.println("user = " + user);
    }


    @Test
    void testQueryByIds() {
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1L, 2L, 3L, 4L));
        users.forEach(System.out::println);
    }

    @Test
    void testQueryByIds2() {
        List<User> users = userMapper.queryUserByIds(Arrays.asList(1L, 2L, 3L, 4L));
        users.forEach(System.out::println);
    }

    @Test
    void testUpdateById() {
        User user = new User();
        user.setId(5L);
        user.setBalance(20000);
        userMapper.updateById(user);
    }

    @Test
    void testDeleteUser() {
        userMapper.deleteById(5L);
    }

    /***
     * @Description: 查询出名字中带o的，存款大于等于1000元的人的id、username、info、balance字段
     * @Param: []
     * @return: void
     * @Author: VICooL
     * @Date: 2023/12/25
     */
    @Test
    void testQueryWrapper(){
        // 1.构造查询条件
        QueryWrapper<User> wrapper = new QueryWrapper<User>()
                .select("id", "username", "info", "balance")
                .like("username", "o")
                .ge("balance", 1000);
        // 查询
        List<User> users = userMapper.selectList(wrapper);
        users.forEach(System.out::println);
    }

    /**
     * @Description: 更新用户名为jack的用户的余额为2000
     * @Param: []
     * @return: void
     * @Author: VICooL
     */
    @Test
    void testUpdateByQueryWrapper(){
        // 1.要更新的数据
        User user = new User();
        user.setBalance (2000);
        // 2.更新的条件
        QueryWrapper<User> wrapper = new QueryWrapper<User>()
                .eq("username","jack");
        // 3.执行更新
        userMapper.update(user,wrapper);
    }

    /**
     * @Description: 更新id为1,2,4的用户的余额,扣200
     * @Param: []
     * @return: void
     * @Author: VICooL
     */
    @Test
    void testUpdateWrapper(){
        // 1.要更新的数据
        User user = new User();
        user.setBalance (2000);
        // 2.更新的条件
        QueryWrapper<User> wrapper = new QueryWrapper<User>()
                .eq("username","jack");
        // 3.执行更新
        userMapper.update(user,wrapper);
    }










}
