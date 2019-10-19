package com.feng.springboot.websocket.controller;

import com.feng.springboot.websocket.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
@RequestMapping("/mail")
public class MailController {

    @Autowired
    private MailService mailService;

    /**
     * 发送简单邮件
     * @return
     */
    @GetMapping("sendSimpleMail")
    public Map<String, Object> sendSimpleMail() {
        return mailService.sendSimpleMail();
    }

    /**
     * 发送带附件邮件
     * @return
     */
    @GetMapping("sendAttachmentsMail")
    public Map<String, Object> sendAttachmentsMail() {
        return mailService.sendAttachmentsMail();
    }

    /**
     * 包含静态html内容发送
     * @return
     * @throws Exception
     */
    @GetMapping("sendInlineMail")
    public Map<String, Object> sendInlineMail() {
        return mailService.sendInlineMail();
    }

    /**
     * 包含静态html内容发送
     * @return
     * @throws Exception
     */
    @GetMapping("sendExcel")
    public Map<String, Object> sendExcel() {
        return mailService.sendExcel();
    }

    /**
     * 根据模版发送邮件
     * @return
     * @throws Exception
     */
    @GetMapping("sendTemplateMail")
    public Map<String, Object> sendTemplateMail() {
        return mailService.sendTemplateMail();
    }
}
