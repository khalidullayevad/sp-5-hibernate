package com.example.sp5.services.impl;

import com.example.sp5.entites.Book;
import com.example.sp5.repositories.BookRepository;
import com.example.sp5.services.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @PersistenceContext
    protected EntityManager entityManager;
    @Override
    public List<Book> getAllBooks() {

        return entityManager.createQuery("SELECT a, b FROM Author  a , Book  b where  a.id = b.author.id")
                .getResultList();
    }


    @Override
    public List<Book> getAllBookAndSort(int page, String sortBy, String sortOrder) {
        String qur ="SELECT a FROM Book  a  ORDER BY "+sortBy+" "+sortOrder;
        return entityManager.createQuery(qur)
                .setFirstResult(page * 2)
                .setMaxResults(page*2+2)
                .getResultList();
    }

    @Override
    public List<Book> sortAndSearchBookByTitle(int page,  String sortBy, String sortOrder, String title) {
        String qur ="SELECT a FROM Book  a  where  a.title like" +"%"+title+"%"+" ORDER BY "+sortBy+" "+sortOrder;
        return entityManager.createQuery(qur)
                .setFirstResult(page * 2)
                .setMaxResults(page*2+2)
                .getResultList();


    }

    @Override
    public List<Book> sortAndSearchBookByTitleAndYear(int page, String sortBy, String sortOrder, String title, Long year) {
        String qur ="SELECT a FROM Book  a  where  a.title like" +"%"+title+"%"+" AND a.issueYear="+year +" ORDER BY "+sortBy+" "+sortOrder;
        return entityManager.createQuery(qur)
                .setFirstResult(page * 2)
                .setMaxResults(page*2+2)
                .getResultList();


    }


}
