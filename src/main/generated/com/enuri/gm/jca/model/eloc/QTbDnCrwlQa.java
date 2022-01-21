package com.enuri.gm.jca.model.eloc;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTbDnCrwlQa is a Querydsl query type for TbDnCrwlQa
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTbDnCrwlQa extends EntityPathBase<TbDnCrwlQa> {

    private static final long serialVersionUID = -2035507164L;

    public static final QTbDnCrwlQa tbDnCrwlQa = new QTbDnCrwlQa("tbDnCrwlQa");

    public final NumberPath<Integer> brndId = createNumber("brndId", Integer.class);

    public final StringPath brndNm = createString("brndNm");

    public final StringPath btcId = createString("btcId");

    public final StringPath cateCd = createString("cateCd");

    public final StringPath chgCateCd = createString("chgCateCd");

    public final StringPath dnAtr = createString("dnAtr");

    public final StringPath dnCatlId = createString("dnCatlId");

    public final StringPath dnCndNm = createString("dnCndNm");

    public final StringPath dnCpUnt = createString("dnCpUnt");

    public final StringPath dnCpVlu = createString("dnCpVlu");

    public final StringPath dnGrCatlId = createString("dnGrCatlId");

    public final NumberPath<Integer> dnQnt = createNumber("dnQnt", Integer.class);

    public final NumberPath<Integer> dnRnk = createNumber("dnRnk", Integer.class);

    public final NumberPath<Float> dnVolume = createNumber("dnVolume", Float.class);

    public final NumberPath<Integer> mkrId = createNumber("mkrId", Integer.class);

    public final StringPath mkrNm = createString("mkrNm");

    public final StringPath modelNm = createString("modelNm");

    public final SetPath<TbDnCrwlQaModel, QTbDnCrwlQaModel> models = this.<TbDnCrwlQaModel, QTbDnCrwlQaModel>createSet("models", TbDnCrwlQaModel.class, QTbDnCrwlQaModel.class, PathInits.DIRECT2);

    public final StringPath nwYn = createString("nwYn");

    public final SetPath<TbDnCrwlQaPl, QTbDnCrwlQaPl> pricelists = this.<TbDnCrwlQaPl, QTbDnCrwlQaPl>createSet("pricelists", TbDnCrwlQaPl.class, QTbDnCrwlQaPl.class, PathInits.DIRECT2);

    public final NumberPath<Long> qaId = createNumber("qaId", Long.class);

    public final StringPath regDate = createString("regDate");

    public final DateTimePath<java.sql.Timestamp> updDtm = createDateTime("updDtm", java.sql.Timestamp.class);

    public final StringPath updEmpId = createString("updEmpId");

    public final StringPath useYn = createString("useYn");

    public QTbDnCrwlQa(String variable) {
        super(TbDnCrwlQa.class, forVariable(variable));
    }

    public QTbDnCrwlQa(Path<? extends TbDnCrwlQa> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTbDnCrwlQa(PathMetadata metadata) {
        super(TbDnCrwlQa.class, metadata);
    }

}

