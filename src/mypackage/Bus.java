package mypackage;

import java.util.Date;

public class Bus extends Vehicle {
	// in cm^3
	private int engineCapacity = 0;
	// methanol 0
	// diesel 1
	// gasoline 2
	private int fuelType = 2;
	
	public Bus() {
		
	}
	
	public Bus(String nameOfTheProducer, Date dateOfManufacture, int mileage, int engineCapacity, int fuelType) throws Exception {
		
		super(nameOfTheProducer, dateOfManufacture, mileage);
		
		setCapacity(engineCapacity);
		setFuelType(fuelType);
	}

	public Bus(String nameOfTheProducer, Date dateOfManufacture, int mileage, int engineCapacity, int fuelType, boolean isLowFloor,
			boolean isArticulated) throws Exception {
	
		super(nameOfTheProducer, dateOfManufacture, mileage, isLowFloor, isArticulated);
		
		setCapacity(engineCapacity);
		setFuelType(fuelType);
	}
	
	@Override
	public void setFuelType(int n) throws Exception {
		if(n < 0 || n > 2)
			throw new Exception("[Error] fuel type can be defiend with 0, 1 or 2 only");
		else 
			fuelType = n;
	}
	
	@Override
	public int getFuelType() {
		return fuelType;
	}
	
	@Override
	public int getCapacity() {
		return engineCapacity;
	}
	
	@Override
	public void setCapacity(int n) throws Exception {
		if(n > 0)
			engineCapacity = n;
		else 
			throw new Exception("[Error] engineCapacity must be greater than 0");
	}

}
