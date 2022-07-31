package io.github.youthred.goc.authorizer.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.youthred.goc.authorizer.mapper.GocAuthUserMapper;
import io.github.youthred.goc.authorizer.service.IGocAuthUserService;
import io.github.youthred.goc.pojo.entity.GocAuthUser;
import org.springframework.stereotype.Service;

@Service
public class GocAuthUserServiceImpl extends ServiceImpl<GocAuthUserMapper, GocAuthUser> implements IGocAuthUserService {
}
