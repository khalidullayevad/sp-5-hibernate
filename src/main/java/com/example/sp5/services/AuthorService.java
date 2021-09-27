package com.example.sp5.services;

import com.example.sp5.entites.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.sql.Date;
import java.util.List;

public interface AuthorService {

    List<Author> getAllAuthors();
    List<Author> getAllAuthorsByBirthDate(Date from, Date to);
    List<Author> getAllAuthorPageable(int page);
    List<Author> getAllAuthorPageableAndSort(int page, int size, String sortBy, String sortOrder);

}
