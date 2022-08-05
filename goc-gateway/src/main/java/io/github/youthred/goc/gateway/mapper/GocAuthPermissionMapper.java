package io.github.youthred.goc.gateway.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.youthred.goc.pojo.entity.GocAuthPermission;
import io.github.youthred.goc.pojo.vo.GocAuthPermissionVO;

import java.util.List;

public interface GocAuthPermissionMapper extends BaseMapper<GocAuthPermission> {

    List<GocAuthPermissionVO> listPermissions();
}
