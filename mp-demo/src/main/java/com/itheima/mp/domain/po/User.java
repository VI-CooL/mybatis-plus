package com.itheima.mp.domain.po;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("tb_user")
public class User {

    /**
     * 用户id
     * IdType.AUTO 代表自增长
     */
//    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户名
     */
//    @TableField("`username`")
    private String username;

    /**
     * 密码
     */
//    @TableField(exist = false)
    private String password;

    /**
     * 注册手机号
     */
    private String phone;

    /**
     * 详细信息
     */
    private String info;

    /**
     * 使用状态（1正常 2冻结）
     */
    private Integer status;

    /**
     * 账户余额
     */
    private Integer balance;

    @TableLogic(value = "0", delval = "1")
    private Integer isDeleted;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;

//    @Override
//    public String toString() {
//        return "User{" +
//                "id=" + id +
//                ", username='" + username + '\'' +
//                ", password='" + password + '\'' +
//                ", phone='" + phone + '\'' +
//                ", info='" + info + '\'' +
//                ", status=" + status +
//                ", balance=" + balance +
//                ", createTime=" + createTime +
//                ", updateTime=" + updateTime +
//                '}';
//    }
}
