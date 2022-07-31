package io.github.youthred.goc.gateway.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.youthred.goc.gateway.mapper.GocAuthUserMapper;
import io.github.youthred.goc.gateway.service.IGocAuthUserService;
import io.github.youthred.goc.pojo.entity.GocAuthUser;
import org.springframework.stereotype.Service;

@Service
public class GocAuthUserServiceImpl extends ServiceImpl<GocAuthUserMapper, GocAuthUser> implements IGocAuthUserService {
}
