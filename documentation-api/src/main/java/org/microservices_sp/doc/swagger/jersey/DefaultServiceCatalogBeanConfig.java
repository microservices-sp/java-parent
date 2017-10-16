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

import java.util.Set;

import org.springframework.stereotype.Component;

import io.swagger.jaxrs.config.BeanConfig;

/**
 * Default representation of an JAX-RS Application.
 * 
 * @author Andre Dias (aloliveiradias@gmail.com)
 * @since 2016-09-30
 * @version 1.0
 */
@Component
public final class DefaultServiceCatalogBeanConfig extends BeanConfig {

    private final BaseJerseySwaggerConfig swaggerResourceConfig;
    
    public DefaultServiceCatalogBeanConfig(final BaseJerseySwaggerConfig swaggerResourceConfig) {
        super();
        this.swaggerResourceConfig = swaggerResourceConfig;
    }

    /**
     * Add complimentary endpoints to the set of classes to be scanned by Swagger.
     * 
     * @return 
     *      Complete set of endpoints to be scanned by Swagger
     */
    @Override
    public Set<Class<?>> classes() {
        Set<Class<?>> c = super.classes(); // All scanned classes, by default
        
        // Specfic module endpoints
        c.addAll(this.swaggerResourceConfig.getEndpoints());

        return c;
    }    
}