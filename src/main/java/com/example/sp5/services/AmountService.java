package com.example.sp5.services;

import com.example.sp5.entites.Amount;
import com.example.sp5.entites.Author;

import java.sql.Date;
import java.util.List;

public interface AmountService {
    List<Amount> getAllAmounts();
    List<Amount> sortAmount(String sortBy, String sortOrder, int page);


    List<Amount> sortAmountWithBdate(String sortBy, String sortOrder, int page, Date at, Date to);
}
