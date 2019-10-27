package com.xh.controller;


import com.xh.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 系统用户 前端控制器
 * </p>
 *
 * @author xiaohe
 * @since 2019-06-04
 */
@RestController
@RequestMapping("/sys-user")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @GetMapping("/")
    public Map<String, Object> test() {
        Map<String, Object> data = new HashMap<>();
        data.put("first", sysUserService.findUserByFirstDb(1l));
        data.put("second", sysUserService.findUserBySecondDb(1l));
        return data;
    }
}
