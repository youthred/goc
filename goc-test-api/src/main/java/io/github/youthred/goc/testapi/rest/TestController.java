package io.github.youthred.goc.testapi.rest;

import io.github.youthred.goc.common.res.Res;
import io.github.youthred.goc.testapi.service.ILoginUserHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {

    public final ILoginUserHolder iLoginUserHolder;

    @GetMapping("/hello")
    public Res<?> hello() {
        return Res.success("HELLO");
    }

    @GetMapping("/user/currentUser")
    public Res<?> currentUser() {
        return Res.success(iLoginUserHolder.getCurrentUser());
    }

    @GetMapping("/a")
    public Res<?> a() {
        return Res.success("a");
    }
}
