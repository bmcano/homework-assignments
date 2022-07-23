import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Query5 {
    public static String Query5(Iterable<FlightRecord> input) {
        Map<Integer, Integer> m = new HashMap<>();
        int mostFlights = 0;
        int month = 0;

        for(FlightRecord r : input) {
            // places values into map
            if(m.containsKey(r.MONTH)) {
                m.put(r.MONTH, m.get(r.MONTH)+1);
            } else {
                m.put(r.MONTH, 1);
            }

            // finds the current month with the most flights
            if(m.get(r.MONTH) > mostFlights) {
                month = r.MONTH;
                mostFlights = m.get(r.MONTH);
            }
        }

        return(month + " had " + mostFlights + " flights");
    }

    public static void main(String[] args) throws IOException {
        Iterable<FlightRecord> input = DataImporter.getData("flights1990.csv");
        String r = Query5(input);
        System.out.println(r);
    }
}
