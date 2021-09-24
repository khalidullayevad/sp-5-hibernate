package com.example.sp5.repositories;

import com.example.sp5.entites.Amount;
import com.example.sp5.entites.Author;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import java.sql.Date;
import java.util.List;
@Repository
public interface AmountRepository extends PagingAndSortingRepository<Author, Long> {

    //Select a.name, count(*) FROM Author a, Book b where a.birthdate BETWEEN "1845-08-10" and"1845-08-12" AND a.id = b.author_id GROUP BY a.name ORDER by a.name DESC;

    @Query( value = "Select new com.example.sp5.entites.Amount(a, count(a)) FROM  Author  a, Book b where a.id = b.author.id GROUP BY a.name")
    List<Amount> findBooksCount();



    @Query( value = "Select new com.example.sp5.entites.Amount(a, count(a)) FROM  Author  a, Book b where a.id = b.author.id GROUP BY a.name order by count(a) asc")
    List<Amount> sortAndPagingAscCount(Pageable pageable);

    @Query( value = "Select new com.example.sp5.entites.Amount(a, count(a)) FROM  Author  a, Book b where a.id = b.author.id GROUP BY a.name order by count(a) desc")
    List<Amount> sortAndPagingDescCount(Pageable pageable);

    @Query( value = "Select new com.example.sp5.entites.Amount(a, count(a)) FROM  Author  a, Book b where a.id = b.author.id GROUP BY a.name order by a.name asc")
    List<Amount> sortAndPagingAscName(Pageable pageable);

    @Query( value = "Select new com.example.sp5.entites.Amount(a, count(a)) FROM  Author  a, Book b where a.id = b.author.id GROUP BY a.name order by a.name desc ")
    List<Amount> sortAndPagingDescName(Pageable pageable);



    @Query( value ="Select new com.example.sp5.entites.Amount(a, count(a)) FROM  Author  a, Book b where a.birthdate BETWEEN  ?1 and ?2  AND a.id = b.author.id GROUP BY a.name ORDER by a.name DESC")
    List<Amount> sortAndPagingAmountBetweenDdateDescName(Pageable pageable,Date at,Date from);

    @Query( value ="Select new com.example.sp5.entites.Amount(a, count(a)) FROM  Author  a, Book b where a.birthdate BETWEEN  ?1 and ?2  AND a.id = b.author.id GROUP BY a.name ORDER by a.name ASC")
    List<Amount> sortAndPagingAmountBetweenDdateAscName(Pageable pageable,Date at,Date from);

    @Query( value ="Select new com.example.sp5.entites.Amount(a, count(a)) FROM  Author  a, Book b where a.birthdate BETWEEN  ?1 and ?2  AND a.id = b.author.id GROUP BY a.name ORDER by  count(a) DESC")
    List<Amount> sortAndPagingAmountBetweenDdateDescCount(Pageable pageable,Date at,Date from);

    @Query( value ="Select new com.example.sp5.entites.Amount(a, count(a)) FROM  Author  a, Book b where a.birthdate BETWEEN  ?1 and ?2  AND a.id = b.author.id GROUP BY a.name ORDER by count(a) ASC")
    List<Amount> sortAndPagingAmountBetweenDdateAscCount(Pageable pageable,Date at,Date from);







}
