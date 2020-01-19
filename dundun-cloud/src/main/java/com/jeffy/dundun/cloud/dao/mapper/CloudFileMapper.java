package com.jeffy.dundun.cloud.dao.mapper;

import com.jeffy.dundun.cloud.dao.module.CloudFile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("cloudFileMapper")
public interface CloudFileMapper {
    /**
     * 查询数据
     * @param params
     * @return
     */
    List<CloudFile> selectByMap(Map<String,Object> params);

    /**
     * 插入数据
     * @param notebook
     */
    void insert(CloudFile notebook);

    /**
     * 更新数据
     * @param params
     */
    int updateByMap(Map<String,Object> params);

    /**
     * 删除数据（物理删除）
     * @param params
     */
    void deleteByMap(Map<String,Object> params);
}
