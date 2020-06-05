package com.ms.assignment.service4.repository;

import java.util.List;

import com.ms.assignment.service4.entity.Person;

public interface PersonRepositoryCustom {
	List<Person> personDetails(Long id);	
}
