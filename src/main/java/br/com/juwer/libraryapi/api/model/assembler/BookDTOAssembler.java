package br.com.juwer.libraryapi.api.model.assembler;

import br.com.juwer.libraryapi.api.model.dto.BookDTO;
import br.com.juwer.libraryapi.domain.model.Book;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookDTOAssembler {

    private final ModelMapper mapper;

    public BookDTO toModel(Book book) {
        return mapper.map(book, BookDTO.class);
    }
}
