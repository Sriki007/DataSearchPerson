package com.datasearchperson.person;

import java.math.BigInteger;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Person 
{
	/**
     * ID number of person.
     */
	@Id
	private BigInteger idNumber;
	
	/**
     * First Name of a Person.
     */
    private String firstName;
	
	/**
     * Last Name of a Person.
     */
    private String lastName;
	
	/**
     * Mobile number of person.
     */
	private BigInteger mobileNumber;
	
	/**
     * Physical Address of a Person.
     */
    private String physicalAddress;
    
    public Person()
    {
    	
    }
    
    public Person(String firstName, String lastName, BigInteger mobileNumber, BigInteger idNumber,
			String physicalAddress) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobileNumber = mobileNumber;
		this.idNumber = idNumber;
		this.physicalAddress = physicalAddress;
	}

	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public BigInteger getMobileNumber() {
		return mobileNumber;
	}

	public BigInteger getIdNumber() {
		return idNumber;
	}

	public String getPhysicalAddress() {
		return physicalAddress;
	}
}
