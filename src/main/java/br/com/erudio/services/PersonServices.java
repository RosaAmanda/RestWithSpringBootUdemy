package br.com.erudio.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import br.com.erudio.model.Person;

@Service
public class PersonServices {
	
	private final AtomicLong counter = new AtomicLong();
	
	public Person create(Person person) {
		person.setId(counter.incrementAndGet());
		return person;
	}

	public Person update(Person person) {
		person.setFirstName(person.getFirstName() + " Alterado");
		return person;
	}

	public void delete(String id) {
		//será implementado via comunicação com o banco de dados
	}

	public Person findById(String id) {
		Person person = new Person();
		person.setId(counter.incrementAndGet());
		person.setFirstName("Amanda");
		person.setLastName("Vieira");
		person.setAddress("Rua ABC");
		person.setGender("Female");
		return person ;
	}
	
	public List<Person> findAll() {
		List<Person> persons = new ArrayList<Person>();
		
		for (int i = 0; i < 8; i++) {
			persons.add(mockPerson(i));
		}

		return persons;
	}

	private Person mockPerson(int i) {
		Person person = new Person();
		person.setId(counter.incrementAndGet());
		person.setFirstName("Person name "+i);
		person.setLastName("Last Name");
		person.setAddress("Rua ABC");
		person.setGender("Female");
		return person;
	}
}
