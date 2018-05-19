package mypackage;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

@SuppressWarnings("unused")
public class Main {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// retrieve 
		ArrayList<Map<String, Map<String, String>>> collection = PersistenceUtility.load("data.txt");

		// initialize 
		Scope.initialize(collection);

		System.out.println(Scope.vehicles);
		
		// modify 
		
		Vehicle v = Scope.vehicles.get(0);
		long l = v.getDateOfManufactureInMs();
		Date date = new Date(l);
		Calendar c = Calendar.getInstance(); 
		c.setTime(date); 
		c.add(Calendar.DATE, -1);
		date = c.getTime();
		
		try {
			v.setDateOfManufacture(date);	
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		// add a new record
		
		c.add(Calendar.DATE, -2);
		date = c.getTime();
		
		Bus b = null;
		try {
			b = new Bus("Mercedes-Benz", date, 151515, 3000, 2, false, true);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		if (b != null) Scope.vehicles.add(b);
		
		System.out.print(Scope.vehicles);
		
		// save 
		PersistenceUtility.store("data.txt", Scope.getDumpable());
	
	}

}
