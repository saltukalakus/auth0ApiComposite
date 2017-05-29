/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auth0Okhttp;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;

/**
 *
 * @author Mac
 */
public class Pj {
    
    public static String printJ(String json){
        
        String prettyJson = new GsonBuilder().setPrettyPrinting().create().toJson(new JsonParser().parse(json));
       // System.out.println(prettyJson);
    
        return prettyJson;
    
    }
    
}
