package com.hyz.mall.service;

import com.hyz.mall.nosql.mongodb.document.MemberReadHistory;

import java.util.List;

/**
 * 会员浏览记录管理service
 * @author hyz
 * @version 1.0
 * @date 2020/5/15 15:32
 */
public interface MemberReadHistoryService {

    /**
     * 生成浏览记录
     * @param memberReadHistory
     * @return
     */
    int create(MemberReadHistory memberReadHistory);

    /**
     * 查询浏览记录
     * @param memberId
     * @return
     */
    List<MemberReadHistory> list(Long memberId);

    /**
     * 批量删除浏览记录
     * @param ids
     * @return
     */
    int delete(List<String> ids);

}
