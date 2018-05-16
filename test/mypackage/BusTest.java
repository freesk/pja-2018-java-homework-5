package mypackage;

import java.util.Calendar;
import java.util.Date;
import java.lang.RuntimeException;

import static org.junit.Assert.*;

import org.junit.Test;

import mypackage.Bus;

@SuppressWarnings({"unused"})
public class BusTest {

	@Test(expected = Exception.class)
	public void createBusWithNegativeEngineCapacity() throws Exception {
		Bus bus = new Bus("Mercedes-Benz", getYesterdaysDate(), 151515, -1, 0);
	}
	
	@Test(expected = Exception.class)
	public void createBusWithInvalidFuelType() throws Exception {
		Bus bus = new Bus("Mercedes-Benz", getYesterdaysDate(), 151515, 2000, -1);
		bus = new Bus("Mercedes-Benz", getYesterdaysDate(), 151515, 2000, 100);
	}
	
	private Date getYesterdaysDate() {
		Date date = new Date();
		Calendar c = Calendar.getInstance(); 
		c.setTime(date); 
		c.add(Calendar.DATE, -1);
		date = c.getTime();
		
		return date;
	}
	
	private Date getTomorrowsDate() {
		Date date = new Date();
		Calendar c = Calendar.getInstance(); 
		c.setTime(date); 
		c.add(Calendar.DATE, 1);
		date = c.getTime();
		
		return date;
	}

}
