package examples.fourth;

import io.vertx.codetrans.Runner;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientRequest;
import io.vertx.core.http.HttpClientResponse;
import io.vertx.core.http.HttpMethod;

/**
 * Created by Adi on 01-May-17.
 */
public class Client extends AbstractVerticle {

    @Override
    public void start() throws Exception {

        HttpClient httpClient = vertx.createHttpClient();

        httpClient.getNow(80, "www.google.com", "/", httpClientResponse -> httpClientResponse.bodyHandler(buffer -> {
            System.out.println("Response (" + buffer.length() + "): ");
            System.out.println(buffer.getString(0, buffer.length()));

            vertx.eventBus().publish("anAddress", buffer.getString(0, buffer.length()));

        }));
    }
}