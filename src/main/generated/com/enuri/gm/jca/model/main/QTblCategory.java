package com.enuri.gm.jca.model.main;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QTblCategory is a Querydsl query type for TblCategory
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTblCategory extends EntityPathBase<TblCategory> {

    private static final long serialVersionUID = 1622886434L;

    public static final QTblCategory tblCategory = new QTblCategory("tblCategory");

    public final StringPath caCode = createString("caCode");

    public final StringPath cName = createString("cName");

    public final NumberPath<Integer> cSeqno = createNumber("cSeqno", Integer.class);

    public QTblCategory(String variable) {
        super(TblCategory.class, forVariable(variable));
    }

    public QTblCategory(Path<? extends TblCategory> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTblCategory(PathMetadata metadata) {
        super(TblCategory.class, metadata);
    }

}

