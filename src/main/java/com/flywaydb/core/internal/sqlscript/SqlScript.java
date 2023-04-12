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
package com.flywaydb.core.internal.sqlscript;

import com.flywaydb.core.api.resource.LoadableResource;

import java.util.Collection;

/**
 * SQL script containing a series of statements terminated by a delimiter (eg: ;).
 * Single-line (--) and multi-line (/* * /) comments are stripped and ignored.
 */
public interface SqlScript extends Comparable<SqlScript> {
    /**
     * @return The sql statements contained in this script.
     */
    SqlStatementIterator getSqlStatements();

    /**
     * @return The number of sql statements contained in this script.
     */
    int getSqlStatementCount();

    /**
     * @return The external SQL scripts referenced directly or indirectly by this SQL script.
     */
    Collection<SqlScript> getReferencedSqlScripts();

    /**
     * @return The resource containing the statements.
     */
    LoadableResource getResource();




    /**
     * Validates this SQL script.
     */
    void validate();
}