package io.github.youthred.goc.gateway.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.youthred.goc.gateway.mapper.GocAuthResourceMapper;
import io.github.youthred.goc.gateway.service.IGocAuthResourceService;
import io.github.youthred.goc.pojo.entity.GocAuthResource;
import org.springframework.stereotype.Service;

@Service
public class GocAuthResourceServiceImpl extends ServiceImpl<GocAuthResourceMapper, GocAuthResource> implements IGocAuthResourceService {
}
