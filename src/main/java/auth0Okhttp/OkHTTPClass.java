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

        Response response = staticClient.newCall(request).execute();
        if (!response.isSuccessful()) {
            throw new IOException("Unexpected code " + response);
        }

        Headers responseHeaders = response.headers();
        for (int i = 0; i < responseHeaders.size(); i++) {
            //  System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
        }

        //   log(response.body().string());
        return response.body().string();
    }

    static void log(String msg) {
        logger.info(msg);
    }

}
