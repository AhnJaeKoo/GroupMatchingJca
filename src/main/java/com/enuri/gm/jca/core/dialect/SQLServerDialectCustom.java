package com.enuri.gm.jca.core.dialect;

import org.hibernate.dialect.SQLServer2012Dialect;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.type.StandardBasicTypes;

public class SQLServerDialectCustom extends SQLServer2012Dialect {
	public SQLServerDialectCustom() {
		registerFunction( "udf_dn_crwl_spec", new StandardSQLFunction( "dbo.udf_dn_crwl_spec", StandardBasicTypes.STRING ) );
	}
}
