package mypackage;

import java.util.Date;

// this is just a dummy class for academic purposes 

public class Train extends Vehicle {
	
	public Train() {
		
	}
	
	public Train(String nameOfTheProducer, Date dateOfManufacture, int mileage) throws Exception {
		super(nameOfTheProducer, dateOfManufacture, mileage);
	}

	public Train(String nameOfTheProducer, Date dateOfManufacture, int mileage, boolean isLowFloor, boolean isArticulated) throws Exception {
		super(nameOfTheProducer, dateOfManufacture, mileage, isLowFloor, isArticulated);
	}
}
