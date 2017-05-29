/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restt;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.jmx.remote.internal.ClientListenerInfo;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.transaction.SystemException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.NotSupportedException;
import javax.ws.rs.Path;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import modelClasses.Auth0Client;
import modelClasses.Auth0Rules;
import modelClasses.Auth0Trans;

/**
 * REST Web Service
 *
 * @author ivanmworozi
 */
@Path("testAutho")
public class CompositeResource {

    @Context
    private UriInfo context;

    Gson gson = new Gson();

    String TAG = "CompositeResource:";
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(CompositeResource.class.getName());

    /**
     * Creates a new instance of CompositeResource
     */
    public CompositeResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("testAutho")
    public String testAutho(@Context HttpHeaders headers) throws IOException, NotSupportedException, SystemException, Exception {
        log("++++++++:testAutho");

        //INIT REPLY CLASS
        Auth0Trans reply = new Auth0Trans();

        //INIT LIST OF CLIENTS
        List<Auth0Client> clientList = new ArrayList();

        //INIT LIST OF RULES
        List<Auth0Rules> rulesList = new ArrayList();

        try {

            //CHECK FOR HEADERS
            if (null == headers.getRequestHeader("Authorization")) {

                reply.setFail("No jwtkey was sent, please send include a valid jwt key in the headers using: Authorization header");

            } else if (null == headers.getRequestHeader("auth0domain")) {

                reply.setFail("No domain was sent, pls send a valid Auth0 domain in headers using 'auth0domain' header e.g: hadrianhu.eu.auth0.com");

            } else {

                //GET JASON WEB TOKEN
                String jwtKey = headers.getRequestHeader("Authorization").get(0);
                log("jwtKey:" + jwtKey);

                //GET DOMAIN
                String domainUrl = headers.getRequestHeader("auth0domain").get(0);
                log("domainUrl:" + domainUrl);

                if (null == jwtKey) {
                    reply.setFail("No jwtkey was sent");

                } else if (null == domainUrl) {
                    reply.setFail("No domainUrl was sent");

                } else {

                    //CREATE OBJECT TO TRANSFER VARS
                    Auth0Trans trans = new Auth0Trans();
                    trans.domainUrl = domainUrl;
                    trans.jwtkey = jwtKey;
                    log("got trans:" + trans.domainUrl);

                    //GET RULES LIST
                    rulesList = loadRules(trans);

                    //GET CLIENT LIST
                    clientList = loadClients(trans);

                    //CLIENT LOOP  /TODO * CONVERT TO FOR FOR EACH STREAMS..
                    for (Auth0Client client : clientList) {

                        log("checking client:" + client.name);

                        //LOOP THROUGH RULES.. /TODO * CONVERT TO FOR FOR EACH STREAMS..
                        for (Auth0Rules rule : rulesList) {

                            String checkString = "context.clientName === '" + client.name + "'";

                            if (rule.script != null && rule.script.contains(checkString)) {
                                log("rule contains checkString:" + checkString);

                                client.rule = rule;
                                log("rule set to:" + client);
                            } else {
                                // log("rule does not contain not setting..");
                            }
                        }
                    }

                    //ADD LIST TO REPLY
                    reply.clientList = clientList;
                }

            }

            //CATCH BAD HOST EXCEPTION
        } catch (UnknownHostException ex) {
            
            reply.setFail("Invalid Domain. Pls check your domain");
            logger.log(Level.SEVERE, "error:" + ex.getMessage(), ex);
            
            //CATCH BAD JWT KEY EXCEPTION
        }  catch (IOException ex) {
            
            reply.setFail("Auth0 Returned 401 Unauthorized, please check your JWT Key. Pls note that your JWT expires after 24 hrs by default.");
            logger.log(Level.SEVERE, "error:" + ex.getMessage(), ex);
            
        } catch (Exception ex) {
            reply.setFail("SERVER__ERROR");
            logger.log(Level.SEVERE, "error:" + ex.getMessage(), ex);
        }
        return reply.toString();
    }

    /**
     * GET LIST OF THE APPLICATION / CLIENT USER HAS..
     * @param trans
     * @return
     * @throws IOException 
     */
    public List<Auth0Client> loadClients(Auth0Trans trans) throws IOException {

        String reply = "it failed";

        //https://hadrianhu.eu.auth0.com/api/v2/rules
        String url = "https://" + trans.domainUrl + "/api/v2/clients?fields=signing_keys&include_fields=false";

        reply = auth0Okhttp.OkHTTPClass.staticGet(url, trans.jwtkey);
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

    /**
     * LOAD LIST OF RULES CLIENT HAS
     * @param trans
     * @return
     * @throws IOException 
     */
    public List<Auth0Rules> loadRules(Auth0Trans trans) throws IOException {

        String reply = "it failed";
        String url = "https://" + trans.domainUrl + "/api/v2/rules";

        reply = auth0Okhttp.OkHTTPClass.staticGet(url, trans.jwtkey);
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
