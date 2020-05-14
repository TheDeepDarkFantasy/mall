package com.hyz.mall.service;

import com.hyz.mall.nosql.elasticsearch.document.EsProduct;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author hyz
 * @version 1.0
 * @date 2020/5/14 14:05
 */
public interface EsProductService {
    /**
     * 从数据库中导入所有商品到ES
     * @return
     */
    int importAll();

    /**
     * 根据id删除
     * @param id
     */
    void delete(Long id);

    /**
     * 根据id创建商品
     * @param id
     * @return
     */
    EsProduct create(Long id);

    /**
     * 批量删除
     * @param ids
     */
    void delete(List<Long> ids);

    /**
     * 根据关键字搜索名称或者副标题
     * @param keyword
     * @param pageNum
     * @param pageSize
     * @return
     */
    Page<EsProduct> search(String keyword, Integer pageNum, Integer pageSize);
}
