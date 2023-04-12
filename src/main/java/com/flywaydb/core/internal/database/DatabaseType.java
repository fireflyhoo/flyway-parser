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
package com.flywaydb.core.internal.database;

import com.flywaydb.core.internal.sqlscript.SqlScriptFactory;
import com.flywaydb.core.api.ResourceProvider;
import com.flywaydb.core.api.configuration.Configuration;
import com.flywaydb.core.extensibility.Plugin;
import com.flywaydb.core.internal.parser.Parser;
import com.flywaydb.core.internal.parser.ParsingContext;

public interface DatabaseType extends Plugin {
    /**
     * @return The human-readable name for this database type.
     */
    String getName();

    /**
     * @return The JDBC type used to represent {@code null} in prepared statements.
     */
    int getNullType();


    /**
     * Get the driver class used to handle this JDBC url.
     * This will only be called if {@code matchesJDBCUrl} previously returned {@code true}.
     *
     * @param url         The JDBC url.
     * @param classLoader The classLoader to check for driver classes.
     * @return The full driver class name to be instantiated to handle this url.
     */
    String getDriverClass(String url, ClassLoader classLoader);

    /**
     * Initializes the Parser used by this Database Type.
     *
     * @param configuration The Flyway configuration.
     * @return The Parser.
     */
    Parser createParser(
            Configuration configuration
            , ResourceProvider resourceProvider
            , ParsingContext parsingContext
    );

    /**
     * Initializes the SqlScriptFactory used by this Database Type.
     *
     * @param configuration The Flyway configuration.
     * @return The SqlScriptFactory.
     */
    SqlScriptFactory createSqlScriptFactory(
            final Configuration configuration,
            final ParsingContext parsingContext);

}


