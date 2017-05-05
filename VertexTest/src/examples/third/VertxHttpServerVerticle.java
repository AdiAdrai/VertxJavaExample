package examples.third;

/**
 * Created by Adi on 01-May-17.
 */

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;

public class VertxHttpServerVerticle extends AbstractVerticle {

    private HttpServer httpServer = null;

    @Override
    public void start() throws Exception {
        httpServer = vertx.createHttpServer();

        httpServer.requestHandler(request -> {

            System.out.println("incoming request!");

            Buffer fullRequestBody = Buffer.buffer();

            HttpServerResponse response = request.response();

            if (request.method() == HttpMethod.POST) {

                request.handler(new Handler<Buffer>() {
                    @Override
                    public void handle(Buffer buffer) {
                        fullRequestBody.appendBuffer(buffer);
                    }
                });

                request.endHandler(buffer -> {
                    // here you can access the
                    // fullRequestBody Buffer instance.
                });
            } else if (request.method() == HttpMethod.GET) {
                response.setStatusCode(200);
                response.headers()
                        .add("Content-Length", String.valueOf(57))
                        .add("Content-Type", "text/html");

                response.write("Vert.x is alive!");
                response.end();

            }
        });

        httpServer.listen(8080);

    }
}