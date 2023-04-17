package com.flywaydb.core;

import com.flywaydb.core.api.configuration.Configuration;
import com.flywaydb.core.internal.database.DatabaseType;
import com.flywaydb.core.internal.database.sqlite.SQLiteDatabaseType;
import com.flywaydb.core.internal.parser.ParsingContext;
import com.flywaydb.core.internal.sqlscript.SqlScript;
import com.flywaydb.core.internal.sqlscript.SqlScriptFactory;
import com.flywaydb.core.internal.sqlscript.SqlStatementIterator;
import com.flywaydb.core.internal.resource.NoopResourceProvider;
import com.flywaydb.core.internal.resource.StringResource;

public class Test {
    public static void main(String[] args) {
        String sql = "" +
                "select 1; " +
                "insert into xxx values(1,1,1,1,1);" +
                " select * from user ";


        final DatabaseType databaseType = new SQLiteDatabaseType();
        Configuration configuration = new Configuration();
        SqlScriptFactory sqlScriptFactory = databaseType.createSqlScriptFactory(configuration, new ParsingContext());
        SqlScript sqlScript = sqlScriptFactory.createSqlScript(new StringResource(sql),true,NoopResourceProvider.INSTANCE);
        SqlStatementIterator stm = sqlScript.getSqlStatements();
        stm.forEachRemaining((v) -> {
            System.out.println(v.getSql());
            System.out.println(v.canExecuteInTransaction());
            System.out.println("------------------------------------------------------------");
        });

    }
}
