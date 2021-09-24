package com.example.sp5.services.impl;

import com.example.sp5.entites.Author;
import com.example.sp5.repositories.AuthorRepository;
import com.example.sp5.services.AuthorService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class AuthServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    public AuthServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> getAllAuthors() {
        return (List<Author>) authorRepository.findAll();
    }

    @Override
    public List<Author> getAllAuthorsByBirthDate(Date from, Date to) {
        return authorRepository.findAllByBirthdateBetween(from, to);
    }

    @Override
    public List<Author> getAllAuthorPageable(Pageable pageable) {
        return authorRepository.findAll(pageable).getContent();
    }
    @Override
    public List<Author> getAllAuthorPageableAndSort(int page, int size, String sortBy, String sortOrder) {
        Pageable paging;
        if("asc".equals(sortOrder)) {
            paging = PageRequest.of(page, size, Sort.by(sortBy).ascending());
        }
        else {
            paging = PageRequest.of(page, 2, Sort.by(sortBy).descending());
        }
        Page<Author> pagedResult = authorRepository.findAll(paging);

        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return null;
        }

    }
}
