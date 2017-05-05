package examples.fourth;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

/**
 * Created by Adi on 01-May-17.
 */
public class Server extends AbstractVerticle {

    public void start(Future<Void> startFuture) {

        vertx.eventBus().consumer("anAddress", message -> {
            System.out.println("Received message: " +
                    message.body());
        });

        vertx.deployVerticle(new Client());
    }

}
