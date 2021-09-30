package com.example.sp5.dto;

import com.example.sp5.entites.Book;
import lombok.Data;

@Data
public class BookDto {

    private Long id;
    private String title;
    private String description;
    private Long issueYear;
    private AuthorDto author;

    public BookDto(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.description = book.getDescription();
        this.issueYear = book.getIssueYear();
        this.author = new AuthorDto(book.getAuthor());
    }

}
