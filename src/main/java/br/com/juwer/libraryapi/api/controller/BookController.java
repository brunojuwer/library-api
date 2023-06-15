package br.com.juwer.libraryapi.api.controller;

import br.com.juwer.libraryapi.api.exceptionhandler.ApiError;
import br.com.juwer.libraryapi.api.model.assembler.BookDTOAssembler;
import br.com.juwer.libraryapi.api.model.disassembler.BookInputDisassembler;
import br.com.juwer.libraryapi.api.model.dto.BookDTO;
import br.com.juwer.libraryapi.api.model.input.BookInput;
import br.com.juwer.libraryapi.domain.model.Book;
import br.com.juwer.libraryapi.domain.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService service;
    private final BookDTOAssembler assembler;
    private final BookInputDisassembler disassembler;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookDTO create(@RequestBody @Valid BookInput bookInput) {
        Book book = disassembler.toDomainModel(bookInput);
        return assembler.toModel(service.save(book));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        return new ApiError(bindingResult);
    }
}
