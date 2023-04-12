package com.flywaydb.core.api;

import com.flywaydb.core.api.resource.LoadableResource;

/**
 * @author fireflyhoo
 */
public interface ResourceProvider {

    LoadableResource getResource(String name);
}
