package com.ms.assignment.service4.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.ms.assignment.service4.entity.NestedPerson;
import com.ms.assignment.service4.entity.Person;
import com.ms.assignment.service4.entity.PersonJsonView;
import com.ms.assignment.service4.exception.ResourceNotFoundException;
import com.ms.assignment.service4.repository.PersonRepository;
import com.ms.assignment.service4.repository.PersonRepositoryImpl;

@RestController
@RequestMapping("/ms4")
@Validated
public class PersonController {
	private static final Logger LOG = Logger.getLogger(PersonController.class.getName());
	@Value("${server.port}")
	private int port;
	
	@Autowired
	PersonRepository personRepository;
	@Autowired
	PersonRepositoryImpl personImpl;
	
	@GetMapping
	public String up() {
		LOG.log(Level.INFO, "GET :: Service1, Running on port : "+ port);
		return "Status : UP";
	}
	
	@GetMapping("/persons")
	public List<Person> getAllPersons(){
		LOG.log(Level.INFO, "GET :: Service4, Fetching All person.");
		return personRepository.findAll();
	}

	@GetMapping("/person/{id}")
	public ResponseEntity<Person> getPerson(@PathVariable(name = "id")@NonNull Long id) throws ResourceNotFoundException {
		LOG.log(Level.INFO, "GET :: Service4, Fetching person for id : "+ id);
		Person person = personRepository
				.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Person not found for this id :: "+ id));
		return ResponseEntity.ok().body(person);
	}
	
	@GetMapping("/persondetails/{id}")
	public ResponseEntity<String> getNestedPerson(@PathVariable(name = "id")@NotNull Long id) throws ResourceNotFoundException, JsonProcessingException{
		Person parent = getPerson(id).getBody(); //parent
		List<Person> persons = personImpl.personDetails(id); //all child person
		
		NestedPerson nestedDetails = new NestedPerson();
		nestedDetails.setName(parent.getName());
		nestedDetails.setSubClasses(persons);
		
		ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT); //to enable pretty print
        String normalView = mapper.writerWithView(PersonJsonView.Normal.class).writeValueAsString(nestedDetails);

		LOG.log(Level.INFO, "GET :: Getting nested details : "+ normalView);
		return ResponseEntity.ok().body(normalView);
	}
	
	@PostMapping("/person")
	public Person addPerson(@Valid @RequestBody Person person) {
		Person p = personRepository.save(person);
		LOG.log(Level.INFO, "POST :: Service4, Adding person with created id : "+ p.getId());
		return p;
	}
	
	@PutMapping("/person/{id}")
	public ResponseEntity<Person> updatePerson(@PathVariable(name = "id")@NotNull Long id, 
			@Valid @RequestBody Person person) throws ResourceNotFoundException{
		Person p = personRepository.findById(id)
								   .orElseThrow(() -> new ResourceNotFoundException("Person not found for id : "+ id));
		
		p.setName(person.getName());
		p.setColor(person.getColor());
		p.setParentId(person.getParentId());
		final Person updatedPerson = personRepository.save(p);
		LOG.log(Level.INFO, "PUT :: Service4, Updating person with id : "+ p.getId());
		return ResponseEntity.ok().body(updatedPerson);
	}

	@DeleteMapping("/person/{id}")
	public Map<String, Boolean> deletePerson(@PathVariable(name = "id")@NotNull Long id)
					throws ResourceNotFoundException {
		Person person = personRepository.findById(id)
										.orElseThrow(() -> new ResourceNotFoundException("Person not found for id : "+ id));
		LOG.log(Level.INFO, "DELETE :: Service4, Deleting person with id : "+ person.getId());
		personRepository.delete(person);
		Map<String, Boolean> response = new HashMap<>();
		response.put("Resource Deleted", Boolean.TRUE);
		return response;
	}
}


