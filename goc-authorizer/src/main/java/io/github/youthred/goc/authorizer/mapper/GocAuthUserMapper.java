package io.github.youthred.goc.authorizer.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.youthred.goc.pojo.entity.GocAuthUser;
import io.github.youthred.goc.pojo.vo.GocAuthUserVO;

import java.util.List;

public interface GocAuthUserMapper extends BaseMapper<GocAuthUser> {

    List<GocAuthUserVO> findUserVOTrees();
}
