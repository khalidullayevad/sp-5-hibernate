package com.example.sp5.services.impl;

import com.example.sp5.dto.AuthorDto;
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
    public List<AuthorDto> getAllAuthors() {
        //return (List<Author>) authorRepository.findAll();
        return  entityManager.createQuery("SELECT new com.example.sp5.dto.AuthorDto(a) FROM Author  a ")
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
    public List<Author> getAllAuthorPageable(int page,int size) {
        return  entityManager.createQuery("SELECT a FROM Author  a ")
                .setFirstResult(page * size)
                .setMaxResults((page + 1)*size)
                .getResultList();

    }
    @Override
    public List<Author> getAllAuthorPageableAndSort(int page,  String sortBy, String sortOrder) {
        String qur ;
        if(("name".equals(sortBy.toLowerCase())|| "birthdate".equals(sortBy.toLowerCase())) &&
                ("desc".equals(sortOrder.toLowerCase())||"asc".equals(sortOrder.toLowerCase()))){
            qur ="SELECT a FROM Author  a  ORDER BY a."+sortBy+" "+sortOrder;
        }else {
            qur ="SELECT a FROM Author  a  ORDER BY a.id asc";
        }
        return entityManager.createQuery(qur)
                .setFirstResult(page * 2)
                .setMaxResults(page*2+2)
                .getResultList();


    }

    @Override
    public AuthorDto getAuthor(Long id) {
        return (AuthorDto) entityManager.createQuery("SELECT new com.example.sp5.dto.AuthorDto(a)  FROM Author  a where  a.id =?1")
                .setParameter(1,id)
                .getSingleResult();
    }

}
