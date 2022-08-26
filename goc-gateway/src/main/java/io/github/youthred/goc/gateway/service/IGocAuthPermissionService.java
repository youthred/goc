package io.github.youthred.goc.gateway.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.youthred.goc.pojo.entity.GocAuthPermission;
import io.github.youthred.goc.pojo.vo.GocAuthPermissionVO;

import java.util.List;
import java.util.Map;

public interface IGocAuthPermissionService extends IService<GocAuthPermission> {

    List<GocAuthPermissionVO> listPermissions();

    /**
     * Redis Permissions
     * Map<String, Map<String, List<String>>>
     * ----Method------Path---------Roles----
     *
     * @return map
     */
    Map<String, Map<String, List<String>>> listPermissionsForRedis();
}
