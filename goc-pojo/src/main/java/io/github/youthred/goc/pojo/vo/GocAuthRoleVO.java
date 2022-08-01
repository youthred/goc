package io.github.youthred.goc.pojo.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Data
@Accessors(chain = true)
public class GocAuthRoleVO {

    private Long id;
    private String code;
    private String name;
    private String desc;
    private List<GocAuthResourceVO> resources = new ArrayList<>();
}
