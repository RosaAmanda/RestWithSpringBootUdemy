package br.com.erudio.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.erudio.converter.DozerConverter;
import br.com.erudio.data.model.Book;
import br.com.erudio.data.vo.v1.BookVO;
import br.com.erudio.exception.ResourceNotFoundException;
import br.com.erudio.repository.BookRepository;

@Service
public class BookServices {
	
	@Autowired
	BookRepository repository;
	
	public BookVO create(BookVO book) {
		var entity = DozerConverter.parseObject(book, Book.class);
		var vo = DozerConverter.parseObject(repository.save(entity), BookVO.class);
		return vo;
	}

	public BookVO update(BookVO book) {
		var entity = repository.findById(book.getId())
				               .orElseThrow(() -> new ResourceNotFoundException("No records found for this id."));

		entity.setAuthor(book.getAuthor());
		entity.setLaunchDate(book.getLaunchDate());
		entity.setPrice(book.getPrice());
		entity.setTitle(book.getTitle());

		var vo = DozerConverter.parseObject(repository.save(entity), BookVO.class);
		
		return vo;
	}

	public void delete(Long id) {
		Book book = repository.findById(id)
							  .orElseThrow(() -> new ResourceNotFoundException("No records found for this id."));
		
		repository.delete(book);
	}

	public BookVO findById(Long id) {
		var entity = repository.findById(id)
						       .orElseThrow(() -> new ResourceNotFoundException("No records found for this id."));
		
		return DozerConverter.parseObject(entity, BookVO.class);
	}
	
	public List<BookVO> findAll() {
		return DozerConverter.parseListObjects(repository.findAll(), BookVO.class);
	}
}
