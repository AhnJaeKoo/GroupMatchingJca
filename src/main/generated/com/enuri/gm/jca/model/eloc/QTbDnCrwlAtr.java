package com.enuri.gm.jca.model.eloc;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTbDnCrwlAtr is a Querydsl query type for TbDnCrwlAtr
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTbDnCrwlAtr extends EntityPathBase<TbDnCrwlAtr> {

    private static final long serialVersionUID = 1323772683L;

    public static final QTbDnCrwlAtr tbDnCrwlAtr = new QTbDnCrwlAtr("tbDnCrwlAtr");

    public final SetPath<TbDnCrwlAtrEnr, QTbDnCrwlAtrEnr> attEnrs = this.<TbDnCrwlAtrEnr, QTbDnCrwlAtrEnr>createSet("attEnrs", TbDnCrwlAtrEnr.class, QTbDnCrwlAtrEnr.class, PathInits.DIRECT2);

    public final StringPath cateCd = createString("cateCd");

    public final StringPath dnAtr = createString("dnAtr");

    public final NumberPath<Integer> dnAtrId = createNumber("dnAtrId", Integer.class);

    public final StringPath dnAtrNm = createString("dnAtrNm");

    public final DateTimePath<java.sql.Timestamp> insDtm = createDateTime("insDtm", java.sql.Timestamp.class);

    public final StringPath insEmpId = createString("insEmpId");

    public final DateTimePath<java.sql.Timestamp> updDtm = createDateTime("updDtm", java.sql.Timestamp.class);

    public final StringPath updEmpId = createString("updEmpId");

    public final StringPath useYn = createString("useYn");

    public QTbDnCrwlAtr(String variable) {
        super(TbDnCrwlAtr.class, forVariable(variable));
    }

    public QTbDnCrwlAtr(Path<? extends TbDnCrwlAtr> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTbDnCrwlAtr(PathMetadata metadata) {
        super(TbDnCrwlAtr.class, metadata);
    }

}

