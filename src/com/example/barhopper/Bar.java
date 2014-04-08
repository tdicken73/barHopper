package com.example.barhopper;

public class Bar {
	private int id;
    private String name;
    private String address;
    private String hours;
 
    public Bar(){}
 
    public Bar(String name, String address, String hours) {
        super();
        this.address = address;
        this.hours = hours;
    }
    
    public int getId() {
    	return this.id;
    }
    
    public String getName() {
    	return this.name;
    }
    
    public String getAddress() {
    	return this.address;
    }
    
    public String getHours() {
    	return this.hours;
    }
    
    public void setId(int value) {
    	this.id = value;
    }
    
    public void setName(String value) {
    	this.name = value;
    }
    
    public void setAddress(String value) {
    	this.address = value;
    }
    
    public void setHours(String value) {
    	this.hours = value;
    }
 
    //getters & setters
 
    @Override
    public String toString() {
        return "Bar [id=" + id + ", name=" + name + ", hours=" + hours
                + "]";
    }
}
