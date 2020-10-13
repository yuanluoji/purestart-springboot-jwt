package com.github.yuanluoji.mbye.jwt;

import lombok.Data;

/**
 * @author yuanluoji
 * @date 2020/09/27
 */
@Data
public class JwtRequest {
    private String username;
    private String password;
}
