package br.com.erudio.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.erudio.data.vo.v1.BookVO;
import br.com.erudio.services.BookServices;
import io.swagger.annotations.Api;

@Api(value="BookEndpoint", tags={"book"})
@RestController
@RequestMapping("/api/book/v1")
public class BookController {
	
	@Autowired
	private BookServices services;
	
	@GetMapping
	public List<BookVO> findAll() {
		List<BookVO> books = services.findAll();
			
		for(BookVO book : books) {
			long id = book.getId();
			book.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
		}
		return books; 
	}

	@GetMapping("/{id}")
	public BookVO findById(@PathVariable(value="id") Long id) {
		BookVO book = services.findById(id);
		book.add(linkTo(methodOn(BookController.class).findAll()).withRel("Lista de produtos"));
		return book; 
	}

	@PostMapping
	public BookVO create(@RequestBody BookVO book) {
		BookVO BookVO = services.create(book);
		BookVO.add(linkTo(methodOn(BookController.class).findById(BookVO.getId())).withSelfRel());
		return services.create(BookVO); 
	}

	@PutMapping
	public BookVO update(@RequestBody BookVO book) {
		BookVO BookVO = services.update(book);
		BookVO.add(linkTo(methodOn(BookController.class).findById(BookVO.getId())).withSelfRel());
		return services.update(book); 
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(value="id") Long id) {
		services.delete(id);
		return ResponseEntity.ok().build();
	}
}