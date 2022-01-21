package com.enuri.gm.jca.model.eloc;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QGoodsCategoryAttribute is a Querydsl query type for GoodsCategoryAttribute
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QGoodsCategoryAttribute extends EntityPathBase<GoodsCategoryAttribute> {

    private static final long serialVersionUID = 1230771264L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QGoodsCategoryAttribute goodsCategoryAttribute = new QGoodsCategoryAttribute("goodsCategoryAttribute");

    public final QGoodsAttribute att;

    public final NumberPath<Integer> displayOrder = createNumber("displayOrder", Integer.class);

    public final QGoodsCategoryAttributeId id;

    public final NumberPath<Integer> tagOrder = createNumber("tagOrder", Integer.class);

    public QGoodsCategoryAttribute(String variable) {
        this(GoodsCategoryAttribute.class, forVariable(variable), INITS);
    }

    public QGoodsCategoryAttribute(Path<? extends GoodsCategoryAttribute> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QGoodsCategoryAttribute(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QGoodsCategoryAttribute(PathMetadata metadata, PathInits inits) {
        this(GoodsCategoryAttribute.class, metadata, inits);
    }

    public QGoodsCategoryAttribute(Class<? extends GoodsCategoryAttribute> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.att = inits.isInitialized("att") ? new QGoodsAttribute(forProperty("att")) : null;
        this.id = inits.isInitialized("id") ? new QGoodsCategoryAttributeId(forProperty("id")) : null;
    }

}

