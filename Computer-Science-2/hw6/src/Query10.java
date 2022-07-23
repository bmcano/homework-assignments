import java.io.IOException;
import java.util.*;

public class Query10 {

    public static Iterable<String> Query10(Iterable<FlightRecord> input) {
        // Algorithm 3
        // Uses two SortedMaps where the keys are airports and the values are List<FlightRecords>

        // for all flights k in the input:
        //   in map1, add k to the list whose key is k.DEST

        // for all flights m in the input:
        //   in map2, add m to the list whose key is m.ORIGIN

        // iterate through the maps in sorted order, outputting the matches within a key by using nested
        // loops

        Set<String> s = new HashSet<>();
        Map<String, List<FlightRecord>> m1 = new HashMap<>();
        Map<String, List<FlightRecord>> m2 = new HashMap<>();

        // ORIGIN_1
        for(FlightRecord r1 : input) {
            if(m1.containsKey(r1.ORIGIN)) {
                m1.get(r1.ORIGIN).add(r1);
            } else {
                List<FlightRecord> temp = new ArrayList<>();
                temp.add(r1);
                m1.put(r1.ORIGIN, temp);
            }
        }

        // DEST_2
        for(FlightRecord r2 : input) {
            if(m2.containsKey(r2.DEST)) {
                m2.get(r2.DEST).add(r2);
            } else {
                List<FlightRecord> temp = new ArrayList<>();
                temp.add(r2);
                m2.put(r2.DEST, temp);
            }
        }

        // DEST_1 == ORIGIN_2
        for(FlightRecord s1 : m1.get("CID")) {
            for(FlightRecord s2 : m2.get("LAX")) {
                if(s1.DEST.equals(s2.ORIGIN)) {
                    // ORIGIN_1->DEST_1->DEST_2
                    s.add("CID->" + s1.DEST + "->LAX");
                }
            }
        }

        return s;
    }

    public static void main(String[] args) throws IOException {
        Iterable<FlightRecord> input = DataImporter.getData("flights2020.csv");
        Timer t = new Timer();
        t.start();
        Iterable<String> results = Query10(input);
        t.end();
        for (String s : results) {
            System.out.println(s);
        }
        System.out.println(t.elapsedSeconds());
    }
}
