package com.datasearchperson.repository;

import java.math.BigInteger;

import org.springframework.data.repository.CrudRepository;

import com.datasearchperson.person.Person;

public interface PersonRepository extends CrudRepository<Person, BigInteger>
{

}
