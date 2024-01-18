package com.itheima.mp.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.itheima.mp.domain.po.User;
import com.itheima.mp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class UserMapperTest {

    @Autowired
    private  UserMapper userMapper;

    @Autowired
    private  UserService userService;
    @Test
    void testInsert() {
        User user = new User();
        // user.setId(5L);
        user.setUsername("Tour");
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
    void testQueryWrapper() {
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
    void testUpdateByQueryWrapper() {
        // 1.要更新的数据
        User user = new User();
        user.setBalance(2000);
        // 2.更新的条件
        QueryWrapper<User> wrapper = new QueryWrapper<User>()
                .eq("username", "jack");
        // 3.执行更新
        userMapper.update(user, wrapper);
    }

    /**
     * @Description: 更新id为1, 2, 4的用户的余额, 扣200
     * @Param: []
     * @return: void
     * @Author: VICooL
     */
    @Test
    void testUpdateWrapper() {
        List<Long> ids = Arrays.asList(1L, 2L, 4L);
        UpdateWrapper<User> wrapper = new UpdateWrapper<User>()
                .setSql("balance = balance - 200")
                .in("id", ids);
        // 执行更新
        userMapper.update(null, wrapper);
    }

    /**
     * @Description: 查询出名字中带o的，存款大于等于1000元的人的id、username、info、balance字段
     * LambdaQueryWrapper
     * @Param: []
     * @return: void
     * @Author: VICooL
     */
    @Test
    void testLambdaQueryWrapper() {
        // 1.构造查询条件
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<User>()
                .select(User::getId, User::getUsername, User::getInfo, User::getBalance)
                .like(User::getUsername, "o")
                .ge(User::getBalance, 1000);
        // 查询
        List<User> users = userMapper.selectList(wrapper);
        users.forEach(System.out::println);
    }

    /**
     * @Description: 自定义sql,更新id为1, 2, 4的用户的余额, 扣200
     * @Param: []
     * @return: void
     * @Author: VICooL
     * summary: 1.基于Wrapper构建条件
     *          2.在mapper方法参数中用Param注解接收wrapper变量,必须是ew
     *          3.自定义SQL,并使用Wrapper条件
     */
    @Test
    void testCustomerSqlUpdate() {
        // 1.更新条件
        List<Long> ids = Arrays.asList(1L, 2L, 4L);
        int amount = 200;
        // 2.自定义条件
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<User>()
                .in(User::getId, ids);
        // 3.调用自定义SQL方法
        userMapper.customerSqlUpdate(wrapper, amount);

    }



    /**
     * @Description: @TableLogic(value = "0", delval = "1")  isDelete 字段加 TableLogic 注解
     *               测试 BaseMapper delete 方法是否是逻辑删除
     * @Param: []
     * @return: void
     * @Author: VICooL
     * @Date: 2024/1/11
     * summary: baseMapper.delete(Wrappers.lambdaQuery(User.class).eq(User::getId,id));  是逻辑删除
     */
    @Test
    void testBaseMapperDelete01(){
        Integer id = 8;
        userService.deleteUserById(8);
    }

    
    /**
     * @Description:  @TableLogic(value = "0", delval = "1")  isDelete 字段加 TableLogic 注解
     *                测试 BaseMapper delete 方法是否是逻辑删除
     * @Param: []
     * @return: void
     * @Author: VICooL
     * @Date: 2024/1/11
     * summary: baseMapper.delete(Wrappers.lambdaQuery(User.class).in(User::getId,ids)); 是逻辑删除
     */
    @Test
    void testBaseMapperDelete02(){
        List<Integer> ids = Arrays.asList(5,6,7);
        userService.deleteUserLstByIds(ids);
    }
    
    /**
     * @Description:  @TableLogic(value = "0", delval = "1")  isDelete 字段加 TableLogic 注解
     *                测试这个注解会不会影响 super.saveOrUpdate
     * @Param: []
     * @return: void
     * @Author: VICooL
     * @Date: 2024/1/11
     * summary: 对增加没有影响
     */
    @Test
    void testSaveOrUpdate(){
        User user = new User();
        //user.setId(5L);
        user.setUsername("ryota");
        user.setPassword("123");
        user.setPhone("18688990012");
        user.setBalance(200);
        user.setInfo("{\"age\": 24, \"intro\": \"贝斯手\", \"gender\": \"female\"}");
        user.setIsDeleted(0);
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());

        User user1 = new User();
        //user.setId(5L);
        user1.setUsername("tomoya");
        user1.setPassword("123");
        user1.setPhone("18688990012");
        user1.setBalance(200);
        user1.setInfo("{\"age\": 24, \"intro\": \"鼓手\", \"gender\": \"female\"}");
        user1.setIsDeleted(0);
        user1.setCreateTime(LocalDateTime.now());
        user1.setUpdateTime(LocalDateTime.now());

        List<User> userList = new ArrayList<>();
        userList.add(user);
        userList.add(user1);

        userService.addGroupTagRelDefLst(userList);

    }
    
    /**
     * @Description:   @TableLogic(value = "0", delval = "1")  isDelete 字段加 TableLogic 注解
     *                 测试这个注解会不会影响到 把删除数据的标识设置为未删除 super.saveOrUpdate
     * @Param: []
     * @return: void
     * @Author: VICooL
     * @Date: 2024/1/11
     * summary: 对修改有影响
     *          实际的查询（SELECT id,username,password,phone,info,status,balance,is_deleted,create_time,update_time FROM tb_user
     *          WHERE id=? AND is_deleted=0）   他会自己加上 is_deleted = 0
     *          如果更新数据要把已经删除变为未删除 需要自己手写sql了,因为mybatis-plus 的查询自动会加上 is_deleted = 0 的条件
     */
    @Test
    void testSaveOrUpdate01() {
        User user = new User();
        user.setId(6L);
        user.setUsername("JayZhou");
        user.setPassword("123");
        user.setPhone("18688990012");
        user.setBalance(200);
        user.setInfo("{\"age\": 33, \"intro\": \"歌手\", \"gender\": \"female\"}");
        user.setIsDeleted(0);
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        List<User> userList = new ArrayList<>();
        userList.add(user);
        userService.addGroupTagRelDefLst(userList);
    }




}
