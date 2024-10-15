package za.co.kafka.topic;


import io.smallrye.reactive.messaging.kafka.Record;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

@ApplicationScoped
public class MyMessagingApplication {

//    private static final Logger LOG = Logger.getLogger(MyMessagingApplication.class);
//
//    private Random random = new Random();
//
//    private List<WeatherStation> stations = List.of(
//            new WeatherStation(1, "Hamburg", 13),
//            new WeatherStation(2, "Snowdonia", 5),
//            new WeatherStation(3, "Boston", 11),
//            new WeatherStation(4, "Tokio", 16),
//            new WeatherStation(5, "Cusco", 12),
//            new WeatherStation(6, "Svalbard", -7),
//            new WeatherStation(7, "Porthsmouth", 11),
//            new WeatherStation(8, "Oslo", 7),
//            new WeatherStation(9, "Marrakesh", 20));
//
//    @Outgoing("stations")
//    public Multi<Record<Integer, String>> weatherStations() {
//        LOG.info("Writing to the topic!!");
//        return Multi.createFrom().items(stations.stream()
//                .map(s -> Record.of(
//                        s.id,
//                        "{ \"id\" : " + s.id +
//                                ", \"name\" : \"" + s.name + "\" }"))
//        );
//    }

    @Inject
    @Channel("stations-out")
    Emitter<Record<Integer, String>> emitter;

    public void sendStationToKafka(WeatherStation station) {
        station.id = station.id + 1;
        station.name = station.name + 1 + "!";

        emitter.send(Record.of(station.id, station.name));
    }

    static class WeatherStation{
        int id;
        String name;
        int temperature;

        public WeatherStation(int id, String name, int temperature) {
            this.id = id;
            this.name = name;
            this.temperature = temperature;
        }
    }
}
