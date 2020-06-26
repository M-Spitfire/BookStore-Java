package com.xcw.service;

import com.xcw.pojo.Book;
import com.xcw.pojo.Page;

import java.util.List;

public interface BookService {
    int addBook(Book book);
    int deleteBook(Book book);
    int updateBook(Book book);
    Book queryById(Integer id);
    List<Book> queryAll();
    int getNumOfBooks(int min, int max);
    List<Book> queryForPage(Page page);
    List<Book> queryByPrice(Page page);
}
