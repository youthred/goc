package io.github.youthred.goc.gateway.rest;

import io.github.youthred.goc.common.res.Res;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
public class RoleController {

    @GetMapping("/{username}")
    public Res<?> findByUsername(@PathVariable("username") String username) {
        return Res.success(username);
    }
}
