/*
 * Copyright (C) Red Gate Software Ltd 2010-2022
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.flywaydb.core.internal.database.sqlite;


import com.flywaydb.core.api.configuration.Configuration;
import com.flywaydb.core.internal.database.base.BaseDatabaseType;
import com.flywaydb.core.api.ResourceProvider;
import com.flywaydb.core.internal.parser.Parser;
import com.flywaydb.core.internal.parser.ParsingContext;

import java.sql.Types;

public class SQLiteDatabaseType extends BaseDatabaseType {
    @Override
    public String getName() {
        return "SQLite";
    }

    @Override
    public int getNullType() {
        return Types.VARCHAR;
    }



    @Override
    public String getDriverClass(String url, ClassLoader classLoader) {
        if (url.startsWith("jdbc:p6spy:sqlite:") || url.startsWith("jdbc:p6spy:sqldroid:")) {
            return "com.p6spy.engine.spy.P6SpyDriver";
        }
        if (url.startsWith("jdbc:sqldroid:")) {
            return "org.sqldroid.SQLDroidDriver";
        }
        return "org.sqlite.JDBC";
    }

    @Override
    public Parser createParser(Configuration configuration, ResourceProvider resourceProvider, ParsingContext parsingContext) {
        return new SQLiteParser(configuration, parsingContext);
    }
}