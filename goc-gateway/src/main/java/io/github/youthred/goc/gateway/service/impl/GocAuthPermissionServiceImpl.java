package io.github.youthred.goc.gateway.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.youthred.goc.gateway.mapper.GocAuthPermissionMapper;
import io.github.youthred.goc.gateway.service.IGocAuthPermissionService;
import io.github.youthred.goc.pojo.entity.GocAuthPermission;
import io.github.youthred.goc.pojo.vo.GocAuthPermissionVO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class GocAuthPermissionServiceImpl extends ServiceImpl<GocAuthPermissionMapper, GocAuthPermission> implements IGocAuthPermissionService {

    @Override
    public List<GocAuthPermissionVO> listPermissions() {
        return this.baseMapper.listPermissions();
    }

    @Override
    public Map<String, Map<String, List<String>>> listPermissionsForRedis() {
        return this.listPermissions().stream().collect(
                Collectors.groupingBy(
                        GocAuthPermissionVO::getMethod,
                        Collectors.groupingBy(
                                GocAuthPermissionVO::getPath,
                                Collectors.flatMapping(vo -> vo.getRoleStrings().stream(), Collectors.toList())
                        )
                )
        );
    }
}
