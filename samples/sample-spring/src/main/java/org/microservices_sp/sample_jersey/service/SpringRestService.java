// CHECK https://springfox.github.io/springfox/docs/current/#docket-spring-java-configuration

package org.microservices_sp.sample_jersey.service;

import java.util.Date;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author Andre Dias (andre.dias@summa.com.br)
 *
 * @since 14 de set de 2016
 * @version 1.0
 */
@RestController
@Api(
        tags = "business",
        description = "Spring REST Service",
        protocols = "http, https, ws, wss")
public class SpringRestService {

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON, path = "entity/action")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(
            value = "Spring Common Action",
            notes = "General Description for Operation 'Spring Common Action'",
            response = Response.class,
            nickname = "actionOK"
    )
    public Response actionOK() {
        return Response.ok().entity("[SPRING] " + new Date(System.currentTimeMillis())).build();
    }
    
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, path = "entity/action_accepted")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ApiOperation(
            value = "Spring Common Action Accepted",
            notes = "General Description for Operation 'Spring Common Action Accepted'",
            response = Response.class)
    public Response actionAccepted() {
        return Response.status(Response.Status.ACCEPTED).entity("[SPRING] " + new Date(System.currentTimeMillis())).build();
    }
}