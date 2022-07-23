package apps;

import net.datastructures.Map;
import net.datastructures.UnsortedTableMap;

/**
 *
 * @author bdmyers
 */
public class AirportSecurityDatabase {
	private final Map<Integer,String> allowedPassengers;
	private int nextTicketNumber;

	/* Create a new database to track tickets and passengers */
	public AirportSecurityDatabase() {
		allowedPassengers = new UnsortedTableMap<>();
		nextTicketNumber = 1000;
	}

	/* register a new passenger and return the generated ticket number */
	public int register(String name) {
		int ticketNumber = nextTicketNumber;
		nextTicketNumber++;
		allowedPassengers.put(ticketNumber, name);
		return ticketNumber;
	}

	/* check that the ticketNumber matches the name and print OK or DENIED */
	public void check(int ticketNumber, String nameOnID) {
		String registeredName = allowedPassengers.get(ticketNumber);
		if (registeredName == null) {
			System.out.println("ERROR: "+ticketNumber+" not recognized");
		} else if (registeredName.equals(nameOnID)) {
			System.out.println(nameOnID+" okay");
		} else {
			System.out.println("ERROR: "+nameOnID+" denied");
		}
	}
	
	public static void main(String[] args) {
		AirportSecurityDatabase db = new AirportSecurityDatabase();

		// tickets are registered
		int birdTicket = db.register("Bird");
		int frogTicket = db.register("Frog");
		int catTicket = db.register("Cat");
		int giraffeTicket = db.register("Giraffe");

		// Snake copies Bird's ticket
		int snakeTicket = birdTicket;

		// passengers try to get through security	
		db.check(frogTicket, "Frog");
		db.check(giraffeTicket, "Giraffe");
		db.check(snakeTicket, "Snake");
	}		
}
