package com.jeffy.dundun.cloud.dao.mapper;

import com.jeffy.dundun.cloud.dao.module.BaseFileInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("baseFileInfoMapper")
public interface BaseFileInfoMapper {
    /**
     * 查询数据
     * @param params
     * @return
     */
    List<BaseFileInfo> selectByMap(Map<String,Object> params);

    /**
     * 插入数据
     * @param tbOauthInfo
     */
    void insert(BaseFileInfo tbOauthInfo);

    /**
     * 更新数据
     * @param params
     */
    void updateByMap(Map<String,Object> params);

    /**
     * 删除数据（物理删除）
     * @param params
     */
    void deleteByMap(Map<String,Object> params);
}
