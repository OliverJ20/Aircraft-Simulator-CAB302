/**
 * 
 */
package asgn2PasengersTest;

import static org.junit.Assert.*;

import org.junit.Test;

import asgn2Passengers.First;
import asgn2Passengers.PassengerException;

/**
 * @author Spicer
 *
 */
public class FirstTest {

	/**
	 * Test method for {@link asgn2Passengers.First#First(int, int)}.
	 * @throws PassengerException 
	 */
	@Test
	public void testFirstNormal() throws PassengerException {
		First firstNormal = new First(4,10);
		assertEquals(firstNormal.getBookingTime(), 4); 
		assertEquals(firstNormal.getDepartureTime(), 10); 
		assertTrue(firstNormal.isNew());
	}

	/**
	 * Test method for {@link asgn2Passengers.First#First(int, int)}.
	 * @throws PassengerException 
	 */
	@Test(expected = PassengerException.class)
	public void testFirstBookingTimeLess0() throws PassengerException {
		First timeLess0 = new First(-1,10);
	}
	
	/**
	 * Test method for {@link asgn2Passengers.First#First(int, int)}.
	 * @throws PassengerException 
	 */
	@Test(expected = PassengerException.class)
	public void testFirstDepartureTimeLessOrEqual0() throws PassengerException {
		First timeLessorequal0 = new First(2,0);
	}
	
	/**
	 * Test method for {@link asgn2Passengers.First#First(int, int)}.
	 * @throws PassengerException 
	 */
	@Test(expected = PassengerException.class)
	public void testFirstDepartureTimeLessBookingTime() throws PassengerException {
		First deparutrelessbooking = new First(2,1);
	}
	
	/**
	 * Test method for {@link asgn2Passengers#cancelSeat(int)}
	 */
	@Test
	public void testCancelSeatNormal() throws PassengerException {
		First cancelNormal = new First(2,10);
		cancelNormal.confirmSeat(3, 10);
		cancelNormal.cancelSeat(4);
		assertTrue(cancelNormal.isNew());
		assertEquals(cancelNormal.getBookingTime(), 4);
	}
	
	/**
	 * Test method for {@link asgn2Passengers#cancelSeat(int)}
	 */
	@Test(expected = PassengerException.class)
	public void testCancelSeatisNew() throws PassengerException {
		First cancelisNew= new First(2,10);
		cancelisNew.cancelSeat(4);
	}
	
	/**
	 * Test method for {@link asgn2Passengers#cancelSeat(int)}
	 */
	@Test(expected = PassengerException.class)
	public void testCancelSeatisQueued() throws PassengerException {
		First cancelQueued = new First(2,10);
		cancelQueued.queuePassenger(2, 4);
		cancelQueued.cancelSeat(4);
	}
	
	/**
	 * Test method for {@link asgn2Passengers#cancelSeat(int)}
	 */
	@Test(expected = PassengerException.class)
	public void testCancelSeatisRefused() throws PassengerException {
		First cancelRefused = new First(2,10);
		cancelRefused.refusePassenger(1);
		cancelRefused.cancelSeat(4);
	}
	
	/**
	 * Test method for {@link asgn2Passengers#cancelSeat(int)}
	 */
	@Test(expected = PassengerException.class)
	public void testCancelSeatisFlown() throws PassengerException {
		First cancelisFlown= new First(2,10);
		cancelisFlown.flyPassenger(1);
		cancelisFlown.cancelSeat(4);
	}
	
	/**
	 * Test method for {@link asgn2Passengers#cancelSeat(int)}
	 */
	@Test(expected = PassengerException.class)
	public void testCancelSeatCancellationTimeLess0() throws PassengerException {
		First cancelTimeLess0 = new First(2,10);
		cancelTimeLess0.confirmSeat(3, 10);
		cancelTimeLess0.cancelSeat(-1);
	}
	
	/**
	 * Test method for {@link asgn2Passengers#cancelSeat(int)}
	 */
	@Test(expected = PassengerException.class)
	public void testCancelSeatDepartureTimeLessCancellationTime() throws PassengerException {
		First cancelTimeLess0 = new First(2,10);
		cancelTimeLess0.confirmSeat(3, 10);
		cancelTimeLess0.cancelSeat(11);
	}
	
	/**
	 * Test method for {@link asgn2Passengers#confirmSeat(int, int)}
	 */
	@Test
	public void testConfirmSeatNormal() throws PassengerException {
		First confirmNormal = new First(2,10);
		confirmNormal.confirmSeat(3, 10);
		assertTrue(confirmNormal.isConfirmed());
	}
	
	/**
	 * Test method for {@link asgn2Passengers#confirmSeat(int, int)}
	 */
	@Test(expected = PassengerException.class)
	public void testConfirmSeatisConfirmed() throws PassengerException {
		First confirmisConfirm = new First(2,10);
		confirmisConfirm.confirmSeat(3, 10);
		confirmisConfirm.confirmSeat(4, 10);
	}
	
	/**
	 * Test method for {@link asgn2Passengers#confirmSeat(int, int)}
	 */
	@Test(expected = PassengerException.class)
	public void testConfirmSeatisRefused() throws PassengerException {
		First confirmisRefused = new First(2,10);
		confirmisRefused.refusePassenger(1);
		confirmisRefused.confirmSeat(3, 10);
	}
	
	/**
	 * Test method for {@link asgn2Passengers#confirmSeat(int, int)}
	 */
	@Test(expected = PassengerException.class)
	public void testConfirmSeatisFlown() throws PassengerException {
		First confirmisFlown = new First(2,10);
		confirmisFlown.flyPassenger(8);
		confirmisFlown.confirmSeat(3, 10);
	}
	
	/**
	 * Test method for {@link asgn2Passengers#confirmSeat(int, int)}
	 */
	@Test(expected = PassengerException.class)
	public void testConfirmSeatConfirmationTimeLess0() throws PassengerException {
		First confirmLess0 = new First(2,10);
		confirmLess0.confirmSeat(-1, 10);
	}
	
	/**
	 * Test method for {@link asgn2Passengers#confirmSeat(int, int)}
	 */
	@Test(expected = PassengerException.class)
	public void testConfirmSeatDepartureTimeLessConfirmationTime() throws PassengerException {
		First confirmDepartureLessConfirm = new First(2,10);
		confirmDepartureLessConfirm.confirmSeat(4, 3);
	}
	
	/**
	 * Test method for {@link asgn2Passengers#flyPassenger(int)}
	 */
	@Test
	public void testFlyPassengerNormal() throws PassengerException {
		First flyNormal = new First(2,10);
		flyNormal.confirmSeat(3, 10);
		flyNormal.flyPassenger(10);
		assertTrue(flyNormal.isFlown());
		
	}
	
	/**
	 * Test method for {@link asgn2Passengers#flyPassenger(int)}
	 */
	@Test(expected = PassengerException.class)
	public void testFlyPassengerisNew() throws PassengerException {
		First flyisNew = new First(2,10);
		flyisNew.flyPassenger(10);
	}
	
	/**
	 * Test method for {@link asgn2Passengers#flyPassenger(int)}
	 */
	@Test(expected = PassengerException.class)
	public void testFlyPassengerisQueued() throws PassengerException {
		First flyisQueued = new First(2,10);
		flyisQueued.queuePassenger(3, 10);
		flyisQueued.flyPassenger(10);
	}
	
	/**
	 * Test method for {@link asgn2Passengers#flyPassenger(int)}
	 */
	@Test(expected = PassengerException.class)
	public void testFlyPassengerisRefused() throws PassengerException {
		First flyisRefused = new First(2,10);
		flyisRefused.refusePassenger(8);
		flyisRefused.flyPassenger(10);
	}
	
	/**
	 * Test method for {@link asgn2Passengers#flyPassenger(int)}
	 */
	@Test(expected = PassengerException.class)
	public void testFlyPassengerisFlown() throws PassengerException {
		First flyNormal = new First(2,10);
		flyNormal.confirmSeat(3, 10);
		flyNormal.flyPassenger(10);
		flyNormal.flyPassenger(11);
	}
	
	/**
	 * Test method for {@link asgn2Passengers#flyPassenger(int)}
	 */
	@Test(expected = PassengerException.class)
	public void testFlyPassengerDepartureLessorEqual0() throws PassengerException {
		First flyDepartureLess1 = new First(2,10);
		flyDepartureLess1.confirmSeat(3, 10);
		flyDepartureLess1.flyPassenger(0);
	}
	
	/**
	 * Test method for {@link asgn2Passengers#queuePassenger(int, int)}
	 */
	@Test
	public void testqueuePassengerNormal() throws PassengerException {
		First queueNormal = new First(2,10);
		queueNormal.queuePassenger(3, 10);
		assertTrue(queueNormal.isQueued());
		
	}
	
	/**
	 * Test method for {@link asgn2Passengers#queuePassenger(int, int)}
	 */
	@Test(expected = PassengerException.class)
	public void testqueuePassengerisQueued() throws PassengerException {
		First queueisQueued = new First(2,10);
		queueisQueued.queuePassenger(3, 10);
		queueisQueued.queuePassenger(3, 10);
	}
	
	/**
	 * Test method for {@link asgn2Passengers#queuePassenger(int, int)}
	 */
	@Test(expected = PassengerException.class)
	public void testqueuePassengerisConfirmed() throws PassengerException {
		First queueisConfirmed = new First(2,10);
		queueisConfirmed.confirmSeat(3, 10);
		queueisConfirmed.queuePassenger(4, 10);
	}
	
	/**
	 * Test method for {@link asgn2Passengers#queuePassenger(int, int)}
	 */
	@Test(expected = PassengerException.class)
	public void testqueuePassengerisRefused() throws PassengerException {
		First queueisRefused = new First(2,10);
		queueisRefused.refusePassenger(3);
		queueisRefused.queuePassenger(4, 10);
	}
	
	/**
	 * Test method for {@link asgn2Passengers#queuePassenger(int, int)}
	 */
	@Test(expected = PassengerException.class)
	public void testqueuePassengerisFlown() throws PassengerException {
		First queueisFlown = new First(2,10);
		queueisFlown.flyPassenger(10);
		queueisFlown.queuePassenger(4, 10);
	}
	
	/**
	 * Test method for {@link asgn2Passengers#queuePassenger(int, int)}
	 */
	@Test(expected = PassengerException.class)
	public void testqueuePassengerqueueTimeLess0() throws PassengerException {
		First queueTimeLess0 = new First(2,10);
		queueTimeLess0.queuePassenger(-1, 10);
	}
	
	/**
	 * Test method for {@link asgn2Passengers#queuePassenger(int, int)}
	 */
	@Test(expected = PassengerException.class)
	public void testqueuePassengerDepartureTimeLessQueueTime() throws PassengerException {
		First queueDepartureLessQueue = new First(2,10);
		queueDepartureLessQueue.queuePassenger(3, 2);
	}
	
	/**
	 * Test method for {@link asgn2Passengers#refusePassenger(int)}
	 */
	@Test
	public void testrefusePassengerNew() throws PassengerException {
		First queueNew = new First(2,10);
		queueNew.refusePassenger(3);
	}
	
	/**
	 * Test method for {@link asgn2Passengers#refusePassenger(int)}
	 */
	@Test
	public void testrefusePassengerQueued() throws PassengerException {
		First queueQueued = new First(2,10);
		queueQueued.queuePassenger(3, 10);
		queueQueued.refusePassenger(4);
	}
	
	/**
	 * Test method for {@link asgn2Passengers#refusePassenger(int)}
	 */
	@Test(expected = PassengerException.class)
	public void testrefusePassengerisConfirmed() throws PassengerException {
		First queueisConfiremd = new First(2,10);
		queueisConfiremd.confirmSeat(3, 10);
		queueisConfiremd.refusePassenger(4);
	}
	
	/**
	 * Test method for {@link asgn2Passengers#refusePassenger(int)}
	 */
	@Test(expected = PassengerException.class)
	public void testrefusePassengerisRefused() throws PassengerException {
		First queueisRefused = new First(2,10);
		queueisRefused.refusePassenger(3);
		queueisRefused.refusePassenger(3);
	}
	
	/**
	 * Test method for {@link asgn2Passengers#refusePassenger(int)}
	 */
	@Test(expected = PassengerException.class)
	public void testrefusePassengerisFlown() throws PassengerException {
		First queueisFlown = new First(2,10);
		queueisFlown.flyPassenger(10);
		queueisFlown.refusePassenger(3);
	}
	
	/**
	 * Test method for {@link asgn2Passengers#refusePassenger(int)}
	 */
	@Test(expected = PassengerException.class)
	public void testrefusePassengerRefusalLess0() throws PassengerException {
		First queueRefusalLess0 = new First(2,10);
		queueRefusalLess0.refusePassenger(-1);
	}
	
	/**
	 * Test method for {@link asgn2Passengers#refusePassenger(int)}
	 */
	@Test(expected = PassengerException.class)
	public void testrefusePassengerRefusaltimeLessBookingTime() throws PassengerException {
		First queueRefusalLessBooking = new First(2,10);
		queueRefusalLessBooking.refusePassenger(1);
	}
}
