import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Query6 {
    public static String Query6(Iterable<FlightRecord> input) {
        Map<String, Integer> m = new HashMap<>();
        String s1;
        String s2;
        String key;
        String flight = null;
        int mostFlights = 0;

        for(FlightRecord r : input) {
            s1 = r.ORIGIN_STATE_ABR;
            s2 = r.DEST_STATE_ABR;

            // alphabetize the states
            if(s1.compareTo(s2) <= 0) {
                key = s1 + "," + s2;
            } else {
                key = s2 + "," + s1;
            }

            // puts into map
            if(m.containsKey(key)) {
                m.put(key, m.get(key)+1);
            } else {
                m.put(key, 1);
            }

            // finds the current flight with the most occurrences
            if(m.get(key) > mostFlights) {
                flight = key;
                mostFlights = m.get(key);
            }
        }

        return flight;
    }

    public static void main(String[] args) throws IOException {
        Iterable<FlightRecord> input = DataImporter.getData("flights1990.csv");
        String r = Query6(input);
        System.out.println(r);
    }
}
