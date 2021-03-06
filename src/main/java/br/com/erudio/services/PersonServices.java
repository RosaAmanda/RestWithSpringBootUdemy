package br.com.erudio.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.erudio.converter.DozerConverter;
import br.com.erudio.data.model.Person;
import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.exception.ResourceNotFoundException;
import br.com.erudio.repository.PersonRepository;

@Service
public class PersonServices {
	
	@Autowired
	PersonRepository repository;
	
	public PersonVO create(PersonVO person) {
		var entity = DozerConverter.parseObject(person, Person.class);
		var vo = DozerConverter.parseObject(repository.save(entity), PersonVO.class);
		return vo;
	}

	public PersonVO update(PersonVO person) {
		var entity = repository.findById(person.getId())
				               .orElseThrow(() -> new ResourceNotFoundException("No records found for this id."));

		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());

		var vo = DozerConverter.parseObject(repository.save(entity), PersonVO.class);
		
		return vo;
	}

	@Transactional
	public PersonVO disablePerson(Long id) {
		repository.disablePerson(id);

		var entity = repository.findById(id)
	               .orElseThrow(() -> new ResourceNotFoundException("No records found for this id."));

		var vo = DozerConverter.parseObject(repository.save(entity), PersonVO.class);
		
		return vo;
	}

	public void delete(Long id) {
		Person person = repository.findById(id)
								  .orElseThrow(() -> new ResourceNotFoundException("No records found for this id."));
		
		repository.delete(person);
	}

	public PersonVO findById(Long id) {
		var entity = repository.findById(id)
						 .orElseThrow(() -> new ResourceNotFoundException("No records found for this id."));
		
		return DozerConverter.parseObject(entity, PersonVO.class);
	}
	
	public Page<PersonVO> findAll(Pageable pageable) {
		var page = repository.findAll(pageable);
		return page.map(this::convertToPersonVO);
	}

	public Page<PersonVO> findByFirstName(String firstName, Pageable pageable) {
		var page = repository.findPersonByFirstName(firstName, pageable);
		return page.map(this::convertToPersonVO);
	}

	private PersonVO convertToPersonVO(Person entity) {
		return DozerConverter.parseObject(entity, PersonVO.class);
	}

}
