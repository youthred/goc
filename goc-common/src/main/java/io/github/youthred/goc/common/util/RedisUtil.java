package io.github.youthred.goc.common.util;

import org.apache.commons.lang3.StringUtils;

/**
 * @author https://github.com/youthred
 */
public class RedisUtil {

    /**
     * 构造Key路径
     *
     * @param chines chains
     * @return Key String
     */
    public static String keyChain(String... chines) {
        return StringUtils.join(chines, ":");
    }
}
