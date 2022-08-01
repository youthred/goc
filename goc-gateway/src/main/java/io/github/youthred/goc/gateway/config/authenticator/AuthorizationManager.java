package io.github.youthred.goc.gateway.config.authenticator;

import cn.hutool.core.convert.Convert;
import io.github.youthred.goc.common.constant.AuthConstant;
import io.github.youthred.goc.common.constant.RedisConstant;
import io.github.youthred.goc.gateway.util.RedisUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 鉴权管理器，用于判断是否有资源的访问权限
 *
 * @author https://github.com/youthred
 */
@Component
@RequiredArgsConstructor
public class AuthorizationManager implements ReactiveAuthorizationManager<AuthorizationContext> {

    private static final PathMatcher ANT_PATH_MATCHER = new AntPathMatcher();

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
        List<String> authorities = new ArrayList<>();
        String key = RedisUtil.keyChain(RedisConstant.GOC_AUTH_RESOURCE_ROLES_MAP, request.getMethodValue());
        Map<Object, Object> entries = redisTemplate.opsForHash().entries(key);
        for (Map.Entry<Object, Object> pathRole : entries.entrySet()) {
            if (ANT_PATH_MATCHER.match(pathRole.getKey().toString(), uri.getPath())) {
                List<String> roles = Convert.toList(String.class, pathRole.getValue());
                authorities = roles.stream().map(i -> AuthConstant.AUTHORITY_PREFIX + i).collect(Collectors.toList());
            }
        }
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