package br.com.juwer.libraryapi.domain.reposiroty;

import br.com.juwer.libraryapi.domain.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
