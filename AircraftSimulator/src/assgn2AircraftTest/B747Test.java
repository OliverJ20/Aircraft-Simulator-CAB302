package assgn2AircraftTest;

import static org.junit.Assert.*;

import org.junit.Test;

import asgn2Aircraft.AircraftException;
import asgn2Aircraft.B747;
import asgn2Passengers.PassengerException;
/** 
 * @author Oliver
 *
 */
public class B747Test {	

	/**
	 * Test method for {@link asgn2Aircraft.B747#B747(string, int)} && {@link asgn2Aircraft.B747#B747(string, int, int, int, int, int)}
	 * @throws PassengerException 
	 */
	@Test
	public void testB747Normal() throws AircraftException {
        B747 testB747 = new B747("400", 10);
        assertEquals(testB747.toString(), "B747:400:10 Count: 0 [F: 0 J: 0 P: 0 Y: 0]");
    }
     
	/**
	 * Test method for {@link asgn2Aircraft.B747#B747(string, int)} 
	 * @throws PassengerException 
	 */
    @Test (expected = AircraftException.class)
    public void testB747InvalidflightCode() throws AircraftException {
        B747 testB747 = new B747(null, 10);
    }
     
    /**
	 * Test method for {@link asgn2Aircraft.B747#B747(string, int)} 
	 * @throws PassengerException 
	 */
    @Test (expected = AircraftException.class)
    public void testB747InvaliddepartureTime() throws AircraftException {
        B747 testB747 = new B747("400", 0);
    }
     
    /**
	 * Test method for {@link asgn2Aircraft.B747#B747(string, int)} && {@link asgn2Aircraft.B747#B747(string, int, int, int, int, int)}
	 * @throws PassengerException 
	 */
    @Test
    public void testB747CustomNormal() throws AircraftException {
        B747 testB747 = new B747("400", 10, 10,10,10,10);
        assertEquals(testB747.toString(), "B747:400:10 Count: 0 [F: 0 J: 0 P: 0 Y: 0]");
    }
    
    /**
	 * Test method for {@link asgn2Aircraft.B747#B747(string, int, int, int, int, int)
	 * @throws PassengerException 
	 */
    @Test (expected = AircraftException.class)
    public void testB747CustomInvalidflightCode() throws AircraftException {
        B747 testB747 = new B747(null, 10,10,10,10,10);
    }
     
    /**
	 * Test method for {@link asgn2Aircraft.B747#B747(string, int, int, int, int, int)
	 * @throws PassengerException 
	 */
    @Test (expected = AircraftException.class)
    public void testB747CustomInvaliddepartureTime() throws AircraftException {
        B747 testB747 = new B747("400", 0, 10,10,10,10);
    }
     
    /**
	 * Test method for {@link asgn2Aircraft.B747#B747(string, int, int, int, int, int)
	 * @throws PassengerException 
	 */
    @Test (expected = AircraftException.class)
    public void testB747CustomInvalidFIRST() throws AircraftException {
        B747 testB747 = new B747("400", 10, -10,10,10,10);
    }

    /**
	 * Test method for {@link asgn2Aircraft.B747#B747(string, int, int, int, int, int)
	 * @throws PassengerException 
	 */
    @Test (expected = AircraftException.class)
    public void testB747CustomInvalidBUSINESS() throws AircraftException {
        B747 testB747 = new B747("400", 10, 10,-10,10,10);
    }
     
    /**
	 * Test method for {@link asgn2Aircraft.B747#B747(string, int, int, int, int, int)
	 * @throws PassengerException 
	 */
    @Test (expected = AircraftException.class)
    public void testB747CustomInvalidPREMIUM() throws AircraftException {
        B747 testB747 = new B747("400", 10, 10,10,-10,10);
    }
     
    /**
	 * Test method for {@link asgn2Aircraft.B747#B747(string, int, int, int, int, int)
	 * @throws PassengerException 
	 */
    @Test (expected = AircraftException.class)
    public void testB747CustomInvalidECONOMY() throws AircraftException {
        B747 testB747 = new B747("400", 10, 10,10,10,-10);
    }
}
