package io.github.youthred.goc.gateway.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.youthred.goc.gateway.mapper.GocAuthResourceMapper;
import io.github.youthred.goc.gateway.service.IGocAuthResourceService;
import io.github.youthred.goc.pojo.entity.GocAuthPermission;
import io.github.youthred.goc.pojo.vo.GocAuthPermissionVO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class GocAuthResourceServiceImpl extends ServiceImpl<GocAuthResourceMapper, GocAuthPermission> implements IGocAuthResourceService {

    @Override
    public List<GocAuthPermissionVO> listResourceVOS() {
        return this.baseMapper.listResourceVOS();
    }

    @Override
    public Map<String, Map<String, List<String>>> listResourcesForRedis() {
        return this.listResourceVOS().stream().collect(
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
