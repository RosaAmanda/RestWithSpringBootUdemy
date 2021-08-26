package br.com.erudio.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.services.PersonServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

//@CrossOrigin habilita cors por controller
@Api(value="PersonEndpoint", tags={"person"})
@RestController
@RequestMapping("/api/person/v1")
public class PersonController {
	
	@Autowired //injeção de dependência
	private PersonServices services;

	@ApiOperation(value="Find all people")
	@GetMapping
	public List<PersonVO> findAll() {
		List<PersonVO> persons = services.findAll();
			
		for(PersonVO person : persons) {
			long id = person.getId();
			person.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		}
		return persons; 
	}

	@ApiOperation(value="Find by id")
	@GetMapping("/{id}")
	public PersonVO findById(@PathVariable(value="id") Long id) {
		PersonVO person = services.findById(id);
		person.add(linkTo(methodOn(PersonController.class).findAll()).withRel("Lista de produtos"));
		return person; 
	}

	@ApiOperation(value="Create person")
	@PostMapping
	public PersonVO create(@RequestBody PersonVO person) {
		PersonVO personVO = services.create(person);
		personVO.add(linkTo(methodOn(PersonController.class).findById(personVO.getId())).withSelfRel());
		return services.create(personVO); 
	}

	@ApiOperation(value="Update person")
	@PutMapping
	public PersonVO update(@RequestBody PersonVO person) {
		PersonVO personVO = services.update(person);
		personVO.add(linkTo(methodOn(PersonController.class).findById(personVO.getId())).withSelfRel());
		return services.update(person); 
	}

	@ApiOperation(value="Disable by id")
	@PatchMapping("/{id}")
	public PersonVO disablePerson(@PathVariable(value="id") Long id) {
		PersonVO person = services.disablePerson(id);
		person.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		return person; 
	}

	@ApiOperation(value="Delete person")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(value="id") Long id) {
		services.delete(id);
		return ResponseEntity.ok().build();
	}
}
