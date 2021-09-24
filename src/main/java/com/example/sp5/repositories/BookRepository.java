package com.example.sp5.repositories;

import com.example.sp5.entites.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends PagingAndSortingRepository<Book,Long> {
    List<Book> findAllByAuthor_Id(Long id);
    List<Book> findByTitleContaining(String title);
    List<Book> findByTitleContainingAndIssueYear(String title, Long year);
}
