#! /usr/bin/python
# -*- coding: UTF-8 -*-

import smtplib
from email.mime.text import MIMEText
from email.header import Header

sender = "445121408@qq.com"
receivers = ['445121408@qq.com']

# 三个参数 第一个为文本内容，第二个 plain 设置文本格式，第三个 utf-8 设置编码
message = MIMEText('Python  邮件发送测试', 'plain', 'utf-8')
message['from'] = Header("菜鸟教程", "utf-8")   # 发送者
message['to'] = Header("测试", "utf-8")   # 接收者

subject = 'Python SMTP 邮件测试'
message['Subject'] = Header(subject, "utf-8")

