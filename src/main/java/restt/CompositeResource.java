/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restt;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.transaction.SystemException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.NotSupportedException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import modelClasses.Auth0Client;
import modelClasses.Auth0Rules;
import okhttpTest.Pj;

/**
 * REST Web Service
 *
 * @author ivanmworozi
 */
@Path("testAutho")
public class CompositeResource {

    @Context
    private UriInfo context;

    String TAG = "CompositeResource:";
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(CompositeResource.class.getName());

    /**
     * Creates a new instance of CompositeResource
     */
    public CompositeResource() {
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("testAutho")
    public String testAutho(@Context Request req, String json) throws IOException, NotSupportedException, SystemException, Exception {
        log("++++++++:testAutho");
        log("sent Json:" + json);

        //GET CLIENT LIST
        List<Auth0Client> clientList = new ArrayList();
        clientList = loadClients();

        //GET RULES LIST
        List<Auth0Rules> rulesList = new ArrayList();
        rulesList = loadRules();

        //return Pj.printJ(reply);
        return "client list:" + clientList.size() + " rules list:" + rulesList;
    }

    public List<Auth0Client> loadClients() throws IOException {

        String reply = "it failed";

        String url = "https://hadrianhu.eu.auth0.com/api/v2/clients?fields=signing_keys&include_fields=false";

        reply = okhttpTest.OkHTTPClass.staticGet(url);
        //RETURN REPLY
        //log(Pj.printJ(reply));
        Gson gson = new Gson();
        Type token = new TypeToken<List<Auth0Client>>() {
        }.getType();

        List<Auth0Client> clientList = gson.fromJson(reply, token);
        log("got final list size:" + clientList.size());

        //SAVE CLIENT
        clientList.forEach(client -> {

            System.out.println("client:" + client.name);

        });

        return clientList;
    }

    public List<Auth0Rules> loadRules() throws IOException {

        String reply = "it failed";
        String url = "https://hadrianhu.eu.auth0.com/api/v2/rules";

        reply = okhttpTest.OkHTTPClass.staticGet(url);
        //RETURN REPLY
        //log(Pj.printJ(reply));

        Gson gson = new Gson();
        Type token = new TypeToken<List<Auth0Rules>>() {
        }.getType();

        List<Auth0Rules> rulesList = gson.fromJson(reply, token);
        log("got final rules size:" + rulesList.size());

        //OUTPUT LIST
        rulesList.forEach(rule -> {
            System.out.println("rule:" + rule.script);
        });

        //SAVE CLIENT
        //rulesList.forEach(client ->{});
        return rulesList;
    }

    void log(String msg) {

        logger.log(Level.INFO, "{0}{1}", new Object[]{TAG, msg});

    }

}
