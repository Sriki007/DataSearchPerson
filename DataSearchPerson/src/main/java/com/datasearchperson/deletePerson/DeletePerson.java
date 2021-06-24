package com.datasearchperson.deletePerson;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.datasearchperson.exception.NoElementsToDelete;
import com.datasearchperson.person.Person;
import com.datasearchperson.repository.PersonRepository;

public class DeletePerson {

	public void deletePerson(String deleteById, PersonRepository personRepository) throws NoElementsToDelete 
	{
		List<Person> personList = new ArrayList<>();

		personRepository.findAll().forEach(personList::add);
		final Optional<Person> deletePerson = personList.stream().filter(n -> n.getIdNumber().toString().equals(deleteById)).findFirst();
		
		if(deletePerson.isEmpty())
		{
			throw new NoElementsToDelete("No Such elements to delete");
		}
		
		personRepository.delete(deletePerson.get());
	}
}
