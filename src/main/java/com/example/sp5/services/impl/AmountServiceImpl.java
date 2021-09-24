package com.example.sp5.services.impl;

import com.example.sp5.entites.Amount;
import com.example.sp5.entites.Author;
import com.example.sp5.repositories.AmountRepository;
import com.example.sp5.services.AmountService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
@Service
public class AmountServiceImpl implements AmountService {
    private final AmountRepository amountRepository;

  //  private EntityManager entityManager;

    public AmountServiceImpl(AmountRepository amountRepository) {
        this.amountRepository = amountRepository;
    }

    @Override
    public List<Amount> getAllAmounts() {
        return amountRepository.findBooksCount();
    }


    @Override
    public List<Amount> sortAmount(String sortBy, String sortOrder, int page) {
        Pageable paging = PageRequest.of(page, 2);;
//        String jql = "Select new com.example.sp5.entites.Amount(a, count(a)) FROM  Author  a, Book b where a.id = b.author.id GROUP BY a.name order by "+sortBy+" "+ sortOrder;
//        Query barQuery = entityManager.createQuery(jql);
//        List<Amount> amount = barQuery.getResultList();
        List<Amount> amount;

        if("asc".equals(sortOrder) && "count".equals(sortBy)) {
            amount=  amountRepository.sortAndPagingAscCount(paging);

        }
        else if("desc".equals(sortOrder) && "count".equals(sortBy)){
            amount=  amountRepository.sortAndPagingDescCount(paging);
        }
        else if("desc".equals(sortOrder) && "name".equals(sortBy)){
            amount = amountRepository.sortAndPagingAscName(paging);
        }
        else {
            amount = amountRepository.sortAndPagingDescName(paging);
        }
        return amount;
    }

    @Override
    public List<Amount> sortAmountWithBdate(String sortBy, String sortOrder, int page, Date at, Date from) {
        Pageable paging = PageRequest.of(page, 2);;

        List<Amount> amount;

        if("asc".equals(sortOrder) && "count".equals(sortBy)) {
            amount=  amountRepository.sortAndPagingAmountBetweenDdateAscCount(paging,at,from);

        }
        else if("desc".equals(sortOrder) && "count".equals(sortBy)){
            amount=  amountRepository.sortAndPagingAmountBetweenDdateDescCount(paging,at,from);
        }
        else if("desc".equals(sortOrder) && "name".equals(sortBy)){
            amount = amountRepository.sortAndPagingAmountBetweenDdateAscName(paging,at,from);
        }
        else {
            amount = amountRepository.sortAndPagingAmountBetweenDdateDescName(paging,at,from);
        }
        return amount;
    }
}
