package com.hyz.mall.nosql.mongodb.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.Date;

/**
 * 用户商品浏览历史记录
 * @author hyz
 * @version 1.0
 * @date 2020/5/15 15:24
 * Document 表示映射到Mongodb文档上的领域对象
 */
@Document
@Data
public class MemberReadHistory {
    @Id
    private String id;
    /**
     * Indexed标示某个字段为Mongodb的索引字段
     */
    @Indexed
    private Long memberId;
    private String memberNickname;
    private String memberIcon;
    @Indexed
    private Long productId;
    private String productName;
    private String productPic;
    private String productSubTitle;
    private String productPrice;
    private Date createTime;



}
