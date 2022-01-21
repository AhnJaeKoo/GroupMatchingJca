package com.enuri.gm.jca.model.eloc;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QTblEnuriBrandId is a Querydsl query type for TblEnuriBrandId
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QTblEnuriBrandId extends BeanPath<TblEnuriBrandId> {

    private static final long serialVersionUID = -569943675L;

    public static final QTblEnuriBrandId tblEnuriBrandId = new QTblEnuriBrandId("tblEnuriBrandId");

    public final NumberPath<Integer> brandId = createNumber("brandId", Integer.class);

    public final NumberPath<Integer> makerId = createNumber("makerId", Integer.class);

    public QTblEnuriBrandId(String variable) {
        super(TblEnuriBrandId.class, forVariable(variable));
    }

    public QTblEnuriBrandId(Path<? extends TblEnuriBrandId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTblEnuriBrandId(PathMetadata metadata) {
        super(TblEnuriBrandId.class, metadata);
    }

}

