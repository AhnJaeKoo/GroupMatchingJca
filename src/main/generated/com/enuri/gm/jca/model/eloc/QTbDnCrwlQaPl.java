package com.enuri.gm.jca.model.eloc;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTbDnCrwlQaPl is a Querydsl query type for TbDnCrwlQaPl
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTbDnCrwlQaPl extends EntityPathBase<TbDnCrwlQaPl> {

    private static final long serialVersionUID = -1912262336L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTbDnCrwlQaPl tbDnCrwlQaPl = new QTbDnCrwlQaPl("tbDnCrwlQaPl");

    public final NumberPath<Long> plNo = createNumber("plNo", Long.class);

    public final QTbDnCrwlQa qa;

    public final NumberPath<Long> qaId = createNumber("qaId", Long.class);

    public final DateTimePath<java.sql.Timestamp> updDtm = createDateTime("updDtm", java.sql.Timestamp.class);

    public final StringPath updEmpId = createString("updEmpId");

    public final StringPath useYn = createString("useYn");

    public QTbDnCrwlQaPl(String variable) {
        this(TbDnCrwlQaPl.class, forVariable(variable), INITS);
    }

    public QTbDnCrwlQaPl(Path<? extends TbDnCrwlQaPl> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTbDnCrwlQaPl(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTbDnCrwlQaPl(PathMetadata metadata, PathInits inits) {
        this(TbDnCrwlQaPl.class, metadata, inits);
    }

    public QTbDnCrwlQaPl(Class<? extends TbDnCrwlQaPl> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.qa = inits.isInitialized("qa") ? new QTbDnCrwlQa(forProperty("qa")) : null;
    }

}

