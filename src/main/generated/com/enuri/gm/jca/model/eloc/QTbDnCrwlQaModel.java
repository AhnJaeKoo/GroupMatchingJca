package com.enuri.gm.jca.model.eloc;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTbDnCrwlQaModel is a Querydsl query type for TbDnCrwlQaModel
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTbDnCrwlQaModel extends EntityPathBase<TbDnCrwlQaModel> {

    private static final long serialVersionUID = 236380517L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTbDnCrwlQaModel tbDnCrwlQaModel = new QTbDnCrwlQaModel("tbDnCrwlQaModel");

    public final StringPath modelNm = createString("modelNm");

    public final NumberPath<Integer> modelNo = createNumber("modelNo", Integer.class);

    public final QTbDnCrwlQa qa;

    public final NumberPath<Long> qaId = createNumber("qaId", Long.class);

    public QTbDnCrwlQaModel(String variable) {
        this(TbDnCrwlQaModel.class, forVariable(variable), INITS);
    }

    public QTbDnCrwlQaModel(Path<? extends TbDnCrwlQaModel> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTbDnCrwlQaModel(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTbDnCrwlQaModel(PathMetadata metadata, PathInits inits) {
        this(TbDnCrwlQaModel.class, metadata, inits);
    }

    public QTbDnCrwlQaModel(Class<? extends TbDnCrwlQaModel> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.qa = inits.isInitialized("qa") ? new QTbDnCrwlQa(forProperty("qa")) : null;
    }

}

