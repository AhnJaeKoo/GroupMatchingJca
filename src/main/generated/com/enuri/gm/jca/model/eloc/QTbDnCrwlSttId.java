package com.enuri.gm.jca.model.eloc;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QTbDnCrwlSttId is a Querydsl query type for TbDnCrwlSttId
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QTbDnCrwlSttId extends BeanPath<TbDnCrwlSttId> {

    private static final long serialVersionUID = 851856410L;

    public static final QTbDnCrwlSttId tbDnCrwlSttId = new QTbDnCrwlSttId("tbDnCrwlSttId");

    public final StringPath cateCd = createString("cateCd");

    public final StringPath sttDt = createString("sttDt");

    public QTbDnCrwlSttId(String variable) {
        super(TbDnCrwlSttId.class, forVariable(variable));
    }

    public QTbDnCrwlSttId(Path<? extends TbDnCrwlSttId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTbDnCrwlSttId(PathMetadata metadata) {
        super(TbDnCrwlSttId.class, metadata);
    }

}

