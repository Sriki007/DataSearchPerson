package com.datasearchperson.createPerson;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.datasearchperson.exception.DuplicatePersonIDInformation;
import com.datasearchperson.exception.DuplicatePersonMobileInformation;
import com.datasearchperson.exception.ImproperPersonInformation;
import com.datasearchperson.exception.InvalidPersonIDInformation;
import com.datasearchperson.exception.PersonInfomationIsNull;
import com.datasearchperson.person.Person;
import com.datasearchperson.repository.PersonRepository;

@RunWith(SpringJUnit4ClassRunner.class)
public class CreatePersonTest 
{
	@Mock
	private PersonRepository personRepository;
	
	@Test (expected = PersonInfomationIsNull.class)
	public void testCreatePersonWhenPersonInformationIsNull() throws PersonInfomationIsNull, ImproperPersonInformation, InvalidPersonIDInformation, DuplicatePersonIDInformation, DuplicatePersonMobileInformation
	{
		CreatePerson createPerson = new CreatePerson();
		createPerson.createPerson(null, personRepository);
	}
	
	@Test (expected = ImproperPersonInformation.class)
	public void testCreatePersonWhenPersonFirstNameIsEmpty() throws PersonInfomationIsNull, ImproperPersonInformation, InvalidPersonIDInformation, DuplicatePersonIDInformation, DuplicatePersonMobileInformation
	{
		Person mockPerson = mock(Person.class);
		when(mockPerson.getFirstName()).thenReturn("");
		CreatePerson createPerson = new CreatePerson();
		createPerson.createPerson(mockPerson, personRepository);
	}
	
	@Test (expected = ImproperPersonInformation.class)
	public void testCreatePersonWhenPersonLastNameIsEmpty() throws PersonInfomationIsNull, ImproperPersonInformation, InvalidPersonIDInformation, DuplicatePersonIDInformation, DuplicatePersonMobileInformation
	{
		Person mockPerson = mock(Person.class);
		when(mockPerson.getFirstName()).thenReturn("Srikanth");
		when(mockPerson.getLastName()).thenReturn("");
		CreatePerson createPerson = new CreatePerson();
		createPerson.createPerson(mockPerson, personRepository);
	}
	
	@Test (expected = InvalidPersonIDInformation.class)
	public void testCreatePersonWhenPersonIDIsInvalidWithLength() throws PersonInfomationIsNull, ImproperPersonInformation, InvalidPersonIDInformation, DuplicatePersonIDInformation, DuplicatePersonMobileInformation
	{
		Person mockPerson = mock(Person.class);
		when(mockPerson.getFirstName()).thenReturn("Srikanth");
		when(mockPerson.getLastName()).thenReturn("Rp");
		when(mockPerson.getMobileNumber()).thenReturn(new BigInteger("7353100069"));
		when(mockPerson.getIdNumber()).thenReturn(new BigInteger("12345678912"));
		when(mockPerson.getPhysicalAddress()).thenReturn("Benagluru");
		
		CreatePerson createPerson = new CreatePerson();
		createPerson.createPerson(mockPerson, personRepository);
	}
	
	@Test (expected = InvalidPersonIDInformation.class)
	public void testCreatePersonWhenPersonIDIsInvalidWithBirthYear() throws PersonInfomationIsNull, ImproperPersonInformation, InvalidPersonIDInformation, DuplicatePersonIDInformation, DuplicatePersonMobileInformation
	{
		Person mockPerson = mock(Person.class);
		when(mockPerson.getFirstName()).thenReturn("Srikanth");
		when(mockPerson.getLastName()).thenReturn("Rp");
		when(mockPerson.getMobileNumber()).thenReturn(new BigInteger("7353100069"));
		when(mockPerson.getIdNumber()).thenReturn(new BigInteger("1234567891234"));
		when(mockPerson.getPhysicalAddress()).thenReturn("Benagluru");
		
		CreatePerson createPerson = new CreatePerson();
		createPerson.createPerson(mockPerson, personRepository);
	}
	
	@Test (expected = DuplicatePersonIDInformation.class)
	public void testCreatePersonWhenPersonIDIsDuplicate() throws PersonInfomationIsNull, ImproperPersonInformation, InvalidPersonIDInformation, DuplicatePersonIDInformation, DuplicatePersonMobileInformation
	{
		Person mockPerson = mock(Person.class);
		when(mockPerson.getFirstName()).thenReturn("Srikanth");
		when(mockPerson.getLastName()).thenReturn("Rp");
		when(mockPerson.getMobileNumber()).thenReturn(new BigInteger("7353100069"));
		when(mockPerson.getIdNumber()).thenReturn(new BigInteger("9202204720083"));
		when(mockPerson.getPhysicalAddress()).thenReturn("Benagluru");
		
		Person existingPerson = mock(Person.class);
		when(existingPerson.getFirstName()).thenReturn("Kusuma");
		when(existingPerson.getLastName()).thenReturn("G");
		when(existingPerson.getMobileNumber()).thenReturn(new BigInteger("9964100069"));
		when(existingPerson.getIdNumber()).thenReturn(new BigInteger("9202204720083"));
		when(existingPerson.getPhysicalAddress()).thenReturn("Tumkuru");
		
		List<Person> existingPersonList = new ArrayList<>();
		existingPersonList.add(existingPerson);
		when(personRepository.findAll()).thenReturn(existingPersonList);
		
		CreatePerson createPerson = new CreatePerson();
		createPerson.createPerson(mockPerson, personRepository);
	}
	
	@Test (expected = DuplicatePersonMobileInformation.class)
	public void testCreatePersonWhenPersonMobileNumberIsDuplicate() throws PersonInfomationIsNull, ImproperPersonInformation, InvalidPersonIDInformation, DuplicatePersonIDInformation, DuplicatePersonMobileInformation
	{
		Person mockPerson = mock(Person.class);
		when(mockPerson.getFirstName()).thenReturn("Srikanth");
		when(mockPerson.getLastName()).thenReturn("Rp");
		when(mockPerson.getMobileNumber()).thenReturn(new BigInteger("7353100069"));
		when(mockPerson.getIdNumber()).thenReturn(new BigInteger("9202204720083"));
		when(mockPerson.getPhysicalAddress()).thenReturn("Benagluru");
		
		Person existingPerson = mock(Person.class);
		when(existingPerson.getFirstName()).thenReturn("Kusuma");
		when(existingPerson.getLastName()).thenReturn("G");
		when(existingPerson.getMobileNumber()).thenReturn(new BigInteger("7353100069"));
		when(existingPerson.getIdNumber()).thenReturn(new BigInteger("9202204720082"));
		when(existingPerson.getPhysicalAddress()).thenReturn("Tumkuru");
		
		List<Person> existingPersonList = new ArrayList<>();
		existingPersonList.add(existingPerson);
		when(personRepository.findAll()).thenReturn(existingPersonList);
		
		CreatePerson createPerson = new CreatePerson();
		createPerson.createPerson(mockPerson, personRepository);
	}
	
	@Test
	public void testCreatePersonSuccess() throws PersonInfomationIsNull, ImproperPersonInformation, InvalidPersonIDInformation, DuplicatePersonIDInformation, DuplicatePersonMobileInformation
	{
		Person mockPerson = mock(Person.class);
		when(mockPerson.getFirstName()).thenReturn("Srikanth");
		when(mockPerson.getLastName()).thenReturn("Rp");
		when(mockPerson.getMobileNumber()).thenReturn(new BigInteger("7353100069"));
		when(mockPerson.getIdNumber()).thenReturn(new BigInteger("9202204720083"));
		when(mockPerson.getPhysicalAddress()).thenReturn("Benagluru");
		
		Person existingPerson = mock(Person.class);
		when(existingPerson.getFirstName()).thenReturn("Kusuma");
		when(existingPerson.getLastName()).thenReturn("G");
		when(existingPerson.getMobileNumber()).thenReturn(new BigInteger("9964100069"));
		when(existingPerson.getIdNumber()).thenReturn(new BigInteger("9202204720082"));
		when(existingPerson.getPhysicalAddress()).thenReturn("Tumkuru");
		
		List<Person> existingPersonList = new ArrayList<>();
		existingPersonList.add(existingPerson);
		when(personRepository.findAll()).thenReturn(existingPersonList);
		when(personRepository.save(mockPerson)).thenReturn(mockPerson);
		
		CreatePerson createPerson = new CreatePerson();
		createPerson.createPerson(mockPerson, personRepository);
	}
}
