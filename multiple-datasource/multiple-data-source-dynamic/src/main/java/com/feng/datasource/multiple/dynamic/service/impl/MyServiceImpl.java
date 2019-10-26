package com.feng.datasource.multiple.dynamic.service.impl;

import com.feng.datasource.multiple.dynamic.repository.cluster.ClusterUserMapper;
import com.feng.datasource.multiple.dynamic.repository.master.MasterUserMapper;
import com.feng.datasource.multiple.dynamic.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class MyServiceImpl implements MyService {

    @Autowired
    private MasterUserMapper masterUserMapper;

    @Autowired
    private ClusterUserMapper clusterUserMapper;

    /**
     * 获取全部数据
     * @return
     */
    public Map<String, Object> all() {
        Map<String, Object> data = new HashMap<>();
        data.put("master", masterUserMapper.getAll());
        data.put("cluster", clusterUserMapper.getAll());
        return data;
    }
}
