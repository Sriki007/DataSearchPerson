package com.datasearchperson.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.datasearchperson.createPerson.CreatePerson;
import com.datasearchperson.deletePerson.DeletePerson;
import com.datasearchperson.exception.DuplicatePersonIDInformation;
import com.datasearchperson.exception.DuplicatePersonMobileInformation;
import com.datasearchperson.exception.ImproperPersonInformation;
import com.datasearchperson.exception.InvalidPersonIDInformation;
import com.datasearchperson.exception.NoElementsToDelete;
import com.datasearchperson.exception.PersonInfomationIsNull;
import com.datasearchperson.person.Person;
import com.datasearchperson.repository.PersonRepository;
import com.datasearchperson.searchPerson.SearchPerson;
import com.datasearchperson.updatePerson.UpdatePerson;

@RestController
public class DataSearchApi 
{
	@Autowired
	private PersonRepository personRepository;
	
	@RequestMapping(value = "/createPerson", method = RequestMethod.POST)
	public ResponseEntity<String> createPerson(@RequestBody Person person)
	{
		CreatePerson createPerson = new CreatePerson();

		try 
		{
			createPerson.createPerson(person, personRepository);
		} 	
		catch (PersonInfomationIsNull | ImproperPersonInformation | InvalidPersonIDInformation | DuplicatePersonIDInformation | DuplicatePersonMobileInformation e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
		}

		return new ResponseEntity<>("Successfully inserted the data", HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/searchPersonByFirstName", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<Person>> searchPersonOnFirstName(@RequestParam(value = "searchPersonOnFirstName") String searchString)
	{
		SearchPerson searchPerson = new SearchPerson();
		List<Person> resultList = null;
		resultList = searchPerson.searchByFirstName(searchString, personRepository);

		return new ResponseEntity<>(resultList, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/searchPersonByID", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<Person>> searchPersonOnID(@RequestParam(value = "searchPersonOnID") String searchString)
	{
		SearchPerson searchPerson = new SearchPerson();
		List<Person> resultList = null;
		resultList = searchPerson.searchByID(searchString, personRepository);
		
		return new ResponseEntity<>(resultList, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/searchPersonByPhone", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<Person>> searchPersonOnPhoneNumber(@RequestParam(value = "searchPersonOnPhoneNumber") String searchString)
	{
		SearchPerson searchPerson = new SearchPerson();
		List<Person> resultList = null;
		resultList = searchPerson.searchByPhoneNumber(searchString, personRepository);
		
		return new ResponseEntity<>(resultList, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/searchAll", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<Person>> searchAll()
	{
		SearchPerson searchPerson = new SearchPerson();
		List<Person> resultList = null;
		resultList = searchPerson.searchAll(personRepository);
		
		return new ResponseEntity<>(resultList, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/updatePerson", method = RequestMethod.POST)
	public ResponseEntity<String> updatePerson(@RequestBody Person person)
	{
		UpdatePerson updatePerson = new UpdatePerson();

		try 
		{
			updatePerson.updatePerson(person, personRepository);
		} 	
		catch (PersonInfomationIsNull | ImproperPersonInformation | InvalidPersonIDInformation | DuplicatePersonIDInformation | DuplicatePersonMobileInformation e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
		}

		return new ResponseEntity<>("Successfully updated the data", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/deletePerson", method = RequestMethod.DELETE)
	public ResponseEntity<String> deletePerson(@RequestParam(value = "deleteByID") String deleteByID)
	{
		DeletePerson deletePerson = new DeletePerson();

		try {
			deletePerson.deletePerson(deleteByID, personRepository);
		} catch (NoElementsToDelete e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
		}

		return new ResponseEntity<>("Successfully deleted the data", HttpStatus.OK);
	}
}
