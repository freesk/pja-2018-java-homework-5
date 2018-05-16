package mypackage;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class ScopeTest {
	
	ArrayList<Map<String, Map<String, String>>> collection = null;
	Map<String, Map<String, String>> foo = null;
	Map<String, String> bar = null;
	Vehicle vehicle = null;
	
	@Before
	public void setUp() throws Exception {		
		collection = new ArrayList<Map<String, Map<String, String>>>();
		foo = new HashMap<String, Map<String, String>>();
		bar = new HashMap<String, String>();
		
		vehicle = new Vehicle("Toyota", getYesterdaysDate(), 999999);	
		
		bar.put("nameOfTheProducer", vehicle.getNameOfTheProducer());
		bar.put("mileage", vehicle.getMileage() + "");
		
		// convert long into String
		String s = Objects.toString(vehicle.getDateOfManufactureInMs(), null);
		
		bar.put("dateOfManufactureInMs", s);
		bar.put("isLowFloor", vehicle.isLowFloor + "");
		bar.put("isArticulated", vehicle.isArticulated + "");
		
		foo.put("Vehicle", bar);
		
		collection.add(foo);
	}
	
	@After 
	public void tearDown() throws Exception {
		if (Scope.vehicles != null) Scope.vehicles.clear();
	}

	@Test
	public void vehicleInitializationLengthTest() {
		Scope.initialize(collection);		
		Assert.assertEquals(1, Scope.vehicles.size());		
	}
	
	@Test
	public void vehicleInitializationComparison() {
		Scope.initialize(collection);
		Vehicle v = Scope.vehicles.get(0);
		
		String s1 = v.toString();
		String s2 = vehicle.toString();
		
		// here we assume that all attributes of the dummy vehicle we have created 
		// must be equal to the attributes of the one initialized by the class Scope
		Assert.assertEquals(true, s1.equals(s2));		
	}
	
	@Test 
	public void getDumpableLengthTest() {
		// bypass the initialization and add one vehicle
		Scope.vehicles.add(vehicle);
		ArrayList<Map<String, Map<String, String>>> c = Scope.getDumpable();		
		Assert.assertEquals(1, c.size());
	}
	
	@Test 
	public void getDumpableComparison() {
		// bypass the initialization
		Scope.vehicles.add(vehicle);
		ArrayList<Map<String, Map<String, String>>> c = Scope.getDumpable();
		// the dumpable created by the Scope must be equal to the one 
		// we have filled manually in the @Before method
		Assert.assertEquals(true, collection.equals(c));
	}
	
	private Date getYesterdaysDate() {
		Date date = new Date();
		Calendar c = Calendar.getInstance(); 
		c.setTime(date); 
		c.add(Calendar.DATE, -1);
		date = c.getTime();
		
		return date;
	}

}
