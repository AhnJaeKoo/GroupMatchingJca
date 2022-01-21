package com.enuri.gm.jca.model.eloc;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QGoodsAttributeElement is a Querydsl query type for GoodsAttributeElement
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QGoodsAttributeElement extends EntityPathBase<GoodsAttributeElement> {

    private static final long serialVersionUID = -1336495234L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QGoodsAttributeElement goodsAttributeElement = new QGoodsAttributeElement("goodsAttributeElement");

    public final QGoodsAttribute att;

    public final StringPath attributeElement = createString("attributeElement");

    public final StringPath delYn = createString("delYn");

    public final QGoodsAttributeElementId id;

    public QGoodsAttributeElement(String variable) {
        this(GoodsAttributeElement.class, forVariable(variable), INITS);
    }

    public QGoodsAttributeElement(Path<? extends GoodsAttributeElement> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QGoodsAttributeElement(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QGoodsAttributeElement(PathMetadata metadata, PathInits inits) {
        this(GoodsAttributeElement.class, metadata, inits);
    }

    public QGoodsAttributeElement(Class<? extends GoodsAttributeElement> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.att = inits.isInitialized("att") ? new QGoodsAttribute(forProperty("att")) : null;
        this.id = inits.isInitialized("id") ? new QGoodsAttributeElementId(forProperty("id")) : null;
    }

}

