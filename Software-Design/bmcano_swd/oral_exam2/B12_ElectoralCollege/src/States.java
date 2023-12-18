import java.util.HashMap;
import java.util.Map;

/**
 * @author Brandon Cano
 */
public class States {

    private static final String[] districts = {
            "Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado", "Connecticut", "Delaware",
            "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana",
            "Maine 1st", "Maine 2nd", "Maine Popular", "Maryland", "Massachusetts", "Michigan", "Minnesota",
            "Mississippi", "Missouri", "Montana", "Nebraska 1st", "Nebraska 2nd", "Nebraska 3rd", "Nebraska Popular",
            "Nevada", "New Hampshire", "New Jersey", "New Mexico", "New York", "North Carolina", "North Dakota", "Ohio",
            "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island", "South Carolina", "South Dakota", "Tennessee", "Texas",
            "Utah", "Vermont", "Virginia", "Washington", "West Virginia", "Wisconsin", "Wyoming", "Washington, D.C."
    };

    private static final int[] votes = {
            9, 3, 11, 6, 55, 9, 7, 3, 29, 16, 4, 4, 20, 11, 6, 6, 8, 8, 1, 1, 2, 10, 11, 16, 10, 6, 10, 3, 1, 1, 1,
            2, 6, 4, 14, 5, 29, 15, 3, 18, 7, 7, 20, 4, 9, 3, 11, 38, 6, 3, 13, 12, 5, 10, 3, 3
    };

    private static final String[] states = {
            "Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado", "Connecticut", "Delaware",
            "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana",
            "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota", "Mississippi", "Missouri", "Montana",
            "Nebraska", "Nevada", "New Hampshire", "New Jersey", "New Mexico", "New York", "North Carolina",
            "North Dakota", "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island", "South Carolina",
            "South Dakota", "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington", "West Virginia",
            "Wisconsin", "Wyoming",
    };

    private static final String[] stateCodes = {
            "AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "ID", "IL", "IN", "IA", "KS", "KY",
            "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC", "ND",
            "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV", "WI", "WY"
    };

    private final Map<String, Integer> electoralVotes = new HashMap<>();
    private final Map<String, String> stateCodesMap = new HashMap<>();

    public States() {
        // electoral votes map
        for (int i = 0; i < districts.length; i++) {
            electoralVotes.put(districts[i], votes[i]);
        }

        // state codes map
        for (int i = 0; i < states.length; i++) {
            stateCodesMap.put(states[i], stateCodes[i]);
        }
    }

    /**
     * This will grab the number of electoral votes associated with the given congressional district, 0 otherwise
     *
     * @param district the congressional district to get the vote count from
     * @return the number of electoral votes
     */
    public int getElectoralVotes(String district) {
        if (!electoralVotes.containsKey(district)) return 0;
        return electoralVotes.get(district);
    }

    /**
     * This will grab the state code for the given state.
     *
     * @param state the state to get the abbreviation of
     * @return the abbreviated state code.
     */
    public String getStateCode(String state) {
        return stateCodesMap.get(state);
    }
}
