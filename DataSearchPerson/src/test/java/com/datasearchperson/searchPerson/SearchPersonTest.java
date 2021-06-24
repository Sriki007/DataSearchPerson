package com.datasearchperson.searchPerson;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.datasearchperson.person.Person;
import com.datasearchperson.repository.PersonRepository;

@RunWith(SpringJUnit4ClassRunner.class)
public class SearchPersonTest {

	@Mock
	private PersonRepository personRepository;
	
	@Test
	public void testSearchPersonByFirstName()
	{
		Person existingPerson = mock(Person.class);
		when(existingPerson.getFirstName()).thenReturn("Kusuma");
		when(existingPerson.getLastName()).thenReturn("G");
		when(existingPerson.getMobileNumber()).thenReturn(new BigInteger("9964100069"));
		when(existingPerson.getIdNumber()).thenReturn(new BigInteger("9202204720083"));
		when(existingPerson.getPhysicalAddress()).thenReturn("Tumkuru");
		
		List<Person> existingPersonList = new ArrayList<>();
		existingPersonList.add(existingPerson);
		when(personRepository.findAll()).thenReturn(existingPersonList);
		
		SearchPerson searchPerson = new SearchPerson();
		List<Person> actualResultList = searchPerson.searchByFirstName("Kusuma", personRepository);
		Assert.assertEquals(existingPersonList, actualResultList);
	}
	
	@Test
	public void testSearchPersonById()
	{
		Person existingPerson = mock(Person.class);
		when(existingPerson.getFirstName()).thenReturn("Kusuma");
		when(existingPerson.getLastName()).thenReturn("G");
		when(existingPerson.getMobileNumber()).thenReturn(new BigInteger("9964100069"));
		when(existingPerson.getIdNumber()).thenReturn(new BigInteger("9202204720083"));
		when(existingPerson.getPhysicalAddress()).thenReturn("Tumkuru");
		
		List<Person> existingPersonList = new ArrayList<>();
		existingPersonList.add(existingPerson);
		when(personRepository.findAll()).thenReturn(existingPersonList);
		
		SearchPerson searchPerson = new SearchPerson();
		List<Person> actualResultList = searchPerson.searchByID("9202204720083", personRepository);
		Assert.assertEquals(existingPersonList, actualResultList);
	}
	
	@Test
	public void testSearchPersonByPhoneNumber()
	{
		Person existingPerson = mock(Person.class);
		when(existingPerson.getFirstName()).thenReturn("Kusuma");
		when(existingPerson.getLastName()).thenReturn("G");
		when(existingPerson.getMobileNumber()).thenReturn(new BigInteger("9964100069"));
		when(existingPerson.getIdNumber()).thenReturn(new BigInteger("9202204720083"));
		when(existingPerson.getPhysicalAddress()).thenReturn("Tumkuru");
		
		List<Person> existingPersonList = new ArrayList<>();
		existingPersonList.add(existingPerson);
		when(personRepository.findAll()).thenReturn(existingPersonList);
		
		SearchPerson searchPerson = new SearchPerson();
		List<Person> actualResultList = searchPerson.searchByPhoneNumber("9964100069", personRepository);
		Assert.assertEquals(existingPersonList, actualResultList);
	}
	
	@Test
	public void testSearchPersonAll()
	{
		Person existingPerson1 = mock(Person.class);
		when(existingPerson1.getFirstName()).thenReturn("Kusuma");
		when(existingPerson1.getLastName()).thenReturn("G");
		when(existingPerson1.getMobileNumber()).thenReturn(new BigInteger("9964100069"));
		when(existingPerson1.getIdNumber()).thenReturn(new BigInteger("9202204720083"));
		when(existingPerson1.getPhysicalAddress()).thenReturn("Tumkuru");
		
		Person existingPerson2 = mock(Person.class);
		when(existingPerson2.getFirstName()).thenReturn("Srikanth");
		when(existingPerson2.getLastName()).thenReturn("RP");
		when(existingPerson2.getMobileNumber()).thenReturn(new BigInteger("7353100069"));
		when(existingPerson2.getIdNumber()).thenReturn(new BigInteger("9202204720082"));
		when(existingPerson2.getPhysicalAddress()).thenReturn("Bengaluru");
		
		List<Person> existingPersonList = new ArrayList<>();
		existingPersonList.add(existingPerson1);
		existingPersonList.add(existingPerson2);
		when(personRepository.findAll()).thenReturn(existingPersonList);
		
		SearchPerson searchPerson = new SearchPerson();
		List<Person> actualResultList = searchPerson.searchAll(personRepository);
		Assert.assertEquals(existingPersonList, actualResultList);
	}

}
