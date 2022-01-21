package com.enuri.gm.jca.model.eloc;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTblEnuriMaker is a Querydsl query type for TblEnuriMaker
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTblEnuriMaker extends EntityPathBase<TblEnuriMaker> {

    private static final long serialVersionUID = 1743144839L;

    public static final QTblEnuriMaker tblEnuriMaker = new QTblEnuriMaker("tblEnuriMaker");

    public final ListPath<TblEnuriBrand, QTblEnuriBrand> brands = this.<TblEnuriBrand, QTblEnuriBrand>createList("brands", TblEnuriBrand.class, QTblEnuriBrand.class, PathInits.DIRECT2);

    public final StringPath delYn = createString("delYn");

    public final NumberPath<Integer> makerId = createNumber("makerId", Integer.class);

    public final StringPath makerNm = createString("makerNm");

    public QTblEnuriMaker(String variable) {
        super(TblEnuriMaker.class, forVariable(variable));
    }

    public QTblEnuriMaker(Path<? extends TblEnuriMaker> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTblEnuriMaker(PathMetadata metadata) {
        super(TblEnuriMaker.class, metadata);
    }

}

