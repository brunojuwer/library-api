package br.com.juwer.libraryapi.api.model.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {

    private Long id;
    private String title;
    private String author;
    private String isbn;
}
