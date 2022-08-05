package io.github.youthred.goc.gateway.config.authenticator;

import cn.hutool.core.convert.Convert;
import io.github.youthred.goc.common.constant.AuthConstant;
import io.github.youthred.goc.common.constant.RedisConstant;
import io.github.youthred.goc.common.util.RedisUtil;
import io.github.youthred.goc.gateway.service.IGocAuthPermissionService;
import org.apache.commons.collections4.MapUtils;
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
public class AuthorizationManager implements ReactiveAuthorizationManager<AuthorizationContext> {

    private static final PathMatcher ANT_PATH_MATCHER = new AntPathMatcher();

    private final RedisTemplate<String, Object> redisTemplate;
    private final IGocAuthPermissionService iGocAuthPermissionService;

    public AuthorizationManager(RedisTemplate<String, Object> redisTemplate, IGocAuthPermissionService iGocAuthPermissionService) {
        this.redisTemplate = redisTemplate;
        this.iGocAuthPermissionService = iGocAuthPermissionService;
        // 初始化资源数据存入Redis
        cachePermissions(iGocAuthPermissionService.listPermissionsForRedis());
    }

    private void cachePermissions(Map<String, Map<String, List<String>>> methodPathRolesMap) {
        methodPathRolesMap.forEach((method, pathRoles) -> redisTemplate.opsForHash().putAll(RedisUtil.keyChain(RedisConstant.GOC_AUTH_PERMISSION_MAP, method), pathRoles));
    }

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
        String methodValue = request.getMethodValue();
        List<String> authorities = new ArrayList<>();
        String key = RedisUtil.keyChain(RedisConstant.GOC_AUTH_PERMISSION_MAP, methodValue);
        Map<String, List<String>> entries = redisTemplate.opsForHash().entries(key).entrySet().stream().collect(Collectors.toMap(e -> e.getKey().toString(), e -> Convert.toList(String.class, e.getValue())));
        if (MapUtils.isEmpty(entries)) {
            Map<String, Map<String, List<String>>> permissionsForRedis = iGocAuthPermissionService.listPermissionsForRedis();
            if (MapUtils.isNotEmpty(permissionsForRedis)) {
                entries = permissionsForRedis.get(methodValue);
            }
            cachePermissions(permissionsForRedis);
        }
        if (MapUtils.isNotEmpty(entries)) {
            for (Map.Entry<String, List<String>> pathRole : entries.entrySet()) {
                if (ANT_PATH_MATCHER.match(pathRole.getKey(), uri.getPath())) {
                    authorities = pathRole.getValue().stream().map(i -> AuthConstant.AUTHORITY_PREFIX + i).collect(Collectors.toList());
                }
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