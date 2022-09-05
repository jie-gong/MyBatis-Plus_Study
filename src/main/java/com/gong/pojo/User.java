package com.gong.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    //主键自增
    @TableId(type = IdType.AUTO)
    private Long id;//对应数据库中的主键（uuid，自增id，雪花算法，redis，zookeeper）
    private String name;
    private Integer age;
    private String email;
    @TableLogic//逻辑删除注解
    private Integer deleted;
    @Version //乐观锁version
    private Integer version;

    //字段填充内容g
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

}
