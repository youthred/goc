package io.github.youthred.goc.gateway.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.youthred.goc.pojo.entity.GocAuthResource;
import io.github.youthred.goc.pojo.vo.GocAuthResourceVO;

import java.util.List;

public interface GocAuthResourceMapper extends BaseMapper<GocAuthResource> {

    List<GocAuthResourceVO> listResourceVOS();
}
