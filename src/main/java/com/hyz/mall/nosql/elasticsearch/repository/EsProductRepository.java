package com.hyz.mall.nosql.elasticsearch.repository;

import com.hyz.mall.nosql.elasticsearch.document.EsProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author hyz
 * @version 1.0
 * @date 2020/5/14 14:02
 */
public interface EsProductRepository extends ElasticsearchRepository<EsProduct,Long> {

    /**
     * 搜索查询
     * @param name
     * @param subTitle
     * @param keywords
     * @param page
     * @return
     */
    Page<EsProduct> findByNameOrSubTitleOrKeywords(String name, String subTitle, String keywords, Pageable page);
}
