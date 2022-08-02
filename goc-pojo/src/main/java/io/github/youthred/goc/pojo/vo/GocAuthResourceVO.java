package io.github.youthred.goc.pojo.vo;

import io.github.youthred.goc.pojo.entity.GocAuthResource;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class GocAuthResourceVO {

    public GocAuthResourceVO(GocAuthResource r) {
        this.id = r.getId();
        this.parentId = r.getParentId();
        this.path = r.getPath();
        this.method = r.getMethod();
    }

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

    private List<String> roleStrings;

    public void setMethodPath(String mp) {
        if (StringUtils.isNotBlank(mp)) {
            String[] split = mp.split(":");
            this.method = split[0];
            this.path = split[1];
        }
    }
}
