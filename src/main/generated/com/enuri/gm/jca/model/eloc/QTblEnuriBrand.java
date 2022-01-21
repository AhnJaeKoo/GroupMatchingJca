package com.enuri.gm.jca.model.eloc;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTblEnuriBrand is a Querydsl query type for TblEnuriBrand
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTblEnuriBrand extends EntityPathBase<TblEnuriBrand> {

    private static final long serialVersionUID = 1733483210L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTblEnuriBrand tblEnuriBrand = new QTblEnuriBrand("tblEnuriBrand");

    public final StringPath brandNm = createString("brandNm");

    public final StringPath delYn = createString("delYn");

    public final QTblEnuriBrandId id;

    public final QTblEnuriMaker maker;

    public QTblEnuriBrand(String variable) {
        this(TblEnuriBrand.class, forVariable(variable), INITS);
    }

    public QTblEnuriBrand(Path<? extends TblEnuriBrand> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTblEnuriBrand(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTblEnuriBrand(PathMetadata metadata, PathInits inits) {
        this(TblEnuriBrand.class, metadata, inits);
    }

    public QTblEnuriBrand(Class<? extends TblEnuriBrand> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.id = inits.isInitialized("id") ? new QTblEnuriBrandId(forProperty("id")) : null;
        this.maker = inits.isInitialized("maker") ? new QTblEnuriMaker(forProperty("maker")) : null;
    }

}

