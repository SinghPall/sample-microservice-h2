package com.ms.assignment.service4.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ms.assignment.service4.controller.PersonController;
import com.ms.assignment.service4.entity.Person;

@Repository
public class PersonRepositoryImpl implements PersonRepositoryCustom {
	private static final Logger LOG = Logger.getLogger(PersonRepositoryImpl.class.getName());
	
    @PersistenceContext
    EntityManager entityManager;
    
    @Autowired
	JdbcTemplate jdbcTemplate;
    
    @Override
    public List<Person> personDetails(Long id) {
    	LOG.log(Level.INFO, "GET :: Service4, Fetching person for parentid : "+ id);
    	return jdbcTemplate.query("select * from person where parent_id = ?", 
    			new Object[] {id}, new BeanPropertyRowMapper<Person>(Person.class));
    	
    }
    class StudentRowMapper implements RowMapper<Person> {
		@Override
		public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
			System.out.println(rs.getString("name"));
			Person student = new Person();
			student.setId(rs.getLong("id"));
			student.setName(rs.getString("name"));
			return student;
		}

	}
}