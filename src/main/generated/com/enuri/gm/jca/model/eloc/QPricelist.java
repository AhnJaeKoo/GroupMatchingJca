package com.enuri.gm.jca.model.eloc;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QPricelist is a Querydsl query type for Pricelist
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPricelist extends EntityPathBase<Pricelist> {

    private static final long serialVersionUID = 546136239L;

    public static final QPricelist pricelist = new QPricelist("pricelist");

    public final StringPath plGoodsnm = createString("plGoodsnm");

    public final NumberPath<Integer> plModelno = createNumber("plModelno", Integer.class);

    public final NumberPath<Long> plNo = createNumber("plNo", Long.class);

    public final StringPath plSrvflag = createString("plSrvflag");

    public QPricelist(String variable) {
        super(Pricelist.class, forVariable(variable));
    }

    public QPricelist(Path<? extends Pricelist> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPricelist(PathMetadata metadata) {
        super(Pricelist.class, metadata);
    }

}

