package br.com.juwer.libraryapi.api.controller;

import br.com.juwer.libraryapi.api.model.input.BookInput;
import br.com.juwer.libraryapi.domain.model.Book;
import br.com.juwer.libraryapi.domain.service.BookService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles( "test" )
@AutoConfigureMockMvc
@SpringBootTest
public class BookControllerTest {

    static final String BOOK_API = "/api/books";


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService service;

    @Test
    @DisplayName("It should create a book with success")
    void createBookTest() throws Exception {

        BookInput bookInput = BookInput.builder()
                .author("Bruno")
                .title("Caminhos da escuridão")
                .isbn("9")
                .build();

        String json = new ObjectMapper().writeValueAsString(bookInput);

        Book book = Book.builder()
                .id(1L)
                .author("Bruno")
                .title("Caminhos da escuridão")
                .isbn("9")
                .build();

        BDDMockito
                .given(service.save(Mockito.any(Book.class)))
                .willReturn(book);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(BOOK_API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(request)
                .andExpect( status().isCreated() )
                .andExpect( jsonPath("id").isNotEmpty())
                .andExpect( jsonPath("title").value(bookInput.getTitle()))
                .andExpect( jsonPath("author").value(bookInput.getAuthor()))
                .andExpect( jsonPath("isbn").value(bookInput.getIsbn()))

        ;
    }

    @Test
    @DisplayName("It should throw a validation error when there is not enough data to create a book")
    void createInvalidBookTest() throws Exception {
        String json = new ObjectMapper().writeValueAsString(new BookInput());

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(BOOK_API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(request)
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("errors", hasSize(3)))

        ;
    }

}
