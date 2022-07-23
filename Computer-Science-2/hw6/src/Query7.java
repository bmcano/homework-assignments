import java.io.IOException;
import java.util.*;

public class Query7 {
    public static Iterable<String> Query7(Iterable<FlightRecord> input) {
        Set<String> s = new HashSet<>();
        Map<String, Integer> m = new HashMap<>();

        // compile map and count how many flights originate from Iowa
        for(FlightRecord r : input) {
            if(m.containsKey(r.DEST_STATE_ABR)) {
                if(r.ORIGIN_STATE_ABR.equals("IA")) {
                    m.put(r.DEST_STATE_ABR, m.get(r.DEST_STATE_ABR)+1);
                }
            } else {
                m.put(r.DEST_STATE_ABR, 0);
            }
        }

        // check each element and find the states that Iowa does not fly to
        Set<String> s1 = m.keySet();
        for(String i : s1) {
            if(m.get(i) == 0) {
                s.add(i);
            }
        }

        return s;
    }

    public static void main(String[] args) throws IOException {
        Iterable<FlightRecord> input = DataImporter.getData("flights1990.csv");
        Iterable<String> rs = Query7(input);
        for (String r : rs) {
            System.out.println(r);
        }
    }
}
