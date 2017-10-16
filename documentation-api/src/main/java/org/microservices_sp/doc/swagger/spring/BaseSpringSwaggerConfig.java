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
package org.microservices_sp.doc.swagger.spring;

import java.util.Calendar;
import java.util.List;
import java.util.Set;

import org.microservices_sp.doc.swagger.AbstractServiceCatalogDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import lombok.NonNull;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * General abstraction to build Services APIs, with Swagger and Spring Framework.
 * 
 * @author Andre Dias (aloliveiradias@gmail.com)
 * @since 2016-09-17
 * @version 1.0
 * 
 */
@EnableSwagger2
public abstract class BaseSpringSwaggerConfig implements AbstractServiceCatalogDefinition {

    private static final Contact MAIN_CONTACT = new Contact("Microservices SP Meetup Group", "https://www.meetup.com/microservices-sp", "");
    
    private static final Set<String> PROTOCOLS = Sets.newHashSet(DEFAULT_DOCUMENTATION_PROTOCOLS);
    
    @Bean
    public Docket api() {

        final Docket api = new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(this.getContextPackage())) //.getName())) //.apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .protocols(PROTOCOLS)
                .apiInfo(this.apiInfo());

        return this.addResponseMessages(api);
    }

    /**
     * Add default response messages to all HTTP verbs used by endpoint
     * operations
     *
     * @param docket Current @see Docket instance
     *
     * @return Enriched @see Docket instance
     */
    protected Docket addResponseMessages(@NonNull final Docket docket) {

        docket.useDefaultResponseMessages(false);

        final List<ResponseMessage> responses = this.buildDefaultResponseMessages();

        /**
         * Associate all response messages with all HTTP Verbs, by default.
         */
        for (RequestMethod method : RequestMethod.values()) {
            docket.globalResponseMessage(method, responses);
        }

        return docket;
    }

    @Override
    public Set<Class<?>> getEndpoints() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * DEFAULT MESSAGES FOR ALL HTTP METHODS COMPLIANT WITH HTTP CODES
     *
     * @return List of default response messages
     */
    private List<ResponseMessage> buildDefaultResponseMessages() {
        final List<ResponseMessage> responses = Lists.newArrayList();

        /**
         * SUCCESSFUL MESSAGES
         */
        // 200
        responses.add(this.buildMessageForHTTPStatus(HttpStatus.OK));

        // 201
        responses.add(this.buildMessageForHTTPStatus(HttpStatus.CREATED, "Resource was created"));

        /**
         * CLIENT ERRORS FIXME ADD ERROR RESPONSE MODEL /*.responseModel(new
         * ModelRef("pims_payload_client_error"))
         */
        // 400
        responses.add(this.buildMessageForHTTPStatus(HttpStatus.BAD_REQUEST));

        // 401
        responses.add(this.buildMessageForHTTPStatus(HttpStatus.UNAUTHORIZED, "Requires user authentication to access this resource.\nAccess is denied due to request have no authentication"));

        // 403
        responses.add(this.buildMessageForHTTPStatus(HttpStatus.FORBIDDEN, "Rights are not sufficient to access this resource.\nAccess denied"));

        // 404
        responses.add(this.buildMessageForHTTPStatus(HttpStatus.NOT_FOUND, "Resource requested does not exist.\nResource not found"));

        // 405
        responses.add(this.buildMessageForHTTPStatus(HttpStatus.METHOD_NOT_ALLOWED, "Method is not supported on this resource or user does not have the permission.\nResource does not support method.\nResource method not implemented yet"));

        /**
         * INTERNAL ERRORS FIXME ADD ERROR RESPONSE MODEL /*.responseModel(new
         * ModelRef("pims_payload_internal_error")).
         */
        // 500
        responses.add(this.buildMessageForHTTPStatus(HttpStatus.INTERNAL_SERVER_ERROR, "The request seems right, but a problem occurred on the server.\nResource internal error"));

        return responses;
    }

    /**
     * Build Message For HTTP Status.
     *
     * @param httpStatus
     * @return
     */
    private ResponseMessage buildMessageForHTTPStatus(@NonNull final HttpStatus httpStatus) {
        return this.buildMessageForHTTPStatus(httpStatus, httpStatus.getReasonPhrase());
    }

    /**
     * Build Message For HTTP Status, customized
     *
     * @param httpStatus
     * @param customMessage
     * @return
     */
    private ResponseMessage buildMessageForHTTPStatus(@NonNull final HttpStatus httpStatus, @NonNull final String customMessage) {
        return new ResponseMessageBuilder()
                .code(httpStatus.value())
                .message(customMessage).build();
    }

    private ApiInfo apiInfo() {
        final StringBuilder copyright = new StringBuilder();

        copyright
                .append("Copyright (c) ")
                .append(Calendar.getInstance().get(Calendar.YEAR))
                .append(" Microservices SP Meetup. All rights reserved");
        
        return ApiInfo.DEFAULT;
//
//        return new ApiInfo(
//                this.getTitle(),
//                this.getDescription(),
//                this.getVersion(),
//                "", // TERMS OF USE URL
//                MAIN_CONTACT,
//                copyright.toString(),
//                ""); // LICENSE URL
    }
}
