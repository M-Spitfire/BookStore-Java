package com.xcw.test;

import com.xcw.pojo.Book;
import com.xcw.service.impl.BookServiceImpl;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class BookServiceImplTest {
    BookServiceImpl service = new BookServiceImpl();

    @Test
    public void addBook() {
        Book book = new Book(null, "资本论", "马克思", 99.99, 100000, 9999, null);
        service.addBook(book);
    }

    @Test
    public void deleteBook() {
        Book book = new Book(25, "资本论", "马克思", 999.99, 100000, 9999, null);
        service.deleteBook(book);
    }

    @Test
    public void updateBook() {
        Book book = new Book(25, "资本论", "马克思", 999.99, 100000, 9999, null);
        service.updateBook(book);
    }

    @Test
    public void queryById() {
        Book book = service.queryById(25);
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