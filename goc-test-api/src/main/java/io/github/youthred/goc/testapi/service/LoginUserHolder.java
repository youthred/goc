package io.github.youthred.goc.testapi.service;

import cn.hutool.core.convert.Convert;
import cn.hutool.json.JSONObject;
import io.github.youthred.goc.common.constant.AuthConstant;
import io.github.youthred.goc.pojo.vo.GocAuthUserVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class LoginUserHolder {

    public static GocAuthUserVO getCurrentUser() {
        // 从Header中获取用户信息
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert servletRequestAttributes != null;
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String userStr = request.getHeader(AuthConstant.HEADER_USER);
        if (StringUtils.isBlank(userStr)) {
            return null;
        }
        JSONObject userJsonObject = new JSONObject(userStr);
        GocAuthUserVO gocAuthUserVO = new GocAuthUserVO();
        gocAuthUserVO.setUsername(userJsonObject.getStr("user_name"));
        gocAuthUserVO.setId(Convert.toLong(userJsonObject.get("id")));
        gocAuthUserVO.setRoles(Convert.toList(String.class, userJsonObject.get(AuthConstant.AUTHORITY_CLAIM_NAME)));
        return gocAuthUserVO;
    }
}
