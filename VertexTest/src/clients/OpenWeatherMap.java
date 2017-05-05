package clients;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientResponse;

/**
 * Created by Adi on 01-May-17.
 */
public class OpenWeatherMap extends AbstractVerticle {

    //Goto api.openweathermap.org and get a APPID to use this API
    private final String APPID = "APPID";

    private String city;

    private int days;

    public OpenWeatherMap(String city, int days) {
        this.city = city;
        this.days = days;
    }


    @Override
    public void start() {

//        HttpClient httpClient = vertx.createHttpClient();

//        httpClient.getNow(80, "api.openweathermap.org", "/data/2.5/forecast/daily?q=" + this.city +
//                        "&mode=csv&units=metric&cnt=" + this.days + "&APPID=" + this.APPID,
//                httpClientResponse -> httpClientResponse.bodyHandler(buffer -> {
//                    System.out.println("Response (" + buffer.length() + "): ");
//                    System.out.println(buffer.getString(0, buffer.length()));
//
//                    vertx.eventBus().publish("OpenWeatherMap", buffer.getString(0, buffer.length()));
//
//
//                }));

        //once you get the APPID delete these two lines and un-comment the code above
        String j = "{\"city\":{\"id\":2643743,\"name\":\"London\",\"coord\":{\"lon\":-0.1258,\"lat\":51.5085},\"country\":\"GB\",\"population\":0},\"cod\":\"200\",\"message\":0.0682184,\"cnt\":7,\"list\":[{\"dt\":1493636400,\"temp\":{\"day\":12.06,\"min\":9.5,\"max\":12.21,\"night\":10.6,\"eve\":11.67,\"morn\":9.5},\"pressure\":1011.78,\"humidity\":77,\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10d\"}],\"speed\":5.04,\"deg\":174,\"clouds\":68,\"rain\":0.61},{\"dt\":1493722800,\"temp\":{\"day\":14.64,\"min\":7.4,\"max\":14.64,\"night\":7.4,\"eve\":11.78,\"morn\":8.23},\"pressure\":1025.23,\"humidity\":74,\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10d\"}],\"speed\":3,\"deg\":18,\"clouds\":88,\"rain\":1.72},{\"dt\":1493809200,\"temp\":{\"day\":11.2,\"min\":8.19,\"max\":11.28,\"night\":9.19,\"eve\":10.45,\"morn\":8.19},\"pressure\":1029.95,\"humidity\":75,\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10d\"}],\"speed\":5.62,\"deg\":31,\"clouds\":64,\"rain\":0.8},{\"dt\":1493895600,\"temp\":{\"day\":13.85,\"min\":7.58,\"max\":13.85,\"night\":7.94,\"eve\":10.36,\"morn\":7.58},\"pressure\":1032.21,\"humidity\":0,\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10d\"}],\"speed\":6.01,\"deg\":52,\"clouds\":10,\"rain\":1.92},{\"dt\":1493982000,\"temp\":{\"day\":11.76,\"min\":5.73,\"max\":11.76,\"night\":5.73,\"eve\":10.9,\"morn\":8.3},\"pressure\":1027.74,\"humidity\":0,\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10d\"}],\"speed\":6.57,\"deg\":46,\"clouds\":66,\"rain\":0.53},{\"dt\":1494068400,\"temp\":{\"day\":11.7,\"min\":6.75,\"max\":11.7,\"night\":9.95,\"eve\":9.4,\"morn\":6.75},\"pressure\":1013.08,\"humidity\":0,\"weather\":[{\"id\":501,\"main\":\"Rain\",\"description\":\"moderate rain\",\"icon\":\"10d\"}],\"speed\":8.09,\"deg\":78,\"clouds\":90,\"rain\":8.19},{\"dt\":1494154800,\"temp\":{\"day\":11.5,\"min\":8.46,\"max\":11.5,\"night\":8.46,\"eve\":11.06,\"morn\":10.53},\"pressure\":1010.21,\"humidity\":0,\"weather\":[{\"id\":501,\"main\":\"Rain\",\"description\":\"moderate rain\",\"icon\":\"10d\"}],\"speed\":7.53,\"deg\":309,\"clouds\":94,\"rain\":3.06}]}";

        vertx.eventBus().publish("OpenWeatherMap", j);
    }
}

