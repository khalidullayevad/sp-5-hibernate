package com.example.sp5.services.impl;

import com.example.sp5.entites.Amount;

import com.example.sp5.services.AmountService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.sql.Date;

import java.util.List;
@Service
public class AmountServiceImpl implements AmountService {


    @PersistenceContext
    protected EntityManager entityManager;


    @Override
    public List<Amount> getAllAmounts() {
        return entityManager.createQuery("Select new com.example.sp5.entites.Amount(a, count(a)) FROM  Author  a, Book b where a.id = b.author.id GROUP BY a.name").getResultList();
    }


    @Override
    public List<Amount> sortAmount(String sortBy, String sortOrder, int page) {
        if ("count".equals(sortBy) &&
                ("desc".equals(sortOrder.toLowerCase()) || "asc".equals(sortOrder.toLowerCase()))) {
            return entityManager.createQuery("Select new com.example.sp5.entites.Amount(a, count(a)) FROM  Author  a, Book b where a.id = b.author.id GROUP BY a.name order by count(a) " + " " + sortOrder)
                    .setFirstResult(page * 2)
                    .setMaxResults(page * 2 + 2)
                    .getResultList();

        } else if (("name".equals(sortBy.toLowerCase()) || "birthdate".equals(sortBy.toLowerCase())) &&
                ("desc".equals(sortOrder.toLowerCase()) || "asc".equals(sortOrder.toLowerCase()))) {
            return entityManager.createQuery("Select new com.example.sp5.entites.Amount(a, count(a)) FROM  Author  a, Book b where a.id = b.author.id GROUP BY a.name order by a." + sortBy + " " + sortOrder)
                    .setFirstResult(page * 2)
                    .setMaxResults(page * 2 + 2)
                    .getResultList();
        } else {
           return entityManager.createQuery("Select new com.example.sp5.entites.Amount(a, count(a)) FROM  Author  a, Book b where a.id = b.author.id GROUP BY a.name order by a.id desc ")
                    .setFirstResult(page * 2)
                    .setMaxResults(page * 2 + 2)
                    .getResultList();
        }
    }



    @Override
    public List<Amount> sortAmountWithBdate(String sortBy, String sortOrder, int page, Date at, Date from) {
        if("count".equals(sortBy)&&
                ("desc".equals(sortOrder.toLowerCase()) || "asc".equals(sortOrder.toLowerCase()))) {
            return entityManager.createQuery("Select new com.example.sp5.entites.Amount(a, count(a)) FROM  Author  a, Book b where a.birthdate BETWEEN  "+at+ " and "+ from+" AND a.id = b.author.id GROUP BY a.name ORDER by count (a)"+" "+sortOrder)
                    .setFirstResult(page * 2)
                    .setMaxResults(page*2+2)
                    .getResultList();

        }
        else if (("name".equals(sortBy.toLowerCase()) || "birthdate".equals(sortBy.toLowerCase())) &&
                ("desc".equals(sortOrder.toLowerCase()) || "asc".equals(sortOrder.toLowerCase()))) {
            return entityManager.createQuery("Select new com.example.sp5.entites.Amount(a, count(a)) FROM  Author  a, Book b " +
                            "where a.birthdate BETWEEN  :at and :from"+" AND a.id = b.author.id GROUP BY a.name ORDER by a." + sortBy + " "
                            + sortOrder)
                    .setParameter("at",at)
                    .setParameter("from",from)
                    .setFirstResult(page * 2)
                    .setMaxResults(page * 2 + 2)
                    .getResultList();
        }
        else{
            return entityManager.createQuery("Select new com.example.sp5.entites.Amount(a, count(a)) FROM  Author  a, Book b " +
                            "where a.birthdate BETWEEN  :at and :from"+" AND a.id = b.author.id GROUP BY a.name ORDER by a.id desc ")
                    .setParameter("at",at)
                    .setParameter("from",from)
                    .setFirstResult(page * 2)
                    .setMaxResults(page * 2 + 2)
                    .getResultList();
        }
    }
}
