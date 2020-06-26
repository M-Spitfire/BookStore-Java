package com.xcw.test;

import com.xcw.dao.impl.BookDaoImpl;
import com.xcw.pojo.Book;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class BookDaoImplTest {
    BookDaoImpl service = new BookDaoImpl();

    @Test
    public void addBook() {
        Book book = new Book(null, "经济学", "tom", 36.55, 345, 0, null);
        service.addBook(book);
    }

    @Test
    public void deleteBook() {
        Book book = new Book();
        book.setId(1);
        service.deleteBook(book);
    }

    @Test
    public void updateBook() {
        Book book = new Book(23, "经济学原理", "tom", 36.55, 345, 0, null);
        service.addBook(book);
    }

    @Test
    public void queryBookById() {
        Book book = service.queryBookById(12);
        System.out.println(book);
    }

    @Test
    public void queryAll() {
        List<Book> list = service.queryAll();
        for(Book book : list){
            System.out.println(book);
        }
    }

}