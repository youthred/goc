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
        this.enabled = Objects.nonNull(u.getEnabled()) && u.getEnabled().equals(1);
    }

    private Long id;
    private String username;
    private String password;
    private boolean enabled;
    private List<GocAuthRoleVO> roles = new ArrayList<>();

    public List<String> getRoleStrings() {
        return roles.stream().map(GocAuthRoleVO::getCode).collect(Collectors.toList());
    }

    public GocAuthUser toEntity() {
        return new GocAuthUser()
                .setId(id)
                .setUsername(username)
                .setPassword(password)
                .setEnabled(enabled ? 1 : 0);
    }
}
