package br.com.juwer.libraryapi.service;

import br.com.juwer.libraryapi.domain.model.Book;
import br.com.juwer.libraryapi.domain.service.BookService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles( "test" )
public class BookServiceTest {

    @MockBean
    private BookService service;

    @Test
    @DisplayName("It should save a book")
    void saveBookTest() {
        // cenário
        Book book = Book.builder()
                .author("Bruno")
                .title("Caminhos da escuridão")
                .isbn("9")
                .build();

        Mockito
                .when(service.save(book))
                .thenReturn(
                        Book.builder()
                        .id(1L)
                        .author("Bruno")
                        .title("Caminhos da escuridão")
                        .isbn("9")
                        .build()
                    );
        // execução
        Book savedBook = service.save(book);

        // validação
        assertThat(savedBook.getId()).isNotNull();
        assertThat(savedBook.getIsbn()).isEqualTo("9");
        assertThat(savedBook.getTitle()).isEqualTo("Caminhos da escuridão");
        assertThat(savedBook.getAuthor()).isEqualTo("Bruno");
    }
}
