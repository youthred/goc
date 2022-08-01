package io.github.youthred.goc.authorizer.config.auth;

import io.github.youthred.goc.pojo.vo.GocAuthUserVO;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class SecurityUser implements UserDetails {

    private static final long serialVersionUID = -4175621569199955336L;

    /**
     * ID
     */
    private Long id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 用户密码
     */
    private String password;
    /**
     * 用户状态
     */
    private Boolean enabled;
    /**
     * 权限数据
     */
    private Collection<SimpleGrantedAuthority> authorities;

    public SecurityUser(GocAuthUserVO gocAuthUserVO) {
        this.setId(gocAuthUserVO.getId());
        this.setUsername(gocAuthUserVO.getUsername());
        this.setPassword(gocAuthUserVO.getPassword());
        this.setEnabled(gocAuthUserVO.isEnabled());
        this.setAuthorities(gocAuthUserVO.getRoleStrings().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}