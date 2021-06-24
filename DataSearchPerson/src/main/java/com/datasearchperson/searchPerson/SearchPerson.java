package com.datasearchperson.searchPerson;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.datasearchperson.person.Person;
import com.datasearchperson.repository.PersonRepository;

public class SearchPerson {

	public List<Person> searchByFirstName(String searchString, PersonRepository personRepository) {
		List<Person> personList = new ArrayList<>();

		personRepository.findAll().forEach(personList::add);

		return personList.stream().filter(n -> n.getFirstName().equals(searchString)).collect(Collectors.toList());
	}

	public List<Person> searchByID(String searchString, PersonRepository personRepository) {
		List<Person> personList = new ArrayList<>();

		personRepository.findAll().forEach(personList::add);

		return personList.stream().filter(n -> n.getIdNumber().toString().equals(searchString)).collect(Collectors.toList());
	}

	public List<Person> searchByPhoneNumber(String searchString, PersonRepository personRepository) {
		List<Person> personList = new ArrayList<>();

		personRepository.findAll().forEach(personList::add);

		return personList.stream().filter(n -> n.getMobileNumber().toString().equals(searchString)).collect(Collectors.toList());
	}
	
	public List<Person> searchAll( PersonRepository personRepository) {
		List<Person> personList = new ArrayList<>();

		personRepository.findAll().forEach(personList::add);

		return personList;
	}

}
