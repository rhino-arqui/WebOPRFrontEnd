/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proxies;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import utils.EndPoints;

/**
 * Jersey REST client generated for REST resource:RentarsResource [/rentar]<br>
 * USAGE:
 * <pre>
 *        RentarProxy client = new RentarProxy();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author sistemas
 */
public class RentarProxy {
    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = EndPoints.OprEndPoint;

    public RentarProxy() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("rentar");
    }

    public Response postJson(Object requestEntity) throws ClientErrorException {
        return webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), Response.class);
    }

    public String getJson() throws ClientErrorException {
        WebTarget resource = webTarget;
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
    }

    public void close() {
        client.close();
    }
    
}
