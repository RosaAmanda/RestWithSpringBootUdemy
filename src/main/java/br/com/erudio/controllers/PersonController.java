package br.com.erudio.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@Autowired
	private PagedResourcesAssembler<PersonVO> assembler;

	@ApiOperation(value="Find all people")
	@GetMapping
	public ResponseEntity<?> findAll(
			@RequestParam(value="page", defaultValue="0") int page,
			@RequestParam(value="size", defaultValue="12") int size,
			@RequestParam(value="sort", defaultValue="asc") String sort) {

		var sortDirection = "desc".equalsIgnoreCase(sort) ? Direction.DESC : Direction.ASC;

		Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "firstName"));

		Page<PersonVO> persons = services.findAll(pageable);

		persons
			.stream()
			.forEach(person -> person.add(
					linkTo(methodOn(PersonController.class).findById(person.getId())).withSelfRel()
			));
		
		PagedModel<?> pagedModel = assembler.toModel(persons);
		
		return new ResponseEntity<>(pagedModel, HttpStatus.OK); 
	}

	@ApiOperation(value="Find people by name")
	@GetMapping(value="/findByFirstName/{firstName}")
	public ResponseEntity<?> findByFirstName(
			@RequestParam(value="page", defaultValue="0") int page,
			@RequestParam(value="size", defaultValue="12") int size,
			@RequestParam(value="sort", defaultValue="asc") String sort,
			@PathVariable("firstName") String firstName) {

		var sortDirection = "desc".equalsIgnoreCase(sort) ? Direction.DESC : Direction.ASC;

		Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "firstName"));

		Page<PersonVO> persons = services.findByFirstName(firstName, pageable);

		persons
			.stream()
			.forEach(person -> person.add(
					linkTo(methodOn(PersonController.class).findById(person.getId())).withSelfRel()
			));
		
		PagedModel<?> pagedModel = assembler.toModel(persons);
		
		return new ResponseEntity<>(pagedModel, HttpStatus.OK); 
	}

	@ApiOperation(value="Find by id")
	@GetMapping("/{id}")
	public PersonVO findById(@PathVariable(value="id") Long id) {
		PersonVO person = services.findById(id);
		//person.add(linkTo(methodOn(PersonController.class).findAll()).withRel("Lista de produtos"));
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
