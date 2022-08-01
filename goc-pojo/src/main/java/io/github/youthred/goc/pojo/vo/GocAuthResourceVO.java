package io.github.youthred.goc.pojo.vo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class GocAuthResourceVO {

    private Long id;
    /**
     * 0 表示 top parent
     */
    private Long parentId;
    /**
     * RESTful Path
     */
    private String path;
    /**
     * Request Method
     */
    private String method;
}
