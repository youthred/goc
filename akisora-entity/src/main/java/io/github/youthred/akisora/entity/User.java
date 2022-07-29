package io.github.youthred.akisora.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class User {

    private Long id;
    private String username;
    private String password;
    private Integer status;
    private List<String> roles;
}
