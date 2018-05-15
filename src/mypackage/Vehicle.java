package mypackage;

import java.util.Date;

public class Vehicle {

	private final int MAX_PRODUCER_NAME = 20;
	private int mileage = 0;
	private Date dateOfManufacture = null;
	private String nameOfTheProducer = "";
	
	// false by default 
	public boolean isLowFloor = false;
	public boolean isArticulated = false;
	
	public final int MAX_MILEAGE = 999999;
	
	public Vehicle() {
		
	}
	
	public Vehicle(String nameOfTheProducer, Date dateOfManufacture, int mileage) throws Exception {
		setDateOfManufacture(dateOfManufacture);
		setMileage(mileage);
		setNameOfTheProducer(nameOfTheProducer);
	}
	
	public Vehicle(String nameOfTheProducer, Date dateOfManufacture, int mileage, boolean isLowFloor, boolean isArticulated) throws Exception {
		this(nameOfTheProducer, dateOfManufacture, mileage);
		this.isLowFloor = isLowFloor;
		this.isArticulated = isArticulated;
	}
	
	public void setDateOfManufacture(Date dateOfManufacture) throws Exception {
		if(dateOfManufacture == null)
			throw new Exception("[Error] dateOfManufacture is missing");
		
		if(!isValidDate(dateOfManufacture))
			throw new Exception("[Error] dateOfManufacture cannot be in the future");
		
		this.dateOfManufacture = dateOfManufacture;
	}
	
	public void setDateOfManufactureInMs(long ms) throws Exception {
		Date date = new Date(ms);
		setDateOfManufacture(date);
	}
	
	public long getDateOfManufactureInMs() {
		return dateOfManufacture.getTime();
	}
	
	public String getNameOfTheProducer() {
		return this.nameOfTheProducer;
	}
	
	public void setNameOfTheProducer(String nameOfTheProducer) throws Exception {
		if (nameOfTheProducer == null || nameOfTheProducer.isEmpty())
			throw new Exception("[Error] nameOfTheProducer is missing");
		
		if (nameOfTheProducer.length() > MAX_PRODUCER_NAME)
			throw new Exception("[Error] maxProducerName is limited to " + MAX_PRODUCER_NAME + " symbols");
		
		this.nameOfTheProducer = nameOfTheProducer;
	}
	
	public void setMileage(int n) throws Exception {
		if (n < 0 || n > MAX_MILEAGE)
			throw new Exception("mileage must be between 0 and " + MAX_MILEAGE);
		this.mileage = n; 
	}
	
	public int getMileage() {
		return mileage;
	}
	
	private boolean isValidDate(Date date) {
		// get now
		final Date now = new Date();
		// make sure it's in the past
		return date.before(now);
	}
	
	@Override
	public String toString() {
		return nameOfTheProducer + " " + dateOfManufacture + " " + mileage + " " + isLowFloor + " " + isArticulated;
	}
	
	// here comes a little sacrifice for dynamic instantiation 
	public void setFuelType(int n) throws Exception {
		
	}

	public int getFuelType() {
		return 0;
	}

	public int getCapacity() {
		return 0;
	}
	
	public void setCapacity(int n) throws Exception {

	}

}
