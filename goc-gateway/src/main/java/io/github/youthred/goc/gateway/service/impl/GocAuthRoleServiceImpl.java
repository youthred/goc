package io.github.youthred.goc.gateway.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.youthred.goc.gateway.mapper.GocAuthRoleMapper;
import io.github.youthred.goc.gateway.service.IGocAuthRoleService;
import io.github.youthred.goc.pojo.entity.GocAuthRole;
import org.springframework.stereotype.Service;

@Service
public class GocAuthRoleServiceImpl extends ServiceImpl<GocAuthRoleMapper, GocAuthRole> implements IGocAuthRoleService {
}
