package com.xcw.service.impl;

import com.xcw.dao.impl.BookDaoImpl;
import com.xcw.pojo.Book;
import com.xcw.pojo.Page;
import com.xcw.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {
    BookDaoImpl service = new BookDaoImpl();
    {
        System.out.println("BookDaoImpl is created at BookServiceImpl");
    }

    @Override
    public int addBook(Book book) {
        return service.addBook(book);
    }

    @Override
    public int deleteBook(Book book) {
        return service.deleteBook(book);
    }

    @Override
    public int updateBook(Book book) {
        return service.updateBook(book);
    }

    @Override
    public Book queryById(Integer id) {
        return service.queryBookById(id);
    }

    @Override
    public List<Book> queryAll() {
        return service.queryAll();
    }

    @Override
    public int getNumOfBooks(Integer min, Integer max) {
        return service.getNumOfBooks(min, max);
    }

    @Override
    public List<Book> queryForPage(Page page) {
        return service.queryForPage(page);
    }

    @Override
    public List<Book> queryByPrice(Page page) {
        return service.queryByPrice(page);
    }
}
