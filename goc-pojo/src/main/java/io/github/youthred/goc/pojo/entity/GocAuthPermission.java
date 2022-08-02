package io.github.youthred.goc.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * oauth resource
 *
 * @author https://github.com/youthred
 */
@Data
@Accessors(chain = true)
@TableName("t_goc_auth_permission")
public class GocAuthPermission implements Serializable {

    private static final long serialVersionUID = -1925857422787099642L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 0 表示 top parent
     */
    @TableField("parent_id")
    private Long parentId;

    /**
     * RESTful Path
     */
    @TableField("path")
    private String path;

    /**
     * Request Method
     */
    @TableField("method")
    private String method;
}
