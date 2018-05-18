package mypackage;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class PersistenceUtilityTest {
	
	private ArrayList<Map<String, Map<String, String>>> collection = null;
	private Map<String, Map<String, String>> foo = null;
	private Map<String, String> bar = null;
	
	private String TEMP_TEST_FILE_NAME = "data.temp.txt";
	private String TEST_CORRUPTED_FILE_NAME = "data.test.corrupted.txt"; 
	private String TEST_FILE_NAME = "data.test.txt";
	
	// these must match the data.test.txt content
	private final String DATE_OF_CREATION = "1524334634587"; 
	private final String MILEAGE = "99999";
	private final String PRODUCER = "Honda";
	private final String IS_ARTICULATED = "true";
	private final String IS_LOW_FLOOR = "true";
	private final String CLASS_NAME = "Train";
	
	@Before
	public void setUp() throws Exception {	
		collection = new ArrayList<Map<String, Map<String, String>>>();
		foo = new HashMap<String, Map<String, String>>();
		bar = new HashMap<String, String>();	
		
		bar.put("nameOfTheProducer", PRODUCER);
		bar.put("mileage", MILEAGE);
		bar.put("dateOfManufactureInMs", DATE_OF_CREATION);
		bar.put("isLowFloor", IS_LOW_FLOOR);
		bar.put("isArticulated", IS_ARTICULATED);
		
		foo.put(CLASS_NAME, bar);
		
		collection.add(foo);	
	}
	
	@After 
	public void tearDown() throws Exception {
		if (collection != null) collection = null;
		// remove the temp file if it's there
		File f = new File(TEMP_TEST_FILE_NAME);
		if (f.exists() && !f.isDirectory()) f.delete();
	}
	
	@Test
	public void readCorruptedData() {
		ArrayList<Map<String, Map<String, String>>> hm = PersistenceUtility.load(TEST_CORRUPTED_FILE_NAME);
		// make sure it's empty 
		Assert.assertEquals(0, hm.size());
	}
	
	@Test
	public void readDataLength() {
		ArrayList<Map<String, Map<String, String>>> hm = PersistenceUtility.load(TEST_FILE_NAME);
		// make sure it can read the data at all 
		Assert.assertEquals(1, hm.size());
	}

	@Test
	public void readDataComparison() {
		// we load predefined data file with already known content 
		ArrayList<Map<String, Map<String, String>>> hm = PersistenceUtility.load(TEST_FILE_NAME);
		// compare it reads date the way we it's supposed to 
		Map<String, Map<String, String>> foo = hm.get(0);
		Map<String, Map<String, String>> bar = collection.get(0);
		Assert.assertEquals(true, foo.equals(bar));
	}
	
	@Test
	public void dataStoreComparison() {
		// store the constants in a file 
		PersistenceUtility.store(TEMP_TEST_FILE_NAME, collection);
		// can I cross test methods like that at all? 
		ArrayList<Map<String, Map<String, String>>> collection = PersistenceUtility.load(TEMP_TEST_FILE_NAME);
		
		Map<String, Map<String, String>> foo = collection.get(0);
		Map<String, Map<String, String>> bar = this.collection.get(0);
		
		Assert.assertEquals(true, foo.equals(bar));
	}

}
