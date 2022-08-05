package io.github.youthred.goc.authorizer.service.impl;

import cn.hutool.json.JSONUtil;
import io.github.youthred.goc.authorizer.config.auth.SecurityUser;
import io.github.youthred.goc.authorizer.service.IGocAuthUserService;
import io.github.youthred.goc.common.constant.MessageConstant;
import io.github.youthred.goc.common.constant.RedisConstant;
import io.github.youthred.goc.pojo.vo.GocAuthUserVO;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserDetailsService {

    private final RedisTemplate<String, Object> redisTemplate;
    private final IGocAuthUserService iGocAuthUserService;

    public UserServiceImpl(RedisTemplate<String, Object> redisTemplate, IGocAuthUserService iGocAuthUserService) {
        this.redisTemplate = redisTemplate;
        this.iGocAuthUserService = iGocAuthUserService;
        // 初始化用户数据存入Redis
        cacheUsers(iGocAuthUserService.findUserVOTrees());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<GocAuthUserVO> gocAuthUserVOS;
        Object usersJsonStr = redisTemplate.opsForValue().get(RedisConstant.GOC_AUTH_USERS_JSON_STRING);
        if (Objects.nonNull(usersJsonStr)) {
            gocAuthUserVOS = JSONUtil.toList(usersJsonStr.toString(), GocAuthUserVO.class);
        } else {
            gocAuthUserVOS = iGocAuthUserService.findUserVOTrees();
            cacheUsers(gocAuthUserVOS);
        }
        GocAuthUserVO userVO = gocAuthUserVOS.stream().filter(item -> item.getUsername().equals(username)).findAny().orElse(null);
        if (Objects.isNull(userVO)) {
            throw new UsernameNotFoundException(MessageConstant.USERNAME_PASSWORD_ERROR);
        }
        SecurityUser securityUser = new SecurityUser(userVO);
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

    private void cacheUsers(List<GocAuthUserVO> gocAuthUserVOS) {
        redisTemplate.opsForValue().set(RedisConstant.GOC_AUTH_USERS_JSON_STRING, JSONUtil.toJsonStr(gocAuthUserVOS));
    }
}
