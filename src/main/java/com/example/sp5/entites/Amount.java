package com.example.sp5.entites;

import com.example.sp5.dto.AuthorDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Amount {
    private AuthorDto author;
    private Long sum;

    public Amount(Author author, Long amount){
        this.sum= amount;
        this.author= new AuthorDto(author);
    }


}
