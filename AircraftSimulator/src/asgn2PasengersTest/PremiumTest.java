/**
 * 
 */
package asgn2PasengersTest;

import static org.junit.Assert.*;

import org.junit.Test;

import asgn2Passengers.Premium;
import asgn2Passengers.Premium;
import asgn2Passengers.Business;
import asgn2Passengers.Passenger;
import asgn2Passengers.PassengerException;

/**
 * @author Spicer
 *
 */
public class PremiumTest {

	/**
	 * Test method for {@link asgn2Passengers.Premium#upgrade()}.
	 * @throws PassengerException 
	 */
	@Test
	public void testUpgrade() throws PassengerException {
		Premium UpgradeNormal = new Premium(4,10);
		Passenger Upgraded = UpgradeNormal.upgrade();
		String ID = Upgraded.getPassID();
		assertEquals(ID, "J:0");
	}

	/**
	 * Test method for {@link asgn2Passengers.Premium#Premium(int, int)}.
	 * @throws PassengerException 
	 */
	@Test
	public void testPremiumNormal() throws PassengerException {
		Premium PremiumNormal = new Premium(4,10);
		assertEquals(PremiumNormal.getBookingTime(), 4);
		assertEquals(PremiumNormal.getDepartureTime(), 10);
		assertTrue(PremiumNormal.isNew());
	}

	/**
	 * Test method for {@link asgn2Passengers.Premium#Premium(int, int)}.
	 * @throws PassengerException 
	 */
	@Test(expected = PassengerException.class)
	public void testPremiumBookingTimeLess0() throws PassengerException {
		Premium timeLess0 = new Premium(-1,10);
	}
	
	/**
	 * Test method for {@link asgn2Passengers.Premium#Premium(int, int)}.
	 * @throws PassengerException 
	 */
	@Test(expected = PassengerException.class)
	public void testPremiumDepartureTimeLessOrEqual0() throws PassengerException {
		Premium timeLessorequal0 = new Premium(2,0);
	}
	
	/**
	 * Test method for {@link asgn2Passengers.Premium#Premium(int, int)}.
	 * @throws PassengerException 
	 */
	@Test(expected = PassengerException.class)
	public void testPremiumDepartureTimeLessBookingTime() throws PassengerException {
		Premium deparutrelessbooking = new Premium(2,1);
	}
}
