/**
 * Copyright (C) 2017 Microservices SP.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * 
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Andre Dias - initial API and implementation
 */
package org.microservices_sp.doc.swagger.jersey;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.wadl.internal.WadlResource;
import org.microservices_sp.doc.swagger.AbstractServiceCatalogDefinition;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.jaxrs.base.JsonMappingExceptionMapper;
import com.fasterxml.jackson.jaxrs.base.JsonParseExceptionMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;

/**
 * General abstraction to build Services APIs, with Swagger and JAX-RS.
 * 
 * @author Andre Dias (aloliveiradias@gmail.com)
 * @since 2016-09-28
 * @version 1.0
 */
@Configuration
public abstract class BaseJerseySwaggerConfig extends ResourceConfig implements AbstractServiceCatalogDefinition {

    /**
     * Registers all needed classes for JAX-RS, WADL and Swagger to define a
     * service catalog.
     */
    public BaseJerseySwaggerConfig() {
        super();

        // JAX-RS
        super.register(JsonMappingExceptionMapper.class);
        super.register(JsonParseExceptionMapper.class);
        super.register(JacksonJaxbJsonProvider.class);
        super.register(JacksonJsonProvider.class);

        // SWAGGER
        super.register(ApiListingResource.class);
        super.register(SwaggerSerializers.class);

        // WADL
        super.register(WadlResource.class);

        // MODULE ENDPOINTS
        this.registerEndpoints();

        // CONFIGURE SWAGGER
        this.configureSwagger();
    }

    /**
     * Register all endpoints for provided by the module.
     */
    private void registerEndpoints() {
        super.registerClasses(this.getEndpoints());
    }

    /**
     * Configure Swagger API for all endpoints. Adds t
     *
     */
    private void configureSwagger() {

        final DefaultServiceCatalogBeanConfig beanConfig = new DefaultServiceCatalogBeanConfig(this);

        // FIXED OPTIONS
        beanConfig.setSchemes(AbstractServiceCatalogDefinition.DEFAULT_DOCUMENTATION_PROTOCOLS);
        beanConfig.setBasePath(this.getDocumentationBasePath());

        // CHILD INFORMATION
        beanConfig.setTitle(this.getTitle());
        beanConfig.setDescription(this.getDescription());
        beanConfig.setVersion(this.getVersion());
        beanConfig.setResourcePackage(this.getContextPackage());

        // OTHER OPTIONS
        beanConfig.setScan(true);
        beanConfig.setPrettyPrint(true);
    }
}
