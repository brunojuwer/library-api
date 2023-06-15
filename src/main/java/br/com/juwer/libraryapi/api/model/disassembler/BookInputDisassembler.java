package br.com.juwer.libraryapi.api.model.disassembler;

import br.com.juwer.libraryapi.api.model.input.BookInput;
import br.com.juwer.libraryapi.domain.model.Book;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookInputDisassembler {

    private final ModelMapper mapper;

    public Book toDomainModel(BookInput bookInput) {
        return mapper.map(bookInput, Book.class);
    }

}
