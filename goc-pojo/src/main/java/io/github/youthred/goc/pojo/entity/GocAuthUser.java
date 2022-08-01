package io.github.youthred.goc.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Objects;

/**
 * oauth user
 *
 * @author https://github.com/youthred
 */
@Data
@Accessors(chain = true)
@TableName("t_goc_auth_user")
public class GocAuthUser implements Serializable {

    private static final long serialVersionUID = -293483745765510117L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("username")
    private String username;

    @TableField("password")
    private String password;

    @TableField("enabled")
    private Integer enabled;
}
