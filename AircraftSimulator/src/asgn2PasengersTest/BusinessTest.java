/**
 * 
 */
package asgn2PasengersTest;

import static org.junit.Assert.*;

import org.junit.Test;

import asgn2Passengers.Business;
import asgn2Passengers.Passenger;
import asgn2Passengers.PassengerException;

/**
 * @author Spicer
 *
 */
public class BusinessTest {

	/**
	 * Test method for {@link asgn2Passengers.Business#upgrade()}.
	 * @throws PassengerException 
	 */
	@Test
	public void testUpgradeNormal() throws PassengerException {
		Business UpgradeNormal = new Business(4,10);
		Passenger Upgraded = UpgradeNormal.upgrade();
		String ID = Upgraded.getPassID();
		assertEquals(ID, "F:0");
	}

	/**
	 * Test method for {@link asgn2Passengers.Business#Business(int, int)}.
	 * @throws PassengerException 
	 */
	@Test
	public void testBusinessNormal() throws PassengerException {
		Business BusinessNormal = new Business(4,10);
		assertEquals(BusinessNormal.getBookingTime(), 4);
		assertEquals(BusinessNormal.getDepartureTime(), 10);
		assertTrue(BusinessNormal.isNew());
	}

	/**
	 * Test method for {@link asgn2Passengers.Business#Business(int, int)}.
	 * @throws PassengerException 
	 */
	@Test(expected = PassengerException.class)
	public void testBusinessBookingTimeLess0() throws PassengerException {
		Business timeLess0 = new Business(-1,10);
	}
	
	/**
	 * Test method for {@link asgn2Passengers.Business#Business(int, int)}.
	 * @throws PassengerException 
	 */
	@Test(expected = PassengerException.class)
	public void testBusinessDepartureTimeLessOrEqual0() throws PassengerException {
		Business timeLessorequal0 = new Business(2,0);
	}
	
	/**
	 * Test method for {@link asgn2Passengers.Business#Business(int, int)}.
	 * @throws PassengerException 
	 */
	@Test(expected = PassengerException.class)
	public void testBusinessDepartureTimeLessBookingTime() throws PassengerException {
		Business deparutrelessbooking = new Business(2,1);
	}
}
