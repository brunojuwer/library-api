package br.com.juwer.libraryapi.domain.service;

import br.com.juwer.libraryapi.domain.model.Book;
import br.com.juwer.libraryapi.domain.reposiroty.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository repository;

    public Book save(Book book) {
        return repository.save(book);
    }
}
