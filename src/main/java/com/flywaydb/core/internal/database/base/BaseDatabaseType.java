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
package com.flywaydb.core.internal.database.base;

import com.flywaydb.core.api.ResourceProvider;
import com.flywaydb.core.api.configuration.Configuration;
import com.flywaydb.core.api.resource.LoadableResource;
import com.flywaydb.core.internal.database.DatabaseType;
import com.flywaydb.core.internal.parser.Parser;
import com.flywaydb.core.internal.parser.ParsingContext;
import com.flywaydb.core.internal.sqlscript.ParserSqlScript;
import com.flywaydb.core.internal.sqlscript.SqlScriptFactory;

import java.util.regex.Pattern;


public abstract class BaseDatabaseType implements DatabaseType {
    /**
     * This is useful for databases that allow setting this in order to easily correlate individual application with
     * database connections.
     */
    protected static final String APPLICATION_NAME = "Flyway by Redgate";
    // Don't grab semicolons and ampersands - they have special meaning in URLs
    private static final Pattern defaultJdbcCredentialsPattern = Pattern.compile("password=([^;&]*).*", Pattern.CASE_INSENSITIVE);

    /**
     * Gets a regex that identifies credentials in the JDBC URL, where they conform to the default URL pattern.
     * The first captured group represents the password text.
     */
    public static Pattern getDefaultJDBCCredentialsPattern() {
        return defaultJdbcCredentialsPattern;
    }

    /**
     * @return The human-readable name for this database.
     */
    @Override
    public abstract String getName();

    @Override
    public String toString() {
        return getName();
    }

    /**
     * @return The JDBC type used to represent {@code null} in prepared statements.
     */
    @Override
    public abstract int getNullType();


    /**
     * @return The full driver class name to be instantiated to handle this url.
     */
    @Override
    public abstract String getDriverClass(String url, ClassLoader classLoader);


    @Override
    public abstract Parser createParser(Configuration configuration, ResourceProvider resourceProvider, ParsingContext parsingContext);

    @Override
    public SqlScriptFactory createSqlScriptFactory(final Configuration configuration, final ParsingContext parsingContext) {
        return (resource, mixed, resourceProvider) -> new ParserSqlScript(createParser(configuration, resourceProvider, parsingContext),
                resource, getMetadataResource(resourceProvider, resource), mixed);
    }


    public LoadableResource getMetadataResource(ResourceProvider resourceProvider, LoadableResource resource) {
        //TODO XXX
        return null;
    }


}