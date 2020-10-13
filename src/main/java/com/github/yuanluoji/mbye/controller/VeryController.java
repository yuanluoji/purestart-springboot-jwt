package com.github.yuanluoji.mbye.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author yuanluoji
 * @date 2020/10/10
 */
@RestController
@CrossOrigin
public class VeryController {
    @ResponseBody
    @GetMapping("aaa")
    String aaa() {
        return UUID.randomUUID().toString();
    }
}
