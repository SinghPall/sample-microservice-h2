package com.ms.assignment.service4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ms.assignment.service4.entity.Person;
@Repository
public interface PersonRepository extends JpaRepository<Person, Long>, PersonRepositoryCustom{

}