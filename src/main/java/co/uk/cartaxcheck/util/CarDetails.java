package co.uk.cartaxcheck.util;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;

public class CarDetails  {
	
    //@CsvBindByPosition(position = 0)
    @CsvBindByName(column = "REGISTRATION")
    private String registration;

    //@CsvBindByPosition(position = 1)
    @CsvBindByName(column = "MAKE")
    private String make;
    
    
    //@CsvBindByPosition(position = 2)
    @CsvBindByName(column = "MODEL")
    private String model;

    
    //@CsvBindByPosition(position = 3)
    @CsvBindByName(column = "COLOR")
    private String color;

    
    //@CsvBindByPosition(position = 4)
    @CsvBindByName(column = "YEAR")
    private String year;


	public String getRegistration() {
		return registration;
	}


	public void setRegistration(String registration) {
		this.registration = registration;
	}


	public String getMake() {
		return make;
	}


	public void setMake(String make) {
		this.make = make;
	}


	public String getModel() {
		return model;
	}


	public void setModel(String model) {
		this.model = model;
	}


	public String getColor() {
		return color;
	}


	public void setColor(String color) {
		this.color = color;
	}


	public String getYear() {
		return year;
	}


	public void setYear(String year) {
		this.year = year;
	}



}
