/**
 * 
 */
package asgn2Passengers;

/**
 * @author hogan
 * @author Johnson
 */
public class Economy extends Passenger {

	/**
	 * Economy Constructor (Partially Supplied)
	 * Passenger is created in New state, later given a Confirmed Economy Class reservation, 
	 * Queued, or Refused booking if waiting list is full. 
	 * 
	 * @param bookingTime <code>int</code> day of the original booking. 
	 * @param departureTime <code>int</code> day of the intended flight.  
	 * @throws PassengerException if invalid bookingTime or departureTime 
	 * @see asgnPassengers.Passenger#Passenger(int,int)
	 */
	public Economy(int bookingTime,int departureTime) throws PassengerException {

		super(bookingTime, departureTime); 
		if (bookingTime < 0)
		{
			throw new PassengerException("invalid booking time");
		}
		if (departureTime < 0)
		{
			throw new PassengerException("invalid departure time");
		}
		this.passID = "Y:" + this.passID;		
	}
	
	@Override
	public String noSeatsMsg() {
		return "No seats available in Economy";
	}

	@Override
	public Passenger upgrade() {
		Passenger Premium = new Premium(); 
		this.copyPassengerState(Premium);
		Premium.passID = "P:" + Premium.passID.substring(2); 
		return Premium;
	}
}
