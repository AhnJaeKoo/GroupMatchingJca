package com.enuri.gm.jca.model.eloc;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTbDnCrwlAtrEnr is a Querydsl query type for TbDnCrwlAtrEnr
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTbDnCrwlAtrEnr extends EntityPathBase<TbDnCrwlAtrEnr> {

    private static final long serialVersionUID = 122357214L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTbDnCrwlAtrEnr tbDnCrwlAtrEnr = new QTbDnCrwlAtrEnr("tbDnCrwlAtrEnr");

    public final QTbDnCrwlAtr att;

    public final StringPath enrAtrDtlRngVlu = createString("enrAtrDtlRngVlu");

    public final QTbDnCrwlAtrEnrId id;

    public final DateTimePath<java.sql.Timestamp> insDtm = createDateTime("insDtm", java.sql.Timestamp.class);

    public final DateTimePath<java.sql.Timestamp> updDtm = createDateTime("updDtm", java.sql.Timestamp.class);

    public final StringPath updEmpId = createString("updEmpId");

    public QTbDnCrwlAtrEnr(String variable) {
        this(TbDnCrwlAtrEnr.class, forVariable(variable), INITS);
    }

    public QTbDnCrwlAtrEnr(Path<? extends TbDnCrwlAtrEnr> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTbDnCrwlAtrEnr(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTbDnCrwlAtrEnr(PathMetadata metadata, PathInits inits) {
        this(TbDnCrwlAtrEnr.class, metadata, inits);
    }

    public QTbDnCrwlAtrEnr(Class<? extends TbDnCrwlAtrEnr> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.att = inits.isInitialized("att") ? new QTbDnCrwlAtr(forProperty("att")) : null;
        this.id = inits.isInitialized("id") ? new QTbDnCrwlAtrEnrId(forProperty("id")) : null;
    }

}

