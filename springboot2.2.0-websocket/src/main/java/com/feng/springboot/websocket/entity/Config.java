package com.feng.springboot.websocket.entity;

import javax.persistence.*;

/**
 * cron配置信息
 */
@Entity
public class Config {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String cron;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }


    public Config() {
    }

    public Config(String cron) {
        this.cron = cron;
    }

    @Override
    public String toString() {
        return "Config{" +
                "id=" + id +
                ", cron='" + cron + '\'' +
                '}';
    }
}
