package com.hyz.mall.service.impl;

import com.hyz.mall.dao.EsProductDao;
import com.hyz.mall.nosql.elasticsearch.document.EsProduct;
import com.hyz.mall.nosql.elasticsearch.repository.EsProductRepository;
import com.hyz.mall.service.EsProductService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author hyz
 * @version 1.0
 * @date 2020/5/14 14:09
 */
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class EsProductServiceImpl implements EsProductService {

    private static final Logger LOGGER= LoggerFactory.getLogger(EsProductServiceImpl.class);

    private final EsProductRepository productRepository;


    private final EsProductDao productDao;

    /**
     * 从数据库中导入所有商品到ES
     *
     * @return
     */
    @Override
    public int importAll() {
        List<EsProduct> esProductList = productDao.getAllEsProductList(null);
        Iterable<EsProduct> esProductIterable = productRepository.saveAll(esProductList);
        Iterator<EsProduct> iterator = esProductIterable.iterator();
        int result=0;
        while (iterator.hasNext()){
            result++;
            iterator.next();
        }


        return result;
    }

    /**
     * 根据id删除
     *
     * @param id
     */
    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    /**
     * 根据id创建商品
     *
     * @param id
     * @return
     */
    @Override
    public EsProduct create(Long id) {
        EsProduct result=null;
        List<EsProduct> esProductList = productDao.getAllEsProductList(id);
        if (esProductList.size()>0){
            EsProduct esProduct = esProductList.get(0);
            result=esProduct;
        }
        return result;
    }

    /**
     * 批量删除
     *
     * @param ids
     */
    @Override
    public void delete(List<Long> ids) {
        if (!CollectionUtils.isEmpty(ids)){
            List<EsProduct> esProductList=new ArrayList<>();
            for (Long id:ids){
                EsProduct product=new EsProduct();
                product.setId(id);
                esProductList.add(product);
            }
            productRepository.deleteAll(esProductList);
        }
    }

    /**
     * 根据关键字搜索名称或者副标题
     *
     * @param keyword
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public Page<EsProduct> search(String keyword, Integer pageNum, Integer pageSize) {
        PageRequest pageable = PageRequest.of(pageNum, pageSize);
        return productRepository.findByNameOrSubTitleOrKeywords(keyword,keyword,keyword,pageable);
    }
}
