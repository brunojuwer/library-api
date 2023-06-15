package br.com.juwer.libraryapi.api.model.input;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookInput {

    @NotBlank
    private String title;

    @NotBlank
    private String author;

    @NotBlank
    private String isbn;
}
