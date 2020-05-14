package com.hyz.mall.nosql.elasticsearch.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 搜索中的商品信息
 * @author hyz
 * @version 1.0
 * @date 2020/5/14 13:38
 * Document注解作用在类上，标记实体类为文档对象
 * indexName：对应索引库名称
 * type：对应在索引库中的类型
 * shards：分片数
 * replicas：副本数
 *
 * Field作用在成员变量，标记为文档的字段，并制定映射属性
 * Id：作用在成员变量，标记一个字段为id主键；一般id字段或是域不需要存储也不需要分词
 *
 * type：字段的类型，取值是枚举，FieldType
 * index：是否索引，布尔值类型，默认是true
 * store：是否存储，布尔值类型，默认值是false
 * analyzer：分词器名称
 */
@Data
@Document(indexName = "pms",type = "product",shards = 1,replicas = 0)
public class EsProduct implements Serializable {

    /**
     * Field(type = FieldType.Keyword)和 @Field(type = FieldType.Text)区别
     * 在早期elasticsearch5.x之前的版本存储字符串只有string字段；
     * 但是在elasticsearch5.x之后的版本存储了Keyword和Text，都是存储字符串的。
     * FieldType.Keyword存储字符串数据时，不会建立索引；
     * 而FieldType.Text在存储字符串数据的时候，会自动建立索引，也会占用部分空间资源
     *
     */
    private static final long serialVersionUID = 1L;
    @Id
    private Long id;
    @Field(type = FieldType.Keyword)
    private String productSn;
    private Long brandId;
    @Field(type = FieldType.Keyword)
    private String brandName;
    private Long productCategoryId;
    @Field(type = FieldType.Keyword)
    private String productCategoryName;
    private String pic;

    /**
     * Field(analyzer="ik_max_word",searchAnalyzer="ik_max_word")表示是否分词，
     * 如果是分词就会按照分词的单词搜索，如果不是分词就按照整体搜索。
     */
    @Field(analyzer = "ik_max_word",type = FieldType.Text)
    private String name;
    @Field(analyzer = "ik_max_word",type = FieldType.Text)
    private String subTitle;
    @Field(analyzer = "ik_max_word",type = FieldType.Text)
    private String keywords;
    private BigDecimal  price;
    private Integer sale;
    private Integer newStatus;
    private Integer recommandStatus;
    private Integer stock;
    private Integer promotionType;
    private Integer sort;
    @Field(type = FieldType.Nested)
    private List<EsProductAttributeValue> attrValueList;


}
