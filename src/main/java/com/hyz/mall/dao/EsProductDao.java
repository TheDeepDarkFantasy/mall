package com.hyz.mall.dao;

import com.hyz.mall.nosql.elasticsearch.document.EsProduct;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author hyz
 * @version 1.0
 * @date 2020/5/14 14:12
 */
public interface EsProductDao {
    /**
     * 搜索系统中的商品管理自定义dao
     * @param id
     * @return
     */
    List<EsProduct> getAllEsProductList(@Param("id") Long id);
}
