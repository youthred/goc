package io.github.youthred.goc.testapi.rest;

import cn.hutool.core.collection.CollUtil;
import io.github.youthred.goc.common.constant.RedisConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@RestController
@RequestMapping("/r")
@RequiredArgsConstructor
public class RedisController {

    private final RedisTemplate<String, Object> redisTemplate;

    @GetMapping("/save")
    public void save() {
        Map<String, List<String>> resourceRolesMap;
        resourceRolesMap = new TreeMap<>();
        resourceRolesMap.put(HttpMethod.GET.name() + RedisConstant.GOC_RESOURCE_ROLES_MAP_KEY_METHOD_URI_DELIMITER + "/test-api/hello", CollUtil.toList("ADMIN"));
        resourceRolesMap.put(HttpMethod.GET.name() + RedisConstant.GOC_RESOURCE_ROLES_MAP_KEY_METHOD_URI_DELIMITER + "/test-api/user/currentUser", CollUtil.toList("ADMIN", "TEST"));
        redisTemplate.opsForHash().putAll(RedisConstant.GOC_RESOURCE_ROLES_MAP, resourceRolesMap);
    }
}
