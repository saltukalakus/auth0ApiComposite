/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package okhttpTest;

/**
 *
 * @author Mac
 */
import com.google.gson.Gson;
import java.io.IOException;
import java.util.logging.Logger;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.Headers;

public class OkHTTPClass {

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    public static final MediaType XML = MediaType.parse("application/xml; charset=utf-8");

    Gson gson;

    OkHttpClient client = new OkHttpClient();
    static OkHttpClient staticClient = new OkHttpClient();

    String url = "http://localhost:8080/UnFpaRestApi/webresources/App/getUsersOnline";

    static String POST_URL = "http://localhost:8080/UnFpaRestApi/webresources/submit/submitPost";

    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");

    static final Logger logger = Logger.getLogger("OkHTTPClass");

    public OkHTTPClass() {
        gson = new Gson();
    }

    public static void main(String[] args) throws IOException {
        OkHTTPClass example = new OkHTTPClass();
        //  String json = example.bowlingJson("Jesse", "Jake");

        //GET JSON
        // Trans trans = new Trans();
        // trans.apiUser = "testing";
        log("calling getUsersONline api");
        //String response = example.post("http://localhost:8080/UnFpaRestApi/webresources/App/getUsersOnline", example.gson.toJson(trans));
        //log("thre reply:" + response);
    }

    public static String staticGet(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", "Bearer " + "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImtpZCI6Ik16ZzNNVVJHTkVSRVEwVXlRVGt5UTBZeE1qWXdOMEkzT1RFM1FrVkdNREU1TWtRNFJFSXhNZyJ9.eyJpc3MiOiJodHRwczovL2hhZHJpYW5odS5ldS5hdXRoMC5jb20vIiwic3ViIjoiMVo4ODM3eXNUZ1ZCYTJxR2lTQkRYc2pFRnExWTlZdWFAY2xpZW50cyIsImF1ZCI6Imh0dHBzOi8vaGFkcmlhbmh1LmV1LmF1dGgwLmNvbS9hcGkvdjIvIiwiZXhwIjoxNDk2MTUzODc5LCJpYXQiOjE0OTYwNjc0NzksInNjb3BlIjoicmVhZDpjbGllbnRfZ3JhbnRzIGNyZWF0ZTpjbGllbnRfZ3JhbnRzIGRlbGV0ZTpjbGllbnRfZ3JhbnRzIHVwZGF0ZTpjbGllbnRfZ3JhbnRzIHJlYWQ6dXNlcnMgdXBkYXRlOnVzZXJzIGRlbGV0ZTp1c2VycyBjcmVhdGU6dXNlcnMgcmVhZDp1c2Vyc19hcHBfbWV0YWRhdGEgdXBkYXRlOnVzZXJzX2FwcF9tZXRhZGF0YSBkZWxldGU6dXNlcnNfYXBwX21ldGFkYXRhIGNyZWF0ZTp1c2Vyc19hcHBfbWV0YWRhdGEgY3JlYXRlOnVzZXJfdGlja2V0cyByZWFkOmNsaWVudHMgdXBkYXRlOmNsaWVudHMgZGVsZXRlOmNsaWVudHMgY3JlYXRlOmNsaWVudHMgcmVhZDpjbGllbnRfa2V5cyB1cGRhdGU6Y2xpZW50X2tleXMgZGVsZXRlOmNsaWVudF9rZXlzIGNyZWF0ZTpjbGllbnRfa2V5cyByZWFkOmNvbm5lY3Rpb25zIHVwZGF0ZTpjb25uZWN0aW9ucyBkZWxldGU6Y29ubmVjdGlvbnMgY3JlYXRlOmNvbm5lY3Rpb25zIHJlYWQ6cmVzb3VyY2Vfc2VydmVycyB1cGRhdGU6cmVzb3VyY2Vfc2VydmVycyBkZWxldGU6cmVzb3VyY2Vfc2VydmVycyBjcmVhdGU6cmVzb3VyY2Vfc2VydmVycyByZWFkOmRldmljZV9jcmVkZW50aWFscyB1cGRhdGU6ZGV2aWNlX2NyZWRlbnRpYWxzIGRlbGV0ZTpkZXZpY2VfY3JlZGVudGlhbHMgY3JlYXRlOmRldmljZV9jcmVkZW50aWFscyByZWFkOnJ1bGVzIHVwZGF0ZTpydWxlcyBkZWxldGU6cnVsZXMgY3JlYXRlOnJ1bGVzIHJlYWQ6ZW1haWxfcHJvdmlkZXIgdXBkYXRlOmVtYWlsX3Byb3ZpZGVyIGRlbGV0ZTplbWFpbF9wcm92aWRlciBjcmVhdGU6ZW1haWxfcHJvdmlkZXIgYmxhY2tsaXN0OnRva2VucyByZWFkOnN0YXRzIHJlYWQ6dGVuYW50X3NldHRpbmdzIHVwZGF0ZTp0ZW5hbnRfc2V0dGluZ3MgcmVhZDpsb2dzIHJlYWQ6c2hpZWxkcyBjcmVhdGU6c2hpZWxkcyBkZWxldGU6c2hpZWxkcyB1cGRhdGU6dHJpZ2dlcnMgcmVhZDp0cmlnZ2VycyByZWFkOmdyYW50cyBkZWxldGU6Z3JhbnRzIHJlYWQ6Z3VhcmRpYW5fZmFjdG9ycyB1cGRhdGU6Z3VhcmRpYW5fZmFjdG9ycyByZWFkOmd1YXJkaWFuX2Vucm9sbG1lbnRzIGRlbGV0ZTpndWFyZGlhbl9lbnJvbGxtZW50cyBjcmVhdGU6Z3VhcmRpYW5fZW5yb2xsbWVudF90aWNrZXRzIHJlYWQ6dXNlcl9pZHBfdG9rZW5zIn0.PoAgzAicXh3h_jIrxCepVc1nnVyyLiHEUFT9VZ4aUf7HMCJQwSc_opCB6XAjgiCySiN-7V_awn7Gu6aHGzCIs1McKuhGfCWQ0GsjeQDyKPmGWg_6fmds5Ii-X55r0AWTBYycyL8AG1awy3XW3IzwT8NHdUJWJwdVUwK9RZFtQqnQtETVdOs3OTy3sqBG1My5jB9y0-evqDbTq58ihScTp-XWtuokdbQT906FeoMputVgucpryeC2jKe_R_bOuL0-qTD6z7lxvJhQwrq4RqzntBoFhN4Ih8LiNMFz4pEgbXmUWGXMAwwJc3A7Vte97CpuCa0r0seBdbkid5WgKlEVSg")
                .build();

        Response response = staticClient.newCall(request).execute();
        if (!response.isSuccessful()) {
            throw new IOException("Unexpected code " + response);
        }

        Headers responseHeaders = response.headers();
        for (int i = 0; i < responseHeaders.size(); i++) {
          //  System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
        }

     //   log(response.body().string());
        
       return  response.body().string();
    }

    //CALLS THE URL
    public static String staticPostXML(String url, String xml) throws IOException {
        log("++++++:  OkHttpCaller staticPostXML ");
        // log("OkHttpCaller url:" + url);
        // log("OkHttpCaller json:" + Pj.printJ(json));
        RequestBody body = RequestBody.create(XML, xml);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = staticClient.newCall(request).execute();
        String responseString = response.body().string();
        log("++++++:  OkHttpCaller staticPostXML responseString:" + responseString);
        return responseString;
    }

    //CALLS THE URL
    public static String staticPost(String url, String json) throws IOException {
        log("++++++:  OkHttpCaller static post");
        // log("OkHttpCaller url:" + url);
        // log("OkHttpCaller json:" + Pj.printJ(json));
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = staticClient.newCall(request).execute();
        String responseString = response.body().string();
        log("++++++:  OkHttpCaller staticPost responseString:" + responseString);
        return responseString;
    }

    //POSTING A STRING
    public String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

//    String bowlingJson(String player1, String player2) {
//        return "{'winCondition':'HIGH_SCORE',"
//                + "'name':'Bowling',"
//                + "'round':4,"
//                + "'lastSaved':1367702411696,"
//                + "'dateStarted':1367702378785,"
//                + "'players':["
//                + "{'name':'" + player1 + "','history':[10,8,6,7,8],'color':-13388315,'total':39},"
//                + "{'name':'" + player2 + "','history':[6,10,5,10,10],'color':-48060,'total':41}"
//                + "]}";
//    }
    static void log(String msg) {
        logger.info(msg);
    }

}
