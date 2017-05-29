/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelClasses;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ivanmworozi
 */
public class Auth0Trans {
    
    
    static Gson toStringGson = new GsonBuilder().setPrettyPrinting().create();
    
    /**
     * HEADER ARGUMENT
     */
    public String domainUrl;
    public String jwtkey;
    
    /**
     * API REPLY VARS
     */
    public Boolean result;
    public String message;
    
    /**
     * CLIENT / APPLICAITON LIST
     */
    public List<Auth0Client> clientList;
    
    /**
     * SET REPLY TO SUCCESS ON SUCCESSFULL API CALL
     * @param message 
     */
     public void setSucc(String message) {
        result = true;
        this.message = message;
    }

    /**
     * SET REPLY TO FAILE ON FAILED API CALL
     * @param message 
     */
    public void setFail(String message) {
        result = false;
        this.message = message;
    }

    @Override
    public String toString() {
        return toStringGson.toJson(this);
    }
}
