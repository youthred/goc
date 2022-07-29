package io.github.akisora.testapi.rest;

import io.github.akisora.testapi.service.LoginUserHolder;
import io.github.youthred.akisora.common.res.Res;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/hello")
    public Res<?> hello() {
        return Res.success("HELLO");
    }

    @GetMapping("/user/currentUser")
    public Res<?> currentUser() {
        return Res.success(LoginUserHolder.getCurrentUser());
    }
}
