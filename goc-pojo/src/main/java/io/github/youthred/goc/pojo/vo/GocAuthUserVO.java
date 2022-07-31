package io.github.youthred.goc.pojo.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class GocAuthUserVO {

    private Long id;
    private String username;
    private String password;
    private Boolean enabled;
    private List<String> roles;
}
