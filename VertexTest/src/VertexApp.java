import finalProject.RestService;
import io.vertx.core.Vertx;

/**
 * Created by Adi on 01-May-17.
 */
public class VertexApp {
    public static void main(String [] args) throws InterruptedException {

        Vertx vertx = Vertx.vertx();

        /*First Example*/
//        MyVerticle myVerticle = new MyVerticle();
//        vertx.deployVerticle(myVerticle);
//        Thread.sleep(2000);
//        vertx.close();

        /*Second Example*/
//        vertx.deployVerticle(new EventBusReceiverVerticle("R1"));
//        vertx.deployVerticle(new EventBusReceiverVerticle("R2"));
//        Thread.sleep(3000);
//        vertx.deployVerticle(new EventBusSenderVerticle());

        /*Third Example*/
//        vertx.deployVerticle(new VertxHttpServerVerticle());

        /*Fourth Example*/
//        vertx.deployVerticle(new Server());


        /*End Points Example*/
//        vertx.deployVerticle(new HealthCheck());
//        vertx.deployVerticle(new Hello());
//        vertx.deployVerticle(new Forcast());


        /*Final Project Example*/
        vertx.deployVerticle(new RestService());
    }
}
