package endPoints;

import clients.OpenWeatherMap;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpClientRequest;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;
import org.apache.commons.io.FileUtils;
import org.jruby.RubyProcess;
import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Adi on 01-May-17.
 */
public class Forcast extends AbstractVerticle {

    @Override
    public void start(Future<Void> fut) throws Exception {
        Router router = Router.router(vertx);

        // Bind "/" to our hello message - so we are still compatible.
        router.route("/forcast").handler(routingContext -> {

            int days = 1;

            HttpServerResponse response = routingContext.response();

            String city = routingContext.request().getParam("city");

            String daysStr = routingContext.request().getParam("days");

            if (city == null || city.isEmpty()) {
                throw new RuntimeException("No city was entered");
            }
            if (daysStr == null || daysStr.isEmpty()) {
                throw new RuntimeException("No days was entered");
            } else {
                days = Integer.parseInt(daysStr);
            }

            System.out.println("city = " + city);
            System.out.println("days = " + days);

            vertx.eventBus().consumer("OpenWeatherMap", message -> {
                System.out.println("Received message: " +
                        message.body());
                File file = convertJsonToCsv(message.body().toString());

                response.sendFile(file.getAbsolutePath());

            });

            vertx.deployVerticle(new OpenWeatherMap(city, days));


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

    private File convertJsonToCsv(String jsonString) {
        JSONObject output;
        try {
            output = new JSONObject(jsonString);


            JSONArray docs = output.getJSONArray("list");

            JSONArray toReturn = new JSONArray();

            for (int i = 0; i < docs.length(); i++) {
                String unFromatedDate = docs.getJSONObject(i).get("dt").toString();
                String minTemp = ((JSONObject) (docs.getJSONObject(i).get("temp"))).get("min").toString();
                String maxTemp = ((JSONObject) (docs.getJSONObject(i).get("temp"))).get("max").toString();
                String dayTemp = ((JSONObject) (docs.getJSONObject(i).get("temp"))).get("day").toString();

                Calendar calendar = Calendar.getInstance();

                long l = Long.parseLong(unFromatedDate + "000");

                calendar.setTimeInMillis(l);

                String dateFormatted = String.format("%d/%d/%d", calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH), calendar.get(Calendar.YEAR));

                JSONObject toAppend = new JSONObject();
                toAppend.accumulate("Max temp", maxTemp);
                toAppend.accumulate("Min temp", minTemp);
                toAppend.accumulate("Day temp", dayTemp);
                toAppend.accumulate("Date", dateFormatted);

                toReturn.put(toAppend);

            }

            File file = new File("C:\\Users\\Adi\\Desktop\\temp\\fromJSON.csv");
            String csv = CDL.toString(toReturn);
            FileUtils.writeStringToFile(file, csv);

            return file;

        } catch (JSONException e) {
            new RuntimeException(e);
        } catch (IOException e) {
            new RuntimeException(e);
        }
        return null;
    }
}
