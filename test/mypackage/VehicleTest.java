package mypackage;

//java lib
import java.util.Date;
import java.lang.RuntimeException;
import java.util.Calendar;

// junit 
//import org.junit.Before;
//import org.junit.After;
import org.junit.Test;

import junit.framework.Assert;

// my classes 
import mypackage.Vehicle;

@SuppressWarnings({"unused", "deprecation"})
public class VehicleTest {

//	@Before
//	public void setUp() throws Exception {
//		System.out.println("VehicleTest @Before");
//	}
	
//	@After
//	public void tearDown() throws Exception {
//		System.out.println("VehicleTest @After");
//	}
	
	@Test(expected = Exception.class)
	public void createVehicleWithoutProducerName() throws Exception {
		Vehicle vehicle = new Vehicle(null, getYesterdaysDate(), 14141);
	}
	
	@Test(expected = Exception.class)
	public void createVehicleWithEmptyProducerName() throws Exception {
		Vehicle vehicle = new Vehicle("", getYesterdaysDate(), 14141);
	}
	
	@Test(expected = Exception.class)
	public void createVehicleWithTooLongProducerName() throws Exception {
		Vehicle vehicle = new Vehicle("This is a too long producer name that fails the validation", getYesterdaysDate(), 14141);
	}
	
	@Test(expected = Exception.class)
	public void createVehicleWithoutDate() throws Exception {
		Vehicle vehicle = new Vehicle("Toyota", null, 14141);
	}
	
	@Test(expected = Exception.class)
	public void createVehicleWithTomorrowsDate() throws Exception {
		Vehicle vehicle = new Vehicle("Toyota", getTomorrowsDate(), 14141);
	}
	
	@Test(expected = Exception.class)
	public void createWithTooBigMileageValue() throws Exception {
		Vehicle vehicle = new Vehicle("Toyota", getYesterdaysDate(), 999999 + 1);
	}
	
	@Test(expected = Exception.class)
	public void createVehicleWithNegativeMileage() throws Exception {
		Vehicle vehicle = new Vehicle("Toyota", getYesterdaysDate(), -1);
	}

	@Test(expected = Exception.class)
	public void setNegativeMileageValue() throws Exception {
		Vehicle vehicle = new Vehicle("Toyota", getYesterdaysDate(), 999999);
		vehicle.setMileage(-1);		
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
