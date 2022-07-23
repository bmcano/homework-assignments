import java.io.IOException;
import java.util.*;

public class Query4 {

    public static Iterable<String> Query4(Iterable<FlightRecord> input) {
        Set<String> s = new HashSet<>();
        Map<String, Integer> m = new HashMap<>();

        // make the hashmap of flights as keys and the occurrences as values
        for(FlightRecord r : input) {
            if(r.ORIGIN.equals("CID")) {
                // add to counter if flight exists
                if(m.containsKey(r.DEST)) {
                    m.put(r.DEST, m.get(r.DEST)+1);
                } else {
                    m.put(r.DEST, 1);
                }
            }
        }

        // format the hashmap into the set
        for(FlightRecord r : input) {
            if(r.ORIGIN.equals("CID")) {
                s.add(r.DEST + "=" + m.get(r.DEST));
            }
        }

        return s;
    }

    public static void main(String[] args) throws IOException {
        Iterable<FlightRecord> input = DataImporter.getData("flights2005.csv");
        Iterable<String> results = Query4(input);
        for (String s : results) {
            System.out.println(s);
        }
    }
}
