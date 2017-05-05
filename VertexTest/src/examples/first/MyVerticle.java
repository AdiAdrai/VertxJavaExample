package examples.first;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

/**
 * Created by Adi on 01-May-17.
 */
public class MyVerticle extends AbstractVerticle {

    @Override
    public void start(Future<Void> startFuture) {
        System.out.println("examples.first.MyVerticle started!");

    }

    @Override
    public void stop(Future stopFuture) throws Exception {
        System.out.println("examples.first.MyVerticle stopped!");
    }
}
