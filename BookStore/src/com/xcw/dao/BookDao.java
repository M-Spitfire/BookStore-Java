package com.xcw.dao;


import com.xcw.pojo.Book;
import com.xcw.pojo.Page;

import java.util.List;

public interface BookDao {
    //增
    int addBook(Book book);

    //删
    int deleteBook(Book book);

    //改
    int updateBook(Book book);

    //查
    Book queryBookById(Integer id);

    List<Book> queryAll();

    List<Book> queryByPrice(Page page);

    List<Book> queryForPage(Page page);

    int getNumOfBooks(Integer min, Integer max);
}
