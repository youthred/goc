package io.github.youthred.goc.testapi.service;

import io.github.youthred.goc.pojo.vo.GocAuthUserVO;
import io.github.youthred.goc.testapi.config.X;


public interface ILoginUserHolder {
    @X
    GocAuthUserVO getCurrentUser();
}
