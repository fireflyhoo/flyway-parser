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
package com.flywaydb.core.internal.parser;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;


public class ParsingContext {
    private static final String DEFAULT_SCHEMA_PLACEHOLDER = "defaultSchema";
    private static final String USER_PLACEHOLDER = "user";
    private static final String DATABASE_PLACEHOLDER = "database";
    private static final String TIMESTAMP_PLACEHOLDER = "timestamp";
    private static final String FILENAME_PLACEHOLDER = "filename";
    private static final String WORKING_DIRECTORY_PLACEHOLDER = "workingDirectory";
    private static final String TABLE_PLACEHOLDER = "table";

    @Getter
    private final Map<String, String> placeholders = new HashMap<>();

}