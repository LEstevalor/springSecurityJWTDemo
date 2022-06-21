package com.it.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 用户表 —— 实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("jwt_user")   //指定到表jwt_user
public class User implements Serializable {
    private static final long serialVersionUID = -40356985123868312L;
    @TableId
    private Long id;            //主键
    private String userName;    //用户名
    private String nickName;    //昵称
    private String password;    //密码
    private String status;      //状态（0正常 1停用）
    private String email;       //邮箱
    private String phonenumber; //手机号
    private String sex;         //性别（0 1 2 男 女 未知）
    private String avatar;      //头像
    private String userType;    //用户类型（0管理员，1普通用户）
    private Long createBy;      //创建用户的ID
    private Date createTime;    //创建时间
    private Long updateBy;      //更新用户的ID
    private Date updateTime;    //更新时间
    private Integer delFlag;    //删除标志（0代表未删除 1代表已删除）
}
