package com.enuri.gm.jca.model.eloc;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QTbDnCrwlAtrEnrId is a Querydsl query type for TbDnCrwlAtrEnrId
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QTbDnCrwlAtrEnrId extends BeanPath<TbDnCrwlAtrEnrId> {

    private static final long serialVersionUID = 1621168025L;

    public static final QTbDnCrwlAtrEnrId tbDnCrwlAtrEnrId = new QTbDnCrwlAtrEnrId("tbDnCrwlAtrEnrId");

    public final NumberPath<Integer> dnAtrId = createNumber("dnAtrId", Integer.class);

    public final NumberPath<Integer> enrAtrDtlId = createNumber("enrAtrDtlId", Integer.class);

    public final NumberPath<Integer> enrAtrId = createNumber("enrAtrId", Integer.class);

    public QTbDnCrwlAtrEnrId(String variable) {
        super(TbDnCrwlAtrEnrId.class, forVariable(variable));
    }

    public QTbDnCrwlAtrEnrId(Path<? extends TbDnCrwlAtrEnrId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTbDnCrwlAtrEnrId(PathMetadata metadata) {
        super(TbDnCrwlAtrEnrId.class, metadata);
    }

}

