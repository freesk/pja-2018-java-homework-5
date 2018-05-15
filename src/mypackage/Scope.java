package mypackage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Scope {
	
	final static String MY_PACKAGE = "mypackage";
	
	public static ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
	
	public static void initialize(ArrayList<Map<String, Map<String, String>>> collection) {
		
		for (Map<String, Map<String, String>> m: collection) {
			
			 Map.Entry<String, Map<String, String>> entry = m.entrySet().iterator().next();
			 
			 final String className = entry.getKey();

			 try {
				 @SuppressWarnings("rawtypes")
				 Class c = Class.forName(MY_PACKAGE + "." + className);

				 // how much unsafe is it? 
				 Vehicle v = (Vehicle) c.newInstance();
				 
				 Map<String, String> attributes = entry.getValue();
				 
				 for (Map.Entry<String, String> e : attributes.entrySet()) {
					 
					 final String key = e.getKey();
					 final String value = e.getValue();
					 
//					 System.out.println(key + ":" + value);
					 
					 // some mapping is going on here
					 if (key.equals("nameOfTheProducer")) {
						 v.setNameOfTheProducer(value);
					 } else if (key.equals("mileage")) {		
						 v.setMileage(Integer.parseInt(value));
					 } else if (key.equals("dateOfManufactureInMs")) { 
						 v.setDateOfManufactureInMs(Long.valueOf(value));						 
					 } else if (key.equals("isLowFloor")) {
						 v.isLowFloor = Boolean.parseBoolean(value);
					 } else if (key.equals("isArticulated")) {
						 v.isArticulated = Boolean.parseBoolean(value);
					 } else if (key.equals("engineCapacity")) {
						 v.setCapacity(Integer.parseInt(value)); 
					 } else if (key.equals("fuelType")) {
						 v.setFuelType(Integer.parseInt(value));
					 }

				 }
				 
				 Scope.vehicles.add(v);
				 
			 } catch (ClassNotFoundException e) {
				 e.printStackTrace();
			 } catch (InstantiationException e) {
				 e.printStackTrace();
			 } catch (IllegalAccessException e) {
				 e.printStackTrace();
			 } catch (Exception e) {
				 e.printStackTrace();
			 }
						
		}
		
	}
	
	public static ArrayList<Map<String, Map<String, String>>> getDumpable() {
		ArrayList<Map<String, Map<String, String>>> collection = new ArrayList<Map<String, Map<String, String>>>();
		
		for (Vehicle v : vehicles) {
			Map<String, Map<String, String>> foo = new HashMap<String, Map<String, String>>();
			Map<String, String> bar = new HashMap<String, String>();
			
			final String className = v.getClass().getSimpleName();
		
			// we do know that this one has some extra attributes
			if (className.equals("Bus")) {
				bar.put("engineCapacity", v.getCapacity() + "");
				bar.put("fuelType", v.getFuelType() + "");
			} 
			
			bar.put("nameOfTheProducer", v.getNameOfTheProducer());
			bar.put("mileage", v.getMileage() + "");
			
			// convert long into String
			String s = Objects.toString(v.getDateOfManufactureInMs(), null);
			
			bar.put("dateOfManufactureInMs", s);
			bar.put("isLowFloor", v.isLowFloor + "");
			bar.put("isArticulated", v.isArticulated + "");
			
			foo.put(className, bar);
			
			collection.add(foo);
		}
		
		return collection;
	}
	
}


