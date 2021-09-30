package com.example.sp5.services;

import com.example.sp5.dto.AuthorDto;
import com.example.sp5.entites.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.sql.Date;
import java.util.List;

public interface AuthorService {

    List<AuthorDto> getAllAuthors();
    List<Author> getAllAuthorsByBirthDate(Date from, Date to);
    List<Author> getAllAuthorPageable(int page,int size);
    List<Author> getAllAuthorPageableAndSort(int page, String sortBy, String sortOrder);

    AuthorDto getAuthor(Long id);

}
