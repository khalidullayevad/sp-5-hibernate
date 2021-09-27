package com.example.sp5.services;

import com.example.sp5.entites.Book;

import java.util.List;

public interface BookService {
    List<Book> getAllBooks();

    List<Book> getAllBookAndSort(int page, String sortBy, String sortOrder);

    List<Book> sortAndSearchBookByTitle(int page, String sortBy, String sortOrder, String title);
    List<Book> sortAndSearchBookByTitleAndYear(int page,  String sortBy, String sortOrder, String title, Long year);



}
