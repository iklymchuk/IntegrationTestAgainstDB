package com.klymchuk;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.klymchuk.dao.PersonDAO;
import com.klymchuk.entity.Person;

/**
 * Unit test for simple App.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
@WebAppConfiguration
@SqlGroup({
    @Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:beforeTestRun.sql"),
    @Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:afterTestRun.sql") 
})
public class AppTest {
	
	 @Autowired
	 private PersonDAO personDAO;
	 
	 @Test
	 public void should_update_password() {
		 
		 Person person = new Person();
		 	person.setPersonId(1);
		 	person.setPassword("newPass");
		 	
		 	personDAO.updatePassword(person);
		 	
		 	//validate update
		 	Person updatedPerson = personDAO.getPerson(1);
		 	assertEquals("newPass", updatedPerson.getPassword());	
	 }
   
}
