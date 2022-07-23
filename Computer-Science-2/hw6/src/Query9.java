import java.io.IOException;
import java.util.*;

public class Query9 {
    public static Iterable<String> Query9(Iterable<FlightRecord> input) {
        Set<String> s = new HashSet<>();
        Map<String, Map<String, Integer>> m = new HashMap<>();
        String key;
        String val;

        // makes the map
        // <DEST_STATE_ABR, <UNIQUE_CARRIER_NAME, OCCURRENCES>>
        for(FlightRecord r : input) {
            key = r.DEST_STATE_ABR;
            val = r.UNIQUE_CARRIER_NAME;
            // checks outside key: DEST_STATE_ABR
            if(m.containsKey(key)) {
                // checks inside key: UNIQUE_CARRIER_NAME
                if(m.get(key).containsKey(val)) {
                    m.get(key).put(val, m.get(key).get(val)+1);
                } else {
                    m.get(key).put(val, 1);
                }
            } else {
                // creates the new key, with the appropriate value
                Map<String, Integer> temp = new HashMap<>();
                temp.put(val, 1);
                m.put(key, temp);
            }
        }

        // iterates through both key sets
        for(String i : m.keySet()) { // i -> DEST_STATE_ABR
            Integer highest = 0;
            String carrier = null;
            for(String j : m.get(i).keySet()) { // j -> UNIQUE_CARRIER_NAME
                if(m.get(i).get(j) > highest) { // OCCURRENCES > highest
                    highest = m.get(i).get(j);
                    carrier = j;
                }
            }
            s.add(i + "," + carrier);
        }

        return s;
    }

    public static void main(String[] args) throws IOException {
        Iterable<FlightRecord> input = DataImporter.getData("flights2005.csv");
        Iterable<String> rs = Query9(input);
        for (String r : rs) {
            System.out.println(r);
        }
    }
}
