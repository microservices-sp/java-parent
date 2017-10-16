/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.microservices_sp.sample_jersey.doc;

import java.util.Date;
import java.util.Set;

import org.microservices_sp.doc.swagger.spring.BaseSpringSwaggerConfig;
import org.microservices_sp.sample_jersey.service.SpringRestService;
import org.springframework.context.annotation.Configuration;

import com.google.common.collect.Sets;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Service API documentation definition for module.
 *
 * @author Andre Dias (andre.dias@summa.com.br)
 * @since 2016-09-17
 * @version 1.0
 */
@Configuration
@EnableSwagger2
public class SpringFoxSwaggerAPIExample extends BaseSpringSwaggerConfig {

    public static final String VERSION = "v1";
    private static final String TITLE = "Swagger + Spring - Services API";
    private static final String DESCRIPTION = "Service API Catalog for Swagger/Spring Example Application";

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    @Override
    public String getVersion() {
        return String.format(VERSION + " (%s)", new Date(System.currentTimeMillis()));
    }

    @Override
    public String getContextPackage() {
        return Package.getPackage("org.microservices_sp.sample_jersey.service").getName();
    }
    
    @Override
    public String getTitle() {
        return TITLE;
    }

    @Override
    public Set<Class<?>> getEndpoints() {
        final Set<Class<?>> endpoints = Sets.newHashSet();
        endpoints.add(SpringRestService.class);
        return endpoints;
    }

    @Override
    public String getDocumentationBasePath() {
        return DEFAULT_JAXRS_APPLICATION_CONTEXT;
    }
}
