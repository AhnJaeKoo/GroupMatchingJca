package com.enuri.gm.jca.model.eloc;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTbDnCrwlStt is a Querydsl query type for TbDnCrwlStt
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTbDnCrwlStt extends EntityPathBase<TbDnCrwlStt> {

    private static final long serialVersionUID = 1323789983L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTbDnCrwlStt tbDnCrwlStt = new QTbDnCrwlStt("tbDnCrwlStt");

    public final NumberPath<Integer> addModelCnt = createNumber("addModelCnt", Integer.class);

    public final NumberPath<Integer> addModelMtcCnt = createNumber("addModelMtcCnt", Integer.class);

    public final NumberPath<Integer> colcQaCnt = createNumber("colcQaCnt", Integer.class);

    public final NumberPath<Integer> colcQaMtcCnt = createNumber("colcQaMtcCnt", Integer.class);

    public final QTbDnCrwlSttId id;

    public final NumberPath<Integer> updModelCnt = createNumber("updModelCnt", Integer.class);

    public final NumberPath<Integer> updModelMtcCnt = createNumber("updModelMtcCnt", Integer.class);

    public final NumberPath<Integer> uprcsQaCnt = createNumber("uprcsQaCnt", Integer.class);

    public final NumberPath<Integer> uprcsQaMtcCnt = createNumber("uprcsQaMtcCnt", Integer.class);

    public QTbDnCrwlStt(String variable) {
        this(TbDnCrwlStt.class, forVariable(variable), INITS);
    }

    public QTbDnCrwlStt(Path<? extends TbDnCrwlStt> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTbDnCrwlStt(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTbDnCrwlStt(PathMetadata metadata, PathInits inits) {
        this(TbDnCrwlStt.class, metadata, inits);
    }

    public QTbDnCrwlStt(Class<? extends TbDnCrwlStt> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.id = inits.isInitialized("id") ? new QTbDnCrwlSttId(forProperty("id")) : null;
    }

}

