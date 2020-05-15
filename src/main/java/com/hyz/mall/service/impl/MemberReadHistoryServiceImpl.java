package com.hyz.mall.service.impl;

import com.hyz.mall.nosql.mongodb.document.MemberReadHistory;
import com.hyz.mall.nosql.mongodb.repository.MemberReadHistoryRepository;
import com.hyz.mall.service.MemberReadHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author hyz
 * @version 1.0
 * @date 2020/5/15 15:34
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MemberReadHistoryServiceImpl implements MemberReadHistoryService {

    private final MemberReadHistoryRepository memberReadHistoryRepository;


    /**
     * 生成浏览记录
     *
     * @param memberReadHistory
     * @return
     */
    @Override
    public int create(MemberReadHistory memberReadHistory) {
        //id设置为null，mongodb会自动生成id，id由前台传入（如果传入的id与之前的id相同，会进行覆盖）
        memberReadHistory.setId(null);
        memberReadHistory.setCreateTime(new Date());
        memberReadHistoryRepository.save(memberReadHistory);
        return 1;
    }

    /**
     * 查询浏览记录
     *
     * @param memberId
     * @return
     */
    @Override
    public List<MemberReadHistory> list(Long memberId) {
        List<MemberReadHistory> result = memberReadHistoryRepository.findByMemberIdOrderByCreateTimeDesc(memberId);
        return result;
    }

    /**
     * 批量删除浏览记录
     *
     * @param ids
     * @return
     */
    @Override
    public int delete(List<String> ids) {
        List<MemberReadHistory> deleteList=new ArrayList<MemberReadHistory>();
        for (String id:ids){
            MemberReadHistory memberReadHistory=new MemberReadHistory();
            memberReadHistory.setId(id);
            deleteList.add(memberReadHistory);
        }
        memberReadHistoryRepository.deleteAll(deleteList);
        return ids.size();
    }
}
