package io.github.youthred.goc.testapi.rest;

import io.github.youthred.goc.testapi.service.LoginUserHolder;
import io.github.youthred.goc.common.res.Res;
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

    @GetMapping("/a")
    public Res<?> a() {
        return Res.success("a");
    }
}
