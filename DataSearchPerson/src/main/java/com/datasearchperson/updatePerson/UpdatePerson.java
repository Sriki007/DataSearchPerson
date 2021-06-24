package com.datasearchperson.updatePerson;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.datasearchperson.exception.DuplicatePersonIDInformation;
import com.datasearchperson.exception.DuplicatePersonMobileInformation;
import com.datasearchperson.exception.ImproperPersonInformation;
import com.datasearchperson.exception.InvalidPersonIDInformation;
import com.datasearchperson.exception.PersonInfomationIsNull;
import com.datasearchperson.person.Person;
import com.datasearchperson.repository.PersonRepository;
import com.datasearchperson.validation.IDNumberData;
import com.datasearchperson.validation.IDNumberParser;

public class UpdatePerson {

	public void updatePerson(Person person, PersonRepository personRepository) throws PersonInfomationIsNull, ImproperPersonInformation, InvalidPersonIDInformation, DuplicatePersonIDInformation, DuplicatePersonMobileInformation 
	{
		if(person == null)
		{
			throw new PersonInfomationIsNull("Person information is Null");
		}
		
		if(StringUtils.isBlank(person.getFirstName()))
		{
			throw new ImproperPersonInformation("First name may be null or empty or blank");
		}
		
		if(StringUtils.isBlank(person.getLastName()))
		{
			throw new ImproperPersonInformation("First name may be null or empty or blank");
		}
		
		String idNumber = person.getIdNumber().toString();
		IDNumberParser idNumberParser = new IDNumberParser();
		IDNumberData idNumberData;
		
		try 
		{
			idNumberData = idNumberParser.parse(idNumber);
		} 
		catch (Exception e) 
		{
			throw new InvalidPersonIDInformation("ID passed is not valid African");
		}
		
		List<Person> personList = new ArrayList<>();

		personRepository.findAll().forEach(personList::add);
		final List<Person> idNumbers = personList.stream().filter(n -> n.getIdNumber().equals(person.getIdNumber())).collect(Collectors.toList());
		final List<Person> phoneNumbers = personList.stream().filter(n -> n.getMobileNumber().equals(person.getMobileNumber())).collect(Collectors.toList());
		
		if(!idNumbers.isEmpty())
		{
			throw new DuplicatePersonIDInformation("Id Number is duplicate");
		}
		
		if(!phoneNumbers.isEmpty())
		{
			throw new DuplicatePersonMobileInformation("Mobile Number is duplicate");
		}
		
		personRepository.save(person);
	}
}
