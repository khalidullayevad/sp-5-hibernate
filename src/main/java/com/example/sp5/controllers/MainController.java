package com.example.sp5.controllers;

import com.example.sp5.entites.Amount;
import com.example.sp5.entites.Author;
import com.example.sp5.entites.Book;
import com.example.sp5.services.AuthorService;
import com.example.sp5.services.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@RestController
public class MainController{

    private final AuthorService authorService;

    private final BookService bookService;

    public MainController(AuthorService authorService, BookService bookService) {
        this.authorService = authorService;
        this.bookService = bookService;
    }

//    Получить список всех авторов
    @GetMapping(value = "/allAuthors")
    public ResponseEntity<?> getAllAuthors(){
        List<Author> authors =authorService.getAllAuthors();
        return new ResponseEntity<>(authors, HttpStatus.OK);
    }

//    Получить список всех авторов с постраничностью
    @GetMapping(value = "/allAuthorsWithPapageable")
    public ResponseEntity<?> getAllAuthorsWithPapageable(@RequestParam int page){
        Pageable pageable= PageRequest.of(page, 2);
        List<Author> authors =authorService.getAllAuthorPageable(pageable);
        return new ResponseEntity<>(authors, HttpStatus.OK);
    }

//    Получить список всех авторов с выбором сортировки + с постраничностью
    @GetMapping(value = "/sortAuthor")
    public ResponseEntity<?> getAllAuthorsWithPapageable(@RequestParam String sortBy, @RequestParam  String sortOrder, @RequestParam int page, @RequestParam int size){
        List<Author> authors = authorService.getAllAuthorPageableAndSort(page,size,sortBy,sortOrder);
        return new ResponseEntity<>(authors, HttpStatus.OK);
    }

//    Получить список всех авторов рожденных с даты, по дату
    @GetMapping(value = "/searchByBirthDate")
    public ResponseEntity<?> getAllAuthorsByBirthDate(@RequestParam Date from,@RequestParam Date to){
        List<Author> authors =authorService.getAllAuthorsByBirthDate(from,to);
        return new ResponseEntity<>(authors, HttpStatus.OK);
    }

//    Получить список всех книг (вывести и книгу и автора)
    @GetMapping(value = "/allBooks")
    public ResponseEntity<?> getAllBooks(){
        List<Book> books =bookService.getAllBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }


//    Список книг (как выше) + постраничность и выбор сортировки
    @GetMapping(value = "/sortBook")
    public ResponseEntity<?> getAllAuthorBook(@RequestParam String sortBy, @RequestParam  String sortOrder, @RequestParam int page, @RequestParam int size){
        List<Book> books = bookService.getAllBookAndSort(page,size,sortBy,sortOrder);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

//    Список книг (как выше) + постраничность и выбор сортировки + поиск по названию
    @GetMapping(value = "/searchbytitle")
    public ResponseEntity<?> sortBookAndSearch(@RequestParam String sortBy, @RequestParam  String sortOrder,@RequestParam String title){
        List<Book> books = bookService.sortAndSearchBookByTitle(0,2,sortBy,sortOrder,title);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

//    Список книг (как выше) + постраничность и выбор сортировки + поиск по названию + поиск по году выпуска
    @GetMapping(value = "/searchbytitleandyear")
    public ResponseEntity<?> sortBookAndSearch(@RequestParam String sortBy, @RequestParam  String sortOrder,@RequestParam String title,@RequestParam Long year){
        List<Book> books = bookService.sortAndSearchBookByTitleAndYear(0,2,sortBy,sortOrder,title,year);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

//    Получить список авторов + количество книг (которые он написал)
    @GetMapping(value = "/amountOfBooks")
    public List<Amount> amountOfBooks(){
        List<Author> authors =authorService.getAllAuthors();
        List<Amount> amounts = new ArrayList<>();

        for(int i = 0; i<authors.size(); i++){
            Amount amount = new Amount();         ;
            amount.setSum(bookService.getAllBokksByAuthorId(authors.get(i).getId()).size());
            amount.setAuthor(authors.get(i).getName());
            amounts.add(amount);
        }

        return  amounts;
    }


//    Получить список авторов + количество книг (которые он написал) + постраничность + выбор соритировки

//    @GetMapping(value = "/authoramountsort")
//    public ResponseEntity<?> authorAmountSort(@RequestParam String sortBy, @RequestParam  String sortOrder,@RequestParam int page){
//        List<Author> authors =authorService.getAllAuthors();
//        List<Amount> amounts = new ArrayList<>();
//        for(int i = 0; i<authors.size(); i++){
//         authors.get(i). setBooks(bookService.getAllBokksByAuthorId(authors.get(i).getId()));
//
//        }
//        if("title".equals(sortBy) || "description".equals(sortBy) || "issueYear".equals(sortBy)){
//            bookService.getAllBookAndSort(page,2,sortBy,sortOrder);
//            return  new ResponseEntity<>(amounts,HttpStatus.OK)
//        }
//
//        return  new ResponseEntity<>(amounts,HttpStatus.OK);
//    }



//    Получить список авторов + количество книг (которые он написал) + постраничность + выбор соритировки + авторы рожденные с даты, по дату



}
