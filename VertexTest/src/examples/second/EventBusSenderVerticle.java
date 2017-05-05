package examples.second;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

/**
 * Created by Adi on 01-May-17.
 */
public class EventBusSenderVerticle extends AbstractVerticle {

    @Override
    public void start(Future<Void> startFuture) {
        vertx.eventBus().publish("anAddress", "message 2");
        vertx.eventBus().send   ("anAddress", "message 1");
    }

}
