package com.enuri.gm.jca.model.eloc;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QGoodsCategoryAttributeId is a Querydsl query type for GoodsCategoryAttributeId
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QGoodsCategoryAttributeId extends BeanPath<GoodsCategoryAttributeId> {

    private static final long serialVersionUID = 1655180667L;

    public static final QGoodsCategoryAttributeId goodsCategoryAttributeId = new QGoodsCategoryAttributeId("goodsCategoryAttributeId");

    public final NumberPath<Integer> attributeId = createNumber("attributeId", Integer.class);

    public final StringPath category = createString("category");

    public QGoodsCategoryAttributeId(String variable) {
        super(GoodsCategoryAttributeId.class, forVariable(variable));
    }

    public QGoodsCategoryAttributeId(Path<? extends GoodsCategoryAttributeId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGoodsCategoryAttributeId(PathMetadata metadata) {
        super(GoodsCategoryAttributeId.class, metadata);
    }

}

