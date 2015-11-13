package base;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import domain.PersonDomainModel;

public class Person_Test {
	
	private static PersonDomainModel person_test1;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		Date Date_test = null;
		try {
			Date_test = new SimpleDateFormat("yyyy-MM-dd").parse("2002-12-14");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		person_test1 = new PersonDomainModel();
		person_test1.setFirstName("Bill");
		person_test1.setLastName("Jones");
		person_test1.setBirthday(Date_test);
		person_test1.setCity("Newark");
		person_test1.setPostalCode(19717);
		person_test1.setStreet("22  Maple Shade Drive");
	
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		
		PersonDomainModel person_test2;	
		PersonDAL.deletePerson(person_test1.getPersonID());
		person_test2 = PersonDAL.getPerson(person_test1.getPersonID());
		assertNull("person_test1 error, person_test1 did not belong in the database",person_test2);
		
	}
	
	@Test
	public void test1_deletingFormTheDatabase()
	{		
		PersonDomainModel person_test2;		
		person_test2 = PersonDAL.getPerson(person_test1.getPersonID());		
		assertNull("person_test1 error, person_test1 did not belong in the database",person_test2);	
		
		PersonDAL.addPerson(person_test1);			
		person_test2 = PersonDAL.getPerson(person_test1.getPersonID());
		System.out.println("person_test1 was found in the database: " + person_test1.getPersonID());
		assertNotNull("person_test1 error, person_test1 did not belong in the database",person_test2);
		
		PersonDAL.deletePerson(person_test1.getPersonID());
		person_test2 = PersonDAL.getPerson(person_test1.getPersonID());		
		assertNull("person_test1 error, person_test1 did not belong in the database",person_test2);	
		
	}
	
	@Test
	public void test2_updatingTheDatabase()
	{		
		PersonDomainModel person_test2;
		final String lastName_test = "Fredrickson";
		
		person_test2 = PersonDAL.getPerson(person_test1.getPersonID());		
		assertNull("person_test1 error, person_test1 did not belong in the database",person_test1);		
		PersonDAL.addPerson(person_test1);	
		
		person_test1.setLastName(lastName_test);
		PersonDAL.updatePerson(person_test1);
		
		person_test2 = PersonDAL.getPerson(person_test1.getPersonID());

		assertTrue("person_test1 error, person_test1.lastName_test is unchanged",person_test1.getLastName() == lastName_test);
	}


	@Test
	public void test3_addingToDatabase() {
		PersonDomainModel person_test2;		
		person_test2 = PersonDAL.getPerson(person_test1.getPersonID());		
		assertNull("person_test1 error, person_test1 did not belong in the database",person_test2);		
		PersonDAL.addPerson(person_test1);	
		
		person_test2 = PersonDAL.getPerson(person_test1.getPersonID());
		System.out.println("person_test1 was found in the database: " + person_test1.getPersonID());
		assertNotNull("person_test1 error, person_test1 did not belong in the database",person_test2);
	}

}
