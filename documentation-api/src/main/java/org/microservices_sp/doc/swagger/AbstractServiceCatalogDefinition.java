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
package org.microservices_sp.doc.swagger;

import java.util.Set;

/**
 * Markup interface that define needed operations in order to generate a Service Catalog, using Swagger.
 * 
 * @author Andre Dias (aloliveiradias@gmail.com)
 * @since 2016-09-17
 * @version 1.0
 */
public interface AbstractServiceCatalogDefinition {

    public static final String DEFAULT_JAXRS_APPLICATION_CONTEXT = "/";
    
    public static final String[] DEFAULT_DOCUMENTATION_PROTOCOLS = new String[]{"http", "https"}; // Used by Swagger API Documentation

    /**
     * Return the Specification Title.
     * @return 
     */
    public String getTitle();
    
    /**
     * Return the Specification Description.
     * @return 
     */
    public String getDescription();

    /**
     * Return the Specification Version.
     * @return 
     */
    public String getVersion();

    /**
     * Return the Base Context Package to load endpoints.
     * @return 
     */
    public String getContextPackage();
    
    /**
     * Return the Specification Endpoint classes to be mapped by the Catalog mechanism (using Swagger API and annotations).
     * @return 
     */
    public Set<Class<?>> getEndpoints();
    
    /**
     * Return the documentation base path to access the Swagger JSON format 
     * (Tip: should be the same for both Swagger and and WADL).
     * @return 
     */
    public String getDocumentationBasePath();
}