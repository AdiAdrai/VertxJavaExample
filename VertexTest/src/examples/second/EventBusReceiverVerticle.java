package examples.second;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

/**
 * Created by Adi on 01-May-17.
 */
public class EventBusReceiverVerticle extends AbstractVerticle {

    private String name = null;

    public EventBusReceiverVerticle(String name) {
        this.name = name;
    }

    public void start(Future<Void> startFuture) {
        vertx.eventBus().consumer("anAddress", message -> {
            System.out.println(this.name +
                    " received message: " +
                    message.body());
        });
    }

}
