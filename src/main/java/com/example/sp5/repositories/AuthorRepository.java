package com.example.sp5.repositories;

import com.example.sp5.entites.Author;
import com.example.sp5.entites.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import java.sql.Date;
import java.util.List;

@Repository
public interface AuthorRepository  extends PagingAndSortingRepository<Author,Long> {

    List<Author> findAllByBirthdateBetween(Date at, Date from);


    Page<Author> findAll(Pageable pageable);

}
