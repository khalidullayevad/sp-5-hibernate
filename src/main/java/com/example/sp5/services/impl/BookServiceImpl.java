package com.example.sp5.services.impl;

import com.example.sp5.entites.Book;
import com.example.sp5.repositories.BookRepository;
import com.example.sp5.services.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getAllBooks() {
        return (List<Book>) bookRepository.findAll();
    }


    @Override
    public List<Book> getAllBookAndSort(int page, int size, String sortBy, String sortOrder) {
        Pageable paging;
        if("asc".equals(sortOrder)) {
            paging = PageRequest.of(page, size, Sort.by(sortBy).ascending());
        }
        else {
            paging = PageRequest.of(page, 2, Sort.by(sortBy).descending());
        }
        Page<Book> pagedResult = bookRepository.findAll(paging);

        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return null;
        }
    }

    @Override
    public List<Book> sortAndSearchBookByTitle(int page, int size, String sortBy, String sortOrder, String title) {
            List<Book> books1 =  getAllBookAndSort(page, size, sortBy, sortOrder);
            List<Book> books2 = bookRepository.findByTitleContaining(title);
            List<Book> books =new ArrayList<>();
            for(Book b: books1){
                for(Book t: books2){
                    if(b.equals(t)){
                        books.add(b);
                    }
                }
            }
            return books;

    }

    @Override
    public List<Book> sortAndSearchBookByTitleAndYear(int page, int size, String sortBy, String sortOrder, String title, Long year) {
        List<Book> books1 =  getAllBookAndSort(page, size, sortBy, sortOrder);
        List<Book> books2 = bookRepository.findByTitleContainingAndIssueYear(title,year);
        List<Book> books =new ArrayList<>();
        for(Book b: books1){
            for(Book t: books2){
                if(b.equals(t)){
                    books.add(b);
                }
            }
        }
        return books;

    }


}
