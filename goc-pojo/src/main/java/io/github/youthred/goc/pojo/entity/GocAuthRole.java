package io.github.youthred.goc.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * oauth role
 *
 * @author https://github.com/youthred
 */
@Data
@Accessors(chain = true)
@TableName("t_goc_auth_role")
public class GocAuthRole implements Serializable {

    private static final long serialVersionUID = 8961769900343427365L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("code")
    private String code;

    @TableField("name")
    private String name;

    @TableField("desc")
    private String desc;
}
