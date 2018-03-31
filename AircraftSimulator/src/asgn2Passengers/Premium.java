/**
 * 
 */
package asgn2Passengers;

/**
 * @author hogan
 * @author Johnson
 */
public class Premium extends Passenger {

	/**
	 * Premium Constructor (Partially Supplied)
	 * Passenger is created in New state, later given a Confirmed Premium Class reservation, 
	 * Queued, or Refused booking if waiting list is full. 
	 * 
	 * @param bookingTime <code>int</code> day of the original booking. 
	 * @param departureTime <code>int</code> day of the intended flight.  
	 * @throws PassengerException if invalid bookingTime or departureTime 
	 * @see asgnPassengers.Passenger#Passenger(int,int)
	 */
	public Premium(int bookingTime,int departureTime) throws PassengerException {
		//Stuff here
		super(bookingTime, departureTime); 
		if (bookingTime < 0)
		{
			throw new PassengerException("invalid booking time");
		}
		if (departureTime < 0)
		{
			throw new PassengerException("invalid departure time");
		}
		this.passID = "P:" + this.passID;
	}
	
	/**
	 * Simple constructor to support {@link asgn2Passengers.Passenger#upgrade()} in other subclasses
	 */
	protected Premium() {
		
	}

	@Override
	public Passenger upgrade() {
		Passenger Business = new Business(); 
		this.copyPassengerState(Business);
		Business.passID = "J:" + Business.passID.substring(2); 
		return Business;		
	}
	
	@Override
	public String noSeatsMsg() {
		return "No seats available in Premium";
	}
}
