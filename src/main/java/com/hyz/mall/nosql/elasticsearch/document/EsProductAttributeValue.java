package com.hyz.mall.nosql.elasticsearch.document;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * 搜索中的商品属性信息
 * @author hyz
 * @version 1.0
 * @date 2020/5/14 13:58
 */
@Data
public class EsProductAttributeValue  implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Long productAttributeId;
    @Field(type = FieldType.Keyword)
    private String value;
    private Integer type;
    @Field(type = FieldType.Keyword)
    private String name;
}
