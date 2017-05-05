package endPoints;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;

/**
 * Created by Adi on 01-May-17.
 */
public class Hello extends AbstractVerticle {

    @Override
    public void start(Future<Void> fut) throws Exception {
        Router router = Router.router(vertx);

        // Bind "/" to our hello message - so we are still compatible.
        router.route("/hello").handler(routingContext -> {
            HttpServerResponse response = routingContext.response();
            String name = routingContext.request().getParam("name");
            System.out.println(name);
            if (name == null || name.isEmpty()) {
                name = "world";
            }
            String responseString = "<h1>Hello " + name + "  </h1>";

            response
                    .putHeader("content-type", "text/html")
                    .end(responseString);
        });

        // Create the HTTP server and pass the "accept" method to the request handler.
        vertx
                .createHttpServer()
                .requestHandler(router::accept)
                .listen(
                        // Retrieve the port from the configuration,
                        // default to 8080.
                        config().getInteger("http.port", 8080),
                        result -> {
                            if (result.succeeded()) {
                                fut.complete();
                            } else {
                                fut.fail(result.cause());
                            }
                        }
                );

    }
}
