package io.github.youthred.goc.pojo.vo;

import io.github.youthred.goc.pojo.entity.GocAuthUser;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class GocAuthUserVO {

    public GocAuthUserVO(GocAuthUser u) {
        this.id = u.getId();
        this.username = u.getUsername();
        this.password = u.getPassword();
        this.setEnabled(u.getEnabled());
    }

    private Long id;
    private String username;
    private String password;
    private Boolean enabled;
    private List<GocAuthRoleVO> roles = new ArrayList<>();

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = Objects.nonNull(enabled) && enabled.equals(1);
    }

    public List<String> getRoleStrings() {
        return roles.stream().map(GocAuthRoleVO::getCode).collect(Collectors.toList());
    }

    public GocAuthUser toEntity() {
        GocAuthUser gocAuthUser = new GocAuthUser()
                .setId(id)
                .setUsername(username)
                .setPassword(password);
        if (Objects.nonNull(enabled)) {
            gocAuthUser.setEnabled(enabled ? 1 : 0);
        } else {
            gocAuthUser.setEnabled(0);
        }
        return gocAuthUser;
    }
}
