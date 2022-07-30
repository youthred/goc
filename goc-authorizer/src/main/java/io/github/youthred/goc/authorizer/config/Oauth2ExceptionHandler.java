package io.github.youthred.goc.authorizer.config;

import io.github.youthred.goc.common.res.Res;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class Oauth2ExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = OAuth2Exception.class)
    public Res handleOauth2(OAuth2Exception e) {
        return Res.failed(e.getMessage());
    }
}