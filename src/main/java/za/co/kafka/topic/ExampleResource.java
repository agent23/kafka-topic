package za.co.kafka.topic;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/dispatch")
public class ExampleResource {

    @Inject
    MyMessagingApplication messagingApplication;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response hello() {
        messagingApplication.sendStationToKafka(new MyMessagingApplication.WeatherStation(1, "Hamburg", 13));
        return Response.ok("Message dispatched").build();
    }
}
