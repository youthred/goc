package io.github.youthred.goc.gateway.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.youthred.goc.pojo.entity.GocAuthResource;
import io.github.youthred.goc.pojo.vo.GocAuthResourceVO;

import java.util.List;
import java.util.Map;

public interface IGocAuthResourceService extends IService<GocAuthResource> {

    List<GocAuthResourceVO> listResourceVOS();

    /**
     * Redis Resources
     * Map<String, Map<String, List<String>>>
     * ----Method------Path---------Roles----
     * @return map
     */
    Map<String, Map<String, List<String>>> listResourcesForRedis();
}
