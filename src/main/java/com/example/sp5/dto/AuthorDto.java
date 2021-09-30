package com.example.sp5.dto;

import com.example.sp5.entites.Author;
import lombok.Data;

import java.sql.Date;

@Data
public class AuthorDto {

    private Long id;
    private String name;
    private Date birthdate;

    public AuthorDto(Author author) {
        this.id = author.getId();
        this.name = author.getName();
        this.birthdate = author.getBirthdate();
    }

}
