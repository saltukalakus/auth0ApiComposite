/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auth0Okhttp;

/**
 *
 * @author Ivan
 */
import com.auth0test.auth0test.Auth0Constants;
import com.google.gson.Gson;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.logging.Logger;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHTTPClass {

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    Gson gson;

    OkHttpClient client = new OkHttpClient();
    static OkHttpClient staticClient = new OkHttpClient();

    static final Logger logger = Logger.getLogger("OkHTTPClass");

    public OkHTTPClass() {
        gson = new Gson();
    }

    public static String staticGet(String url, String jwtKey) throws IOException {
        log("staticGet: url:" + url);
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", jwtKey)
                .build();

        Response response = null;

        try {
         response = staticClient.newCall(request).execute();

            if (!response.isSuccessful()) {
                log("error:" + response.body().string());

                //  throw new IOException("Unexpected code " + response);
                //CHECK FOR 401
                if (response.code() == Auth0Constants.ERROR_CODE_401) {

                   throw new IOException("ERROR_CODE_401 code " + response);
                }
            }

        } catch (java.net.UnknownHostException ex) {
            
            throw new UnknownHostException("ERROR_CODE code " + response);

        }

        return response.body().string();
    }

    static void log(String msg) {
        logger.info(msg);
    }

}
