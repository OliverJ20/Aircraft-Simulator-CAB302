/**
 * 
 */
package asgn2PasengersTest;

import static org.junit.Assert.*;

import org.junit.Test;

import asgn2Passengers.Economy;
import asgn2Passengers.Passenger;
import asgn2Passengers.Economy;
import asgn2Passengers.PassengerException;
import asgn2Passengers.Premium;

/**
 * @author Spicer
 *
 */
public class EconomyTest {

	/**
	 * Test method for {@link asgn2Passengers.Economy#upgrade()}.
	 * @throws PassengerException 
	 */
	@Test
	public void testUpgrade() throws PassengerException {
		Economy UpgradeNormal = new Economy(4,10);
		Passenger Upgraded = UpgradeNormal.upgrade();
		String ID = Upgraded.getPassID();
		assertEquals(ID, "P:0");
	}

	/**
	 * Test method for {@link asgn2Passengers.Economy#Economy(int, int)}.
	 * @throws PassengerException 
	 */
	@Test
	public void testEconomyNormal() throws PassengerException {
		Economy EconomyNormal = new Economy(4,10);
		assertEquals(EconomyNormal.getBookingTime(), 4);
		assertEquals(EconomyNormal.getDepartureTime(), 10);
		assertTrue(EconomyNormal.isNew());
	}

	/**
	 * Test method for {@link asgn2Passengers.Economy#Economy(int, int)}.
	 * @throws PassengerException 
	 */
	@Test(expected = PassengerException.class)
	public void testEconomyBookingTimeLess0() throws PassengerException {
		Economy timeLess0 = new Economy(-1,10);
	}
	
	/**
	 * Test method for {@link asgn2Passengers.Economy#Economy(int, int)}.
	 * @throws PassengerException 
	 */
	@Test(expected = PassengerException.class)
	public void testEconomyDepartureTimeLessOrEqual0() throws PassengerException {
		Economy timeLessorequal0 = new Economy(2,0);
	}
	
	/**
	 * Test method for {@link asgn2Passengers.Economy#Economy(int, int)}.
	 * @throws PassengerException 
	 */
	@Test(expected = PassengerException.class)
	public void testEconomyDepartureTimeLessBookingTime() throws PassengerException {
		Economy deparutrelessbooking = new Economy(2,1);
	}
}
