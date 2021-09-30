package com.example.sp5.services.impl;

import com.example.sp5.dto.AuthorDto;
import com.example.sp5.dto.BookDto;
import com.example.sp5.entites.Book;

import com.example.sp5.services.BookService;

import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {


    @PersistenceContext
    protected EntityManager entityManager;
    @Override
    public List<BookDto> getAllBooks() {

        return entityManager.createQuery("SELECT new com.example.sp5.dto.BookDto(b) FROM Book b")
                .getResultList();
    }


    @Override
    public List<Book> getAllBookAndSort(int page, String sortBy, String sortOrder) {
        String qur;
        if(("title".equals(sortBy.toLowerCase() )|| "description".equals(sortBy.toLowerCase() ) || "issueYear".equals(sortBy.toLowerCase() )
                && ("desc".equals(sortOrder.toLowerCase())||"asc".equals(sortOrder.toLowerCase())))) {
             qur = "SELECT a FROM Book  a  ORDER BY a." + sortBy + " " + sortOrder;

        }
        else{
            qur = "SELECT a FROM Book  a  ORDER BY a.id asc";
        }
        return entityManager.createQuery(qur)
                .setFirstResult(page * 2)
                .setMaxResults(page * 2 + 2)
                .getResultList();
    }

    @Override
    public List<Book> sortAndSearchBookByTitle(int page,  String sortBy, String sortOrder, String title) {
        String qur;
        if(("title".equals(sortBy.toLowerCase() )|| "description".equals(sortBy.toLowerCase() ) || "issueYear".equals(sortBy.toLowerCase() ) &&
                ("desc".equals(sortOrder.toLowerCase())||"asc".equals(sortOrder.toLowerCase()))) ) {
            qur ="SELECT a FROM Book  a  where  a.title like :title ORDER BY a."+sortBy+" "+sortOrder;
        }else{
            qur ="SELECT a FROM Book  a  where  a.title like :title ORDER BY a.id asc";
        }
        return entityManager.createQuery(qur)
                .setParameter("title", "%" + title + "%")
                .setFirstResult(page * 2)
                .setMaxResults(page*2+2)
                .getResultList();


    }

    @Override
    public List<Book> sortAndSearchBookByTitleAndYear(int page, String sortBy, String sortOrder, String title, Long year) {
        String qur ;
        if(("title".equals(sortBy.toLowerCase() )|| "description".equals(sortBy.toLowerCase() ) || "issueYear".equals(sortBy.toLowerCase() )
                && ("desc".equals(sortOrder.toLowerCase())||"asc".equals(sortOrder.toLowerCase()))) ) {
            qur ="SELECT a FROM Book  a  where  a.title  a.title like :title  AND a.issueYear= :year ORDER BY a."+sortBy+" "+sortOrder;
        }
        else{
            qur ="SELECT a FROM Book  a  where  a.title like :title ORDER BY a.id asc";
        }
        return entityManager.createQuery(qur)
                .setParameter("title", "%" + title + "%")
                .setParameter("issueYear",year )
                .setFirstResult(page * 2)
                .setMaxResults(page*2+2)
                .getResultList();


    }

    @Override
    @Transactional
    public int addBook(Book book) {

        String qur = " INSERT INTO book(id, title, description,issue_year,author_id) values(?,?,?,?,?)";

        return  entityManager.createNativeQuery(qur)
                .setParameter(1, book.getId())
                .setParameter(2, book.getTitle())
                .setParameter(3,book.getDescription())
                .setParameter(4,book.getIssueYear())
                .setParameter(5,book.getAuthor().getId())
                .executeUpdate();

    }
    //update book set title ='year' WHERE id =1
    @Override
    @Transactional
    public int UpdateBook(Book book,Long id) {

        String qur = " UPDATE  book SET title = ?,description=?, issue_year=?, author_id=? where id = ?";

        return  entityManager.createNativeQuery(qur)

                .setParameter(1, book.getTitle())
                .setParameter(2,book.getDescription())
                .setParameter(3,book.getIssueYear())
                .setParameter(4,book.getAuthor().getId())
                .setParameter(5, id)
                .executeUpdate();

    }

    // get one book
    @Override
    public BookDto getBook(Long id) {
        return (BookDto) entityManager.createQuery("SELECT new com.example.sp5.dto.BookDto(a)  FROM Book  a where  a.id =?1")
                .setParameter(1,id)
                .getSingleResult();
    }

    //DELETE FROM book WHERE id=1;
    @Override
    public void deleteBook(Long id){
     entityManager.createQuery("DELETE FROM Book  b  WHERE b.id=?1")
                .setParameter(1, id)
                .executeUpdate();
    }



}
