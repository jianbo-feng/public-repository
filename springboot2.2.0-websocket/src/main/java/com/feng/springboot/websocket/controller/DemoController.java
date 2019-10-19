package com.feng.springboot.websocket.controller;

import com.feng.springboot.websocket.common.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Controller
public class DemoController {

    @Autowired
    HttpServletRequest request;

    @RequestMapping(value = {"/", "", "/index", "index"})
    public String index(@RequestParam(defaultValue = "test") String name, Model model) {
        model.addAttribute("name", name);
        return "index";
    }

    @RequestMapping(value = {"/test"})
    public String test(HashMap<String, Object> map) {
        map.put("hello", "欢迎进入HTML页面");
        return "test";
    }

    /**
     * WebSocket前端访问路径
     * @param name 用户名，默认：test
     * @param model 存储数据发送到前端
     * @return
     */
    @RequestMapping("web")
    public String web(@RequestParam(defaultValue = "test") String name, Model model) {
        model.addAttribute("name", name);
        // 向会话中写入信息
        HttpSession httpSession = request.getSession(true);
        httpSession.setAttribute(Constants.SESSION_USERNAME, name);
        model.addAttribute("JSESSIONID", httpSession.getId());
        return "web";
    }

    /**
     * 定时任务设计
     * @return
     * @see <a href="http://cron.qqe2.com/">参考</a>
     */
    @RequestMapping(value = {"cron", "/cron", "/cron/index", "/cron/"})
    public String cron() {
        return "/cron/index";
    }
}


