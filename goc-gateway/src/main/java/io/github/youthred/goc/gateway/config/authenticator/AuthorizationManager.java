package io.github.youthred.goc.gateway.config.authenticator;

import cn.hutool.core.convert.Convert;
import io.github.youthred.goc.common.constant.AuthConstant;
import io.github.youthred.goc.common.constant.RedisConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 鉴权管理器，用于判断是否有资源的访问权限
 *
 * @author https://github.com/youthred
 */
@Component
@RequiredArgsConstructor
public class AuthorizationManager implements ReactiveAuthorizationManager<AuthorizationContext> {

    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> mono, AuthorizationContext authorizationContext) {
        /*
            String bearer = authorizationContext.getExchange().getRequest().getHeaders().getOrEmpty(HttpHeaders.AUTHORIZATION).stream().findAny().orElse(null);
            if (bearer != null) {
                String token = bearer.replace(AuthConstant.TOKEN_HEAD + " ", "");
                JWTUtil.parseToken(token).getPayloads();
            }
         */
        ServerHttpRequest request = authorizationContext.getExchange().getRequest();
        URI uri = request.getURI();
        Object resourceRolesObj = redisTemplate.opsForHash().get(RedisConstant.GOC_RESOURCE_ROLES_MAP, request.getMethodValue() + RedisConstant.GOC_RESOURCE_ROLES_MAP_KEY_METHOD_URI_DELIMITER + uri.getPath());
        List<String> authorities = Convert.toList(String.class, resourceRolesObj);
        authorities = authorities.stream().map(i -> AuthConstant.AUTHORITY_PREFIX + i).collect(Collectors.toList());
        // 认证通过且角色匹配的用户可访问当前路径
        return mono
                .filter(Authentication::isAuthenticated)
                .flatMapIterable(Authentication::getAuthorities)
                .map(GrantedAuthority::getAuthority)
                .any(authorities::contains)
                .map(AuthorizationDecision::new)
                .defaultIfEmpty(new AuthorizationDecision(false));
    }
}