package io.github.youthred.goc.authorizer.service;

import cn.hutool.core.collection.CollUtil;
import io.github.youthred.goc.authorizer.config.auth.SecurityUser;
import io.github.youthred.goc.common.constant.MessageConstant;
import io.github.youthred.goc.pojo.vo.GocAuthUserVO;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserDetailsService {

    private final List<GocAuthUserVO> gocAuthUserVOS;

    public UserServiceImpl(PasswordEncoder passwordEncoder) {
        gocAuthUserVOS = new ArrayList<>(1);
        gocAuthUserVOS.add(
                new GocAuthUserVO().setId(123L).setUsername("admin").setPassword(passwordEncoder.encode("123456")).setEnabled(true).setRoles(Collections.singletonList("ADMIN"))
        );
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<GocAuthUserVO> findGocAuthUserVOList = gocAuthUserVOS.stream().filter(item -> item.getUsername().equals(username)).collect(Collectors.toList());
        if (CollUtil.isEmpty(findGocAuthUserVOList)) {
            throw new UsernameNotFoundException(MessageConstant.USERNAME_PASSWORD_ERROR);
        }
        SecurityUser securityUser = new SecurityUser(findGocAuthUserVOList.get(0));
        if (!securityUser.isEnabled()) {
            throw new DisabledException(MessageConstant.ACCOUNT_DISABLED);
        } else if (!securityUser.isAccountNonLocked()) {
            throw new LockedException(MessageConstant.ACCOUNT_LOCKED);
        } else if (!securityUser.isAccountNonExpired()) {
            throw new AccountExpiredException(MessageConstant.ACCOUNT_EXPIRED);
        } else if (!securityUser.isCredentialsNonExpired()) {
            throw new CredentialsExpiredException(MessageConstant.CREDENTIALS_EXPIRED);
        }
        return securityUser;
    }
}
