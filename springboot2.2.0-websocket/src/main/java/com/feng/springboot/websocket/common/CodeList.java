package com.feng.springboot.websocket.common;

import java.util.Map;

public interface CodeList {

    Map<String, String> getMap(String bizType);

    Map<String, String> toMap();
}
