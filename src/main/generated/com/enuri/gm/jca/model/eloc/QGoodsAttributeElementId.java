package com.enuri.gm.jca.model.eloc;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QGoodsAttributeElementId is a Querydsl query type for GoodsAttributeElementId
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QGoodsAttributeElementId extends BeanPath<GoodsAttributeElementId> {

    private static final long serialVersionUID = -176696007L;

    public static final QGoodsAttributeElementId goodsAttributeElementId = new QGoodsAttributeElementId("goodsAttributeElementId");

    public final NumberPath<Integer> attributeElementId = createNumber("attributeElementId", Integer.class);

    public final NumberPath<Integer> attributeId = createNumber("attributeId", Integer.class);

    public QGoodsAttributeElementId(String variable) {
        super(GoodsAttributeElementId.class, forVariable(variable));
    }

    public QGoodsAttributeElementId(Path<? extends GoodsAttributeElementId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGoodsAttributeElementId(PathMetadata metadata) {
        super(GoodsAttributeElementId.class, metadata);
    }

}

