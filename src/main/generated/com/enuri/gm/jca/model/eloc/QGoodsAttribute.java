package com.enuri.gm.jca.model.eloc;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QGoodsAttribute is a Querydsl query type for GoodsAttribute
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QGoodsAttribute extends EntityPathBase<GoodsAttribute> {

    private static final long serialVersionUID = 1422726558L;

    public static final QGoodsAttribute goodsAttribute = new QGoodsAttribute("goodsAttribute");

    public final SetPath<GoodsAttributeElement, QGoodsAttributeElement> attEls = this.<GoodsAttributeElement, QGoodsAttributeElement>createSet("attEls", GoodsAttributeElement.class, QGoodsAttributeElement.class, PathInits.DIRECT2);

    public final NumberPath<Integer> attributeId = createNumber("attributeId", Integer.class);

    public final SetPath<GoodsCategoryAttribute, QGoodsCategoryAttribute> cateAtts = this.<GoodsCategoryAttribute, QGoodsCategoryAttribute>createSet("cateAtts", GoodsCategoryAttribute.class, QGoodsCategoryAttribute.class, PathInits.DIRECT2);

    public final StringPath delYn = createString("delYn");

    public final StringPath galleryAttributeNm = createString("galleryAttributeNm");

    public final StringPath manageAttributeNm = createString("manageAttributeNm");

    public final StringPath useClassCode = createString("useClassCode");

    public QGoodsAttribute(String variable) {
        super(GoodsAttribute.class, forVariable(variable));
    }

    public QGoodsAttribute(Path<? extends GoodsAttribute> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGoodsAttribute(PathMetadata metadata) {
        super(GoodsAttribute.class, metadata);
    }

}

