package com.datasearchperson.updatePerson;

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
public class UpdatePersonTest 
{
	@Mock
	private PersonRepository personRepository;
	
	@Test (expected = PersonInfomationIsNull.class)
	public void testUpdatePersonWhenPersonInformationIsNull() throws PersonInfomationIsNull, ImproperPersonInformation, InvalidPersonIDInformation, DuplicatePersonIDInformation, DuplicatePersonMobileInformation
	{
		UpdatePerson updatePerson = new UpdatePerson();
		updatePerson.updatePerson(null, personRepository);
	}
	
	@Test (expected = ImproperPersonInformation.class)
	public void testUpdatePersonWhenPersonFirstNameIsEmpty() throws PersonInfomationIsNull, ImproperPersonInformation, InvalidPersonIDInformation, DuplicatePersonIDInformation, DuplicatePersonMobileInformation
	{
		Person mockPerson = mock(Person.class);
		when(mockPerson.getFirstName()).thenReturn("");
		UpdatePerson updatePerson = new UpdatePerson();
		updatePerson.updatePerson(mockPerson, personRepository);
	}
	
	@Test (expected = ImproperPersonInformation.class)
	public void testUpdatePersonWhenPersonLastNameIsEmpty() throws PersonInfomationIsNull, ImproperPersonInformation, InvalidPersonIDInformation, DuplicatePersonIDInformation, DuplicatePersonMobileInformation
	{
		Person mockPerson = mock(Person.class);
		when(mockPerson.getFirstName()).thenReturn("Srikanth");
		when(mockPerson.getLastName()).thenReturn("");
		UpdatePerson updatePerson = new UpdatePerson();
		updatePerson.updatePerson(mockPerson, personRepository);
	}
	
	@Test (expected = InvalidPersonIDInformation.class)
	public void testUpdatePersonWhenPersonIDIsInvalidWithLength() throws PersonInfomationIsNull, ImproperPersonInformation, InvalidPersonIDInformation, DuplicatePersonIDInformation, DuplicatePersonMobileInformation
	{
		Person mockPerson = mock(Person.class);
		when(mockPerson.getFirstName()).thenReturn("Srikanth");
		when(mockPerson.getLastName()).thenReturn("Rp");
		when(mockPerson.getMobileNumber()).thenReturn(new BigInteger("7353100069"));
		when(mockPerson.getIdNumber()).thenReturn(new BigInteger("12345678912"));
		when(mockPerson.getPhysicalAddress()).thenReturn("Benagluru");
		
		UpdatePerson updatePerson = new UpdatePerson();
		updatePerson.updatePerson(mockPerson, personRepository);
	}
	
	@Test (expected = InvalidPersonIDInformation.class)
	public void testUpdatePersonWhenPersonIDIsInvalidWithBirthYear() throws PersonInfomationIsNull, ImproperPersonInformation, InvalidPersonIDInformation, DuplicatePersonIDInformation, DuplicatePersonMobileInformation
	{
		Person mockPerson = mock(Person.class);
		when(mockPerson.getFirstName()).thenReturn("Srikanth");
		when(mockPerson.getLastName()).thenReturn("Rp");
		when(mockPerson.getMobileNumber()).thenReturn(new BigInteger("7353100069"));
		when(mockPerson.getIdNumber()).thenReturn(new BigInteger("1234567891234"));
		when(mockPerson.getPhysicalAddress()).thenReturn("Benagluru");
		
		UpdatePerson updatePerson = new UpdatePerson();
		updatePerson.updatePerson(mockPerson, personRepository);
	}
	
	@Test (expected = DuplicatePersonIDInformation.class)
	public void testUpdatePersonWhenPersonIDIsDuplicate() throws PersonInfomationIsNull, ImproperPersonInformation, InvalidPersonIDInformation, DuplicatePersonIDInformation, DuplicatePersonMobileInformation
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
		
		UpdatePerson updatePerson = new UpdatePerson();
		updatePerson.updatePerson(mockPerson, personRepository);
	}
	
	@Test (expected = DuplicatePersonMobileInformation.class)
	public void testUpdatePersonWhenPersonMobileNumberIsDuplicate() throws PersonInfomationIsNull, ImproperPersonInformation, InvalidPersonIDInformation, DuplicatePersonIDInformation, DuplicatePersonMobileInformation
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
		
		UpdatePerson updatePerson = new UpdatePerson();
		updatePerson.updatePerson(mockPerson, personRepository);
	}
	
	@Test
	public void testUpdatePersonSuccess() throws PersonInfomationIsNull, ImproperPersonInformation, InvalidPersonIDInformation, DuplicatePersonIDInformation, DuplicatePersonMobileInformation
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
		
		UpdatePerson updatePerson = new UpdatePerson();
		updatePerson.updatePerson(mockPerson, personRepository);
	}
}
