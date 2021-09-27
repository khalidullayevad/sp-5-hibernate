package com.example.sp5.services.impl;

import com.example.sp5.entites.Author;

import com.example.sp5.services.AuthorService;

import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.sql.Date;
import java.util.List;

@Service
public class AuthServiceImpl implements AuthorService {


    @PersistenceContext protected EntityManager entityManager;



    @Override
    public List<Author> getAllAuthors() {
        //return (List<Author>) authorRepository.findAll();
        return  entityManager.createQuery("SELECT a FROM Author  a ")
                .getResultList();
    }

    @Override
    public List<Author> getAllAuthorsByBirthDate(Date from, Date to) {
       // return authorRepository.findAllByBirthdateBetween(from, to);
        return  entityManager.createQuery("SELECT a FROM Author a WHERE a.birthdate BETWEEN :startDate AND :endDate")
                .setParameter("startDate", from)
                .setParameter("endDate", to)
                .getResultList();
    }

    @Override
    public List<Author> getAllAuthorPageable(int page) {
        return  entityManager.createQuery("SELECT a FROM Author  a ")
                .setFirstResult(page * 2)
                .setMaxResults(page*2+2)
                .getResultList();

    }
    @Override
    public List<Author> getAllAuthorPageableAndSort(int page,  String sortBy, String sortOrder) {
        String qur ="SELECT a FROM Author  a  ORDER BY "+sortBy+" "+sortOrder;
        return entityManager.createQuery(qur)
                .setFirstResult(page * 2)
                .setMaxResults(page*2+2)
                .getResultList();


    }

}
