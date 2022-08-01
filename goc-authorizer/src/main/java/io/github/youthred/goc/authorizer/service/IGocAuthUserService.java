package io.github.youthred.goc.authorizer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.youthred.goc.pojo.entity.GocAuthUser;
import io.github.youthred.goc.pojo.vo.GocAuthUserVO;

import java.util.List;

public interface IGocAuthUserService extends IService<GocAuthUser> {

    List<GocAuthUserVO> findUserVOS();
}
