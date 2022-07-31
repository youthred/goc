package io.github.youthred.goc.authorizer.rest;

import io.github.youthred.goc.common.res.Res;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
public class RoleController {

    @PostMapping("/{username}")
    public Res<?> findRoleByUsername(@PathVariable("username") String username) {
        return Res.success("xx");
    }
}