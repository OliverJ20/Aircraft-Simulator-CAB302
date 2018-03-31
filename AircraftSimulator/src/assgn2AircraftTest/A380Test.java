package assgn2AircraftTest;

import static org.junit.Assert.*;

import org.junit.Test;
import asgn2Aircraft.Aircraft;
import asgn2Aircraft.AircraftException;
import asgn2Aircraft.A380;
import asgn2Passengers.Business;
import asgn2Passengers.Economy;
import asgn2Passengers.First;
import asgn2Passengers.Passenger;
import asgn2Passengers.PassengerException;
import asgn2Passengers.Premium;

/** 
 * @author Oliver
 *
 */
public class A380Test  {
	
	/**
	 * Test method for {@link asgn2Aircraft.A380#A380(string, int)} {@link asgn2Aircraft.A380#A380(string, int, int, int, int, int)}
	 * @throws PassengerException 
	 */
	@Test
    public void testA380Normal() throws AircraftException {
        A380 testA380 = new A380("400", 10);
        assertEquals(testA380.toString(), "A380:400:10 Count: 0 [F: 0 J: 0 P: 0 Y: 0]");
    }
	
	/**
	 * Test method for {@link asgn2Aircraft.A380#A380(string, int)}
	 * @throws PassengerException 
	 */ 
    @Test (expected = AircraftException.class)
    public void testA380InvalidflightCode() throws AircraftException {
        A380 testA380 = new A380(null, 10);
    }
    
	/**
	 * Test method for {@link asgn2Aircraft.A380#A380(string, int)} 
	 * @throws PassengerException 
	 */
    @Test (expected = AircraftException.class)
    public void testA380InvaliddepartureTime() throws AircraftException {
        A380 testA380 = new A380("400", 0);
    }
    
	/**
	 * Test method for {@link asgn2Aircraft.A380#A380(string, int, int, int, int, int)}
	 * @throws PassengerException 
	 */
    @Test
    public void testA380CustomNormal() throws AircraftException {
        A380 testA380 = new A380("400", 10, 10,10,10,10);
        assertEquals(testA380.toString(), "A380:400:10 Count: 0 [F: 0 J: 0 P: 0 Y: 0]");
    }
     
	/**
	 * Test method for {@link asgn2Aircraft.A380#A380(string, int, int, int, int, int)}
	 * @throws PassengerException 
	 */
    @Test (expected = AircraftException.class)
    public void testA380CustomInvalidflightCode() throws AircraftException {
        A380 testA380 = new A380(null, 10,10,10,10,10);
    }
    
	/**
	 * Test method for {@link asgn2Aircraft.A380#A380(string, int, int, int, int, int)}
	 * @throws PassengerException 
	 */
    @Test (expected = AircraftException.class)
    public void testA380CustomInvaliddepartureTime() throws AircraftException {
        A380 testA380 = new A380("400", 0, 10,10,10,10);
    }
     
    /**
	 * Test method for {@link asgn2Aircraft.A380#A380(string, int, int, int, int, int)}
	 * @throws PassengerException 
	 */
    @Test (expected = AircraftException.class)
    public void testA380CustomInvalidFIRST() throws AircraftException {
        A380 testA380 = new A380("400", 10, -10,10,10,10);
    }
     
    /**
	 * Test method for {@link asgn2Aircraft.A380#A380(string, int, int, int, int, int)}
	 * @throws PassengerException 
	 */
    @Test (expected = AircraftException.class)
    public void testA380CustomInvalidBUSINESS() throws AircraftException {
        A380 testA380 = new A380("400", 10, 10,-10,10,10);
    }
     
    /**
	 * Test method for {@link asgn2Aircraft.A380#A380(string, int, int, int, int, int)}
	 * @throws PassengerException 
	 */
    @Test (expected = AircraftException.class)
    public void testA380CustomInvalidPREMIUM() throws AircraftException {
        A380 testA380 = new A380("400", 10, 10,10,-10,10);
    }
     
    /**
	 * Test method for {@link asgn2Aircraft.A380#A380(string, int, int, int, int, int)}
	 * @throws PassengerException 
	 */
    @Test (expected = AircraftException.class)
    public void testA380CustomInvalidECONOMY() throws AircraftException {
        A380 testA380 = new A380("400", 10, 10,10,10,-10);
    }
    


    /**
     * Test method for {@link asgn2Aircraft.cancelBooking(Passenger, int)}
     */
    @Test
    public void testCancelBookingNormal() throws AircraftException, PassengerException {
        A380 cancelNormal = new A380("400", 10);
        Passenger Passenger = new Economy(4, 10); 
        cancelNormal.confirmBooking(Passenger, 6);
        cancelNormal.cancelBooking(Passenger, 7 );
        assertTrue(Passenger.isNew());
    }
    
    /**
     * Test method for {@link asgn2Aircraft.cancelBooking(Passenger, int)}  
     */
    @Test (expected = AircraftException.class )
    public void testCancelBookingisConfirmed() throws PassengerException, AircraftException  {
        A380 cancelNormal = new A380("400", 10); 
        Passenger Passenger = new Economy(4, 10); 
        cancelNormal.cancelBooking(Passenger, 5 );
    }
    
    /**
     * Test method for {@link asgn2Aircraft.cancelBooking(Passenger, int)} 
     */ 
    @Test (expected = AircraftException.class)
    public void testCancelBookingCancellationLess0() throws PassengerException, AircraftException  {
        A380 cancelNormal = new A380("400", 10); 
        Passenger Passenger = new Economy(4, 10); 
        cancelNormal.confirmBooking(Passenger, 6);
        cancelNormal.cancelBooking(Passenger, 0 );
    }    
    
    /**
     * Test method for {@link asgn2Aircraft.cancelBooking(Passenger, int)} 
     */
    @Test (expected = AircraftException.class)
    public void testCancelBookinghasPassenger() throws PassengerException, AircraftException  {
        A380 cancelNormal = new A380("400", 10); 
        Passenger Passenger = new Economy(4, 10); 
        cancelNormal.cancelBooking(Passenger, 7);
    }	    
	
    /**
     * Test method for {@link asgn2Aircraft.cancelBooking(Passenger, int)}
     */
	@Test 
	public void testCancelBookingEconomyTrue() throws PassengerException, AircraftException  {		
		Passenger Passenger = new Economy(4, 10); 
		Passenger.confirmSeat(5, 10);		
		Passenger.cancelSeat(6);		
		assertTrue(Passenger.isNew() && Passenger.getBookingTime() == 6);
		Passenger.confirmSeat(5, 10);
		Passenger.cancelSeat(5);		
		assertTrue(Passenger.isNew()); 		
		assertTrue(Passenger instanceof Economy);		
	}
	
	 /**
     * Test method for {@link asgn2Aircraft.cancelBooking(Passenger, int)}
     */
	@Test 
	public void testCancelBookingEconomyFalse() throws PassengerException, AircraftException  {		
		Passenger Passenger = new Economy(4, 10); 		
		Passenger.confirmSeat(5, 10);
		Passenger.cancelSeat(5);		
		assertTrue(Passenger.isNew()); 		
		assertFalse(Passenger instanceof Premium);
		assertFalse(Passenger instanceof Business);
		assertFalse(Passenger instanceof First);				
	}
	
	 /**
     * Test method for {@link asgn2Aircraft.cancelBooking(Passenger, int)}
     */
	@Test 
	public void testCancelBookingPremium() throws PassengerException, AircraftException  {		
		Passenger Passenger = new Premium(4, 10);
		Passenger.confirmSeat(4, 10);
		Passenger.cancelSeat(6);		
		assertTrue(Passenger.isNew() && Passenger.getBookingTime() == 6);
		Passenger.confirmSeat(5, 10);
		Passenger.cancelSeat(5);
		assertTrue(Passenger.isNew()); 
		assertTrue(Passenger instanceof Premium);		
	}
	
	 /**
     * Test method for {@link asgn2Aircraft.cancelBooking(Passenger, int)} 
     */
	@Test 
	public void testCancelBookingPremiumFalse() throws PassengerException, AircraftException  {		
		Passenger Passenger = new Premium(4, 10); 
		Passenger.confirmSeat(5, 10);
		Passenger.cancelSeat(5);
		assertTrue(Passenger.isNew()); 
		assertFalse(Passenger instanceof Economy);
		assertFalse(Passenger instanceof Business);
		assertFalse(Passenger instanceof First);		
	}
	
	 /**
     * Test method for {@link asgn2Aircraft.cancelBooking(Passenger, int)} 
     */
	@Test 
	public void testCancelBookingBusiness() throws PassengerException, AircraftException  {	
		Passenger Passenger = new Business(4, 10); 		
		Passenger.confirmSeat(5, 10);
		Passenger.cancelSeat(5);
		assertTrue(Passenger.isNew()); 
		assertTrue(Passenger instanceof Business);		
	}
	
	 /**
     * Test method for {@link asgn2Aircraft.cancelBooking(Passenger, int)} 
     */
	@Test 
	public void testCancelBookingBusinessFalse() throws PassengerException, AircraftException  {		
		Passenger Passenger = new Business(4, 10); 		
		Passenger.confirmSeat(5, 10);
		Passenger.cancelSeat(5);
		assertTrue(Passenger.isNew()); 
		assertFalse(Passenger instanceof Economy);
		assertFalse(Passenger instanceof Premium);
		assertFalse(Passenger instanceof First);		
	}
	
	 /**
     * Test method for {@link asgn2Aircraft.cancelBooking(Passenger, int)}    
     */
	@Test 
	public void testCancelBookingFirst() throws PassengerException, AircraftException  {		
		Passenger Passenger = new First(4, 10); 
		Passenger.confirmSeat(5, 10);
		Passenger.cancelSeat(5);		
		assertTrue(Passenger.isNew()); 
		assertTrue(Passenger instanceof First);		
	}
	
	 /**
     * Test method for {@link asgn2Aircraft.cancelBooking(Passenger, int)} 
     */
	@Test 
	public void testCancelBookingFirstFalse() throws PassengerException, AircraftException  {		 
		Passenger Passenger = new First(4, 10); 
		Passenger.confirmSeat(5, 10);
		Passenger.cancelSeat(5);	
		assertTrue(Passenger.isNew()); 
		assertFalse(Passenger instanceof Economy);
		assertFalse(Passenger instanceof Premium);
		assertFalse(Passenger instanceof Business);		
	}
	
	 /**
     * Test method for {@link asgn2Aircraft.confirmBooking(Passenger, int)}   
     */
	@Test
    public void testConfirmBookingNormal() throws AircraftException, PassengerException {
        A380 confirmNormal = new A380("400", 10);
        Passenger Passenger = new Economy(4,10);
        confirmNormal.confirmBooking(Passenger, 4);
        assertTrue(Passenger.isConfirmed());
    }
	
	 /**
     * Test method for {@link asgn2Aircraft.confirmBooking(Passenger, int)} 
     */
	@Test (expected = PassengerException.class)
    public void testConfirmBookingInvalidConfirmState() throws AircraftException, PassengerException {
        A380 confirmNormal = new A380("400", 10);
        Passenger Passenger = new Economy(4,10);
        Passenger.confirmSeat(5, 10);
        confirmNormal.confirmBooking(Passenger, 4);
    }
     
	 /**
     * Test method for {@link asgn2Aircraft.confirmBooking(Passenger, int)}  
     */
    @Test (expected = PassengerException.class)
    public void testConfirmBookingInvalidFlownState() throws AircraftException, PassengerException {
        A380 confirmNormal = new A380("400", 10);
        Passenger Passenger = new Economy(4,10);
        Passenger.confirmSeat(5, 10);
        Passenger.flyPassenger(10);
        confirmNormal.confirmBooking(Passenger, 4);
    }
     
    /**
     * Test method for {@link asgn2Aircraft.confirmBooking(Passenger, int)}  
     */
    @Test (expected = PassengerException.class)
    public void testConfirmBookingInvalidRefusalState() throws AircraftException, PassengerException {
        A380 confirmNormal = new A380("400", 10);
        Passenger Passenger = new Economy(4,10);
        Passenger.refusePassenger(5);
        confirmNormal.confirmBooking(Passenger, 4);
    }
     
    /**
     * Test method for {@link asgn2Aircraft.confirmBooking(Passenger, int)}   
     */
    @Test (expected = PassengerException.class)
    public void testConfirmBookingInvalidDepartureTime() throws AircraftException, PassengerException {
        A380 confirmNormal = new A380("400", 10);
        Passenger Passenger = new Economy(4,0);
        confirmNormal.confirmBooking(Passenger, 4);
    }
    
    /**
     * Test method for {@link asgn2Aircraft.confirmBooking(Passenger, int)}
     */
    @Test (expected = PassengerException.class)
    public void testConfirmBookingInvalidConfirmationTime() throws AircraftException, PassengerException {
        A380 confirmNormal = new A380("400", 10);
        Passenger Passenger = new Economy(4,10);
        confirmNormal.confirmBooking(Passenger, -1);
    }
    
    /**
     * Test method for {@link asgn2Aircraft.confirmBooking(Passenger, int)}  
     */
	@Test
	public void testConfirmBookingClassCap() throws AircraftException, PassengerException{
		A380 confirmNormal = new A380("400", 10);
		Passenger Passenger = new Economy(4, 10); 
		assertTrue(confirmNormal.seatsAvailable(Passenger));		
	}
	
	 /**
     * Test method for {@link asgn2Aircraft.confirmBooking(Passenger, int)}    
     */
	@Test
	public void testConfirmBookingAddSeats() throws AircraftException, PassengerException{
		A380 confirmNormal = new A380("400", 10);
		Passenger Passenger = new Economy(4, 10); 		
		assertTrue(confirmNormal.getPassengers().add(Passenger));		
	}
	
	 /**
     * Test method for {@link asgn2Aircraft.confirmBooking(Passenger, int)}
     */
	@Test 
	public void testConfirmBookingFirst() throws PassengerException, AircraftException  {		
		Passenger Passenger = new First(4, 10); 
		Passenger.confirmSeat(10, Passenger.getDepartureTime());		
		assertTrue(Passenger instanceof First);		
	}
	
	 /**
     * Test method for {@link asgn2Aircraft.confirmBooking(Passenger, int)} 
     */
	@Test 
	public void testConfirmBookingFirstFalse() throws PassengerException, AircraftException  {		
		Passenger Passenger = new First(4, 10); 
		Passenger.confirmSeat(10, Passenger.getDepartureTime());		
		assertFalse(Passenger instanceof Economy);
		assertFalse(Passenger instanceof Premium);
		assertFalse(Passenger instanceof Business);		
	}
	
	 /**
     * Test method for {@link asgn2Aircraft.confirmBooking(Passenger, int)}
     */
	@Test 
	public void testConfirmBookingBusiness() throws PassengerException, AircraftException  {		
		Passenger Passenger = new Business(4, 10); 
		Passenger.confirmSeat(10, Passenger.getDepartureTime());		
		assertTrue(Passenger instanceof Business);		
	}
	
	 /**
     * Test method for {@link asgn2Aircraft.confirmBooking(Passenger, int)}
     */
	@Test 
	public void testConfirmBookingBusinessFalse() throws PassengerException, AircraftException  {		
		Passenger Passenger = new Business(4, 10); 
		Passenger.confirmSeat(10, Passenger.getDepartureTime());		
		assertFalse(Passenger instanceof Economy);
		assertFalse(Passenger instanceof Premium);
		assertFalse(Passenger instanceof First);		
	}
	
	 /**
     * Test method for {@link asgn2Aircraft.confirmBooking(Passenger, int)}
     */
	@Test
	public void testConfirmBookingPremium() throws PassengerException, AircraftException  {	
		Passenger Passenger = new Premium(4, 10); 
		Passenger.confirmSeat(10, Passenger.getDepartureTime());		
		assertTrue(Passenger instanceof Premium);		
	}
	
	 /**
     * Test method for {@link asgn2Aircraft.confirmBooking(Passenger, int)}
     */
	@Test 
	public void testConfirmBookingPremiumFalse() throws PassengerException, AircraftException  {	 
		Passenger Passenger = new Premium(4, 10); 
		Passenger.confirmSeat(10, Passenger.getDepartureTime());		
		assertFalse(Passenger instanceof Economy);
		assertFalse(Passenger instanceof Business);
		assertFalse(Passenger instanceof First);		
	}
	
	 /**
     * Test method for {@link asgn2Aircraft.confirmBooking(Passenger, int)} 
     */
	@Test 
	public void testConfirmBookingEconomy() throws PassengerException, AircraftException  {	
		Passenger Passenger = new Economy(4, 10); 
		Passenger.confirmSeat(10, Passenger.getDepartureTime());		
		assertTrue(Passenger instanceof Economy);		
	}
	
	 /**
     * Test method for {@link asgn2Aircraft.confirmBooking(Passenger, int)}
     */
	@Test 
	public void testConfirmBookingEconomyFalse() throws PassengerException, AircraftException  {	
		Passenger Passenger = new Economy(4, 10); 
		Passenger.confirmSeat(10, Passenger.getDepartureTime());		
		assertFalse(Passenger instanceof Premium);
		assertFalse(Passenger instanceof Business);
		assertFalse(Passenger instanceof First);		
	}
	
	/**
	 * Test method for {@link asgn2Aircraft.flightEmpty()}	
	 */
	@Test
	public void testFlightEmptyTrue() throws AircraftException {		
		A380 flightEmptyNormal = new A380 ("400", 10);		
		assertTrue(flightEmptyNormal.flightEmpty());
	}
	
	/**
	 * Test method for {@link asgn2Aircraft.flightEmpty()}	
	 */
	@Test
	public void testFlightEmptyFalse() throws AircraftException, PassengerException {		
		A380 flightEmptyNormal = new A380 ("400", 10);		
		Passenger Passenger = new Economy(4, 10); 
		flightEmptyNormal.confirmBooking(Passenger, 10);	
		assertFalse(flightEmptyNormal.flightEmpty());
	}
	
	/**
	 * Test method for {@link asgn2Aircraft.flightFull()}	
	 */
	@Test
	public void testFlightFullTrue() throws AircraftException, PassengerException {		
		A380 flightFullNormal = new A380 ("400", 10);
		for (int i = 0; i < 371; i++)
		{
			Passenger PassengerE = new Economy(4, 10); 
			flightFullNormal.confirmBooking(PassengerE, 10);
		}
		for (int i = 0; i < 35 ; i++)
		{
			Passenger PassengerP = new Premium(4, 10); 
			flightFullNormal.confirmBooking(PassengerP, 10);
			
		}
		for (int i = 0; i < 64; i++)
		{
			Passenger PassengerB = new Business(4, 10); 
			flightFullNormal.confirmBooking(PassengerB, 10);
		}
		for (int i = 0; i < 14; i++)
		{
			Passenger PassengerF = new First(4, 10); 
			flightFullNormal.confirmBooking(PassengerF, 10);
		}	
		assertTrue(flightFullNormal.flightFull());
	}
	
	/**
	 * Test method for {@link asgn2Aircraft.flightFull()}	
	 */
	@Test
	public void testFlightFullFalse() throws AircraftException, PassengerException {		
		A380 flightFullNormal = new A380 ("400", 10);
		Passenger Passenger = new Economy(4, 10); 
		flightFullNormal.confirmBooking(Passenger, 10);		
		assertFalse(flightFullNormal.flightFull());
	}
	
	/**
	 * Test method for {@link asgn2Aircraft.flyPassengers(int)}
	 */
	@Test
	public void testFlyPassengersTrue() throws AircraftException, PassengerException {	
		A380 flyPassengersNormal = new A380 ("400", 10);
		Passenger Passenger = new Economy(4, 10);		
		flyPassengersNormal.confirmBooking(Passenger, 10);	
		flyPassengersNormal.flyPassengers(Passenger.getDepartureTime());		
		assertTrue(Passenger.isFlown());			
	}
	
	/**
	 * Test method for {@link asgn2Aircraft.flyPassengers(int)}	
	 */
	@Test
	public void testFlyPassengersFalse() throws AircraftException, PassengerException {		
		A380 flyPassengersNormal = new A380 ("400", 10);
		Passenger Passenger = new Economy(4, 10); 		
		flyPassengersNormal.flyPassengers(Passenger.getDepartureTime());		
		assertFalse(Passenger.isFlown());		
	}

	/**
	 * Test method for {@link asgn2Aircraft.hasPassengers(Passenger)}	
	 */
	@Test
	public void testHasPassengerTrue() throws AircraftException, PassengerException{	
		A380 hasPassengerNormal = new A380 ("400", 10);
		Passenger Passenger = new Economy(4, 10); 		
		hasPassengerNormal.confirmBooking(Passenger, 10);		
		assertTrue(hasPassengerNormal.hasPassenger(Passenger));		
	}
	
	/**
	 * Test method for {@link asgn2Aircraft.hasPassengers(Passenger)}	
	 */
	@Test
	public void testHasPassengerFalse() throws AircraftException, PassengerException{	
		A380 hasPassengerNormal = new A380 ("400", 10);
		Passenger Passenger = new Economy(4, 10); 	
		assertFalse(hasPassengerNormal.hasPassenger(Passenger));
	}
	
	/**
	 * Test method for {@link asgn2Aircraft.seatsAvailable(Passenger)}	
	 */
	@Test
	public void testSeatsAvailableTrue() throws AircraftException, PassengerException {		
		A380 seatAvailableNormal = new A380 ("400", 10);
		Passenger Passenger = new Economy(4, 10); 		
		for (int i = 0; i < 370; i++)
		{
			Passenger PassengerE = new Economy(4, 10); 
			seatAvailableNormal.confirmBooking(PassengerE, 10);		
		}		
		assertTrue(seatAvailableNormal.seatsAvailable(Passenger));		
	}
	
	/**
	 * Test method for {@link asgn2Aircraft.seatsAvailable(Passenger)}	
	 */
	@Test
	public void testSeatsAvailableFalse() throws AircraftException, PassengerException {		
		A380 seatAvailableNormal = new A380 ("400", 10);
		Passenger Passenger = new Economy(4, 10); 		
		for (int i = 0; i < 371; i++)
		{
			Passenger PassengerE = new Economy(4, 10); 
			seatAvailableNormal.confirmBooking(PassengerE, 10);		
		}		
		assertFalse(seatAvailableNormal.seatsAvailable(Passenger));
	}
	
	/**
	 * Test method for {@link asgn2Aircraft.upgradeBookings()}	
	 */
	@Test
	public void testUpgradeBookingsTrue() throws AircraftException, PassengerException {	
		A380 upgradeBookingsNormal = new A380 ("400", 10);	
		Passenger PassengerFirst = new First(4, 10); 
		Passenger PassengerBusiness = new Business(4,10);
		Passenger PassengerPremium = new Premium(4, 10);
		Passenger PassengerEconomy = new Economy(4, 10);
		for (int i = 0; i < 371; i++)
		{
			Passenger PassengerE = new Economy(4, 10); 
			upgradeBookingsNormal.confirmBooking(PassengerE, 10);
		}
		for (int i = 0; i < 32 ; i++)
		{
			Passenger PassengerP = new Premium(4, 10); 
			upgradeBookingsNormal.confirmBooking(PassengerP, 10);
			
		}
		for (int i = 0; i < 60; i++)
		{
			Passenger PassengerB = new Business(4, 10); 
			upgradeBookingsNormal.confirmBooking(PassengerB, 10);
		}
		for (int i = 0; i < 10; i++)
		{
			Passenger PassengerF = new First(4, 10); 
			upgradeBookingsNormal.confirmBooking(PassengerF, 10);
			
		}
		upgradeBookingsNormal.upgradeBookings();		
		assertFalse(upgradeBookingsNormal.seatsAvailable(PassengerFirst));
		assertFalse(upgradeBookingsNormal.seatsAvailable(PassengerBusiness));
		assertFalse(upgradeBookingsNormal.seatsAvailable(PassengerPremium));
		assertTrue(upgradeBookingsNormal.seatsAvailable(PassengerEconomy));
	}
	
	/**
	 * Test method for {@link asgn2Aircraft.upgradeBookings()}	
	 */
	@Test
	public void testUpgradeBookingsFalse() throws AircraftException, PassengerException {		
		A380 upgradeBookingsNormal = new A380 ("400", 10);		
		Passenger PassengerFirst = new First(4, 10); 
		Passenger PassengerBusiness = new Business(4,10);
		Passenger PassengerPremium = new Premium(4, 10);
		Passenger PassengerEconomy = new Economy(4, 10);	
		for (int i = 0; i < 254; i++)
		{
			Passenger PassengerE = new Economy(4, 10); 
			upgradeBookingsNormal.confirmBooking(PassengerE, 10);
		}
		for (int i = 0; i < 31 ; i++)
		{
			Passenger PassengerP = new Premium(4, 10); 
			upgradeBookingsNormal.confirmBooking(PassengerP, 10);
			
		}
		for (int i = 0; i < 51; i++)
		{
			Passenger PassengerB = new Business(4, 10); 
			upgradeBookingsNormal.confirmBooking(PassengerB, 10);
		}
		for (int i = 0; i < 10; i++)
		{
			Passenger PassengerF = new First(4, 10); 
			upgradeBookingsNormal.confirmBooking(PassengerF, 10);			
		}		
		assertTrue(upgradeBookingsNormal.seatsAvailable(PassengerFirst));
		assertTrue(upgradeBookingsNormal.seatsAvailable(PassengerBusiness));
		assertTrue(upgradeBookingsNormal.seatsAvailable(PassengerPremium));
		assertTrue(upgradeBookingsNormal.seatsAvailable(PassengerEconomy));		
	}
	

}
