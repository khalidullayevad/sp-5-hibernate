package com.example.sp5.controllers;

import com.example.sp5.dto.AuthorDto;
import com.example.sp5.dto.BookDto;
import com.example.sp5.entites.Amount;
import com.example.sp5.entites.Author;
import com.example.sp5.entites.Book;
import com.example.sp5.services.AmountService;
import com.example.sp5.services.AuthorService;
import com.example.sp5.services.BookService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class MainController{

    private final AuthorService authorService;

    private final BookService bookService;

    private final AmountService amountService;

    public MainController(AuthorService authorService, BookService bookService, AmountService amountService) {
        this.authorService = authorService;
        this.bookService = bookService;
        this.amountService = amountService;
    }



//    Получить список всех авторов //
    @GetMapping(value = "/allAuthors")
    public ResponseEntity<?> getAllAuthors(){
        List<AuthorDto> authors =authorService.getAllAuthors();
        return new ResponseEntity<>(authors, HttpStatus.OK);
    }

//    Получить список всех авторов с постраничностью //
    @GetMapping(value = "/allAuthorsWithPapageable")
    public ResponseEntity<?> getAllAuthorsWithPapageable(@RequestParam int page, @RequestParam int size){

        List<Author> authors =authorService.getAllAuthorPageable(page,size);
        return new ResponseEntity<>(authors, HttpStatus.OK);
    }

//    Получить список всех авторов с выбором сортировки + с постраничностью //
    @GetMapping(value = "/sortAuthor")
    public ResponseEntity<?> getAllAuthorsWithPapageable(@RequestParam String sortBy, @RequestParam  String sortOrder, @RequestParam int page){
        List<Author> authors = authorService.getAllAuthorPageableAndSort(page,sortBy,sortOrder);
        return new ResponseEntity<>(authors, HttpStatus.OK);
    }

//    Получить список всех авторов рожденных с даты, по дату //
    @GetMapping(value = "/searchByBirthDate")
    public ResponseEntity<?> getAllAuthorsByBirthDate(@RequestParam Date from,@RequestParam Date to){
        List<Author> authors =authorService.getAllAuthorsByBirthDate(from,to);
        return new ResponseEntity<>(authors, HttpStatus.OK);
    }

//    Получить список всех книг (вывести и книгу и автора) //
    @GetMapping(value = "/allBooks")
    public ResponseEntity<?> getAllBooks(){
        List<BookDto> books =bookService.getAllBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }


//    Список книг (как выше) + постраничность и выбор сортировки//
    @GetMapping(value = "/sortBook")
    public ResponseEntity<?> getAllAuthorBook(@RequestParam String sortBy, @RequestParam  String sortOrder, @RequestParam int page ){
        List<Book> books = bookService.getAllBookAndSort(page,sortBy,sortOrder);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

//    Список книг (как выше) + постраничность и выбор сортировки + поиск по названию //
    @GetMapping(value = "/searchbytitle")
    public ResponseEntity<?> sortBookAndSearch(@RequestParam String sortBy, @RequestParam  String sortOrder,@RequestParam String title, int page){
        List<Book> books = bookService.sortAndSearchBookByTitle(page,sortBy,sortOrder,title);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

//    Список книг (как выше) + постраничность и выбор сортировки + поиск по названию + поиск по году выпуска//
    @GetMapping(value = "/searchbytitleandyear")
    public ResponseEntity<?> sortBookAndSearch(@RequestParam String sortBy, @RequestParam  String sortOrder,@RequestParam String title,@RequestParam Long year){
        List<Book> books = bookService.sortAndSearchBookByTitleAndYear(0,sortBy,sortOrder,title,year);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

//    Получить список авторов + количество книг (которые он написал)//
    @GetMapping(value = "/amountOfBooks")
    public List<Amount> amountOfBooks(){
        List<Amount> amounts = amountService.getAllAmounts();
        return  amounts;
    }


 //   Получить список авторов + количество книг (которые он написал) + постраничность + выбор соритировки//

    @GetMapping(value = "/authoramountsort")
    public ResponseEntity<?> authorAmountSort(@RequestParam String sortBy, @RequestParam  String sortOrder,@RequestParam int page){
        List<Amount> amounts = amountService.sortAmount(sortBy,sortOrder,page);
        return  new ResponseEntity<>(amounts,HttpStatus.OK);
    }



//    Получить список авторов + количество книг (которые он написал) + постраничность + выбор соритировки + авторы рожденные с даты, по дату//

    @GetMapping(value = "/amountsortbdate")
    public ResponseEntity<?> authorAmountSortBdate(@RequestParam String sortBy, @RequestParam  String sortOrder,@RequestParam int page, @RequestParam Date at,@RequestParam Date from ){
        List<Amount> amounts = amountService.sortAmountWithBdate(sortBy,sortOrder,page,at,from);
        return  new ResponseEntity<>(amounts,HttpStatus.OK);
    }


    //Add book
    @PostMapping(value = "/addBook")
    public ResponseEntity<?> addBook(@RequestBody Book book){
        int t= bookService.addBook(book);
        System.out.println(book.getAuthor().getName());
        return  new ResponseEntity<>(t,HttpStatus.OK);
    }


    //get one Author
    @GetMapping(value = "/getAuthor/{id}")
    public ResponseEntity<?> getAuthor(@PathVariable  Long id){
        AuthorDto author = authorService.getAuthor(id);
        return  new ResponseEntity<>(author,HttpStatus.OK);
    }

    //get one Book
    @GetMapping(value = "/getBook/{id}")
    public ResponseEntity<?> getBook(@PathVariable  Long id){
        BookDto book = bookService.getBook(id);
        return  new ResponseEntity<>(book,HttpStatus.OK);
    }

    // update book
    @PutMapping(value = "/updateBook/{id}")
    public ResponseEntity<?> updateBook(@PathVariable  Long id,@RequestBody Book book){
       int t = bookService.UpdateBook(book,id);
        System.out.println(book.getAuthor().getName());
        return  new ResponseEntity<>(t,HttpStatus.OK);
    }

    //delete book

    @PutMapping(value = "/deleteBook/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable  Long id){
       bookService.deleteBook(id);
        return  new ResponseEntity<>(HttpStatus.OK);
    }
}
