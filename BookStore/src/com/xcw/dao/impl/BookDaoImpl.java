package com.xcw.dao.impl;

import com.xcw.dao.BookDao;
import com.xcw.pojo.Book;
import com.xcw.pojo.Page;
import com.xcw.utils.jdbcUtils;
import org.apache.commons.dbutils.QueryRunner;

import javax.swing.*;
import java.sql.Connection;
import java.util.List;

public class BookDaoImpl extends BaseDao implements BookDao {
    @Override
    public int addBook(Book book) {
        String sql = "insert into books(name,author,price,sales,stock,img_path) values(?,?,?,?,?,?);";
        Connection connection = jdbcUtils.getConnection();
        int n = update(connection, sql, book.getName(), book.getAuthor(), book.getPrice(),
                book.getSales(), book.getStock(), book.getImg_path());
//        jdbcUtils.releaseConnection(connection);
        return n;
    }

    @Override
    public int deleteBook(Book book) {
        String sql = "delete from books where id = ?;";
        Connection connection = jdbcUtils.getConnection();
        int n = update(connection, sql, book.getId());
//        jdbcUtils.releaseConnection(connection);
        return n;
    }

    @Override
    public int updateBook(Book book) {
        String sql = "update books set name = ?, author = ?, price = ?, sales = ?, stock = ?, img_path = ?" +
                "where id = ?;";
        Connection connection = jdbcUtils.getConnection();
        int n = update(connection, sql, book.getName(), book.getAuthor(), book.getPrice(),
                book.getSales(), book.getStock(), book.getImg_path(), book.getId());
//        jdbcUtils.releaseConnection(connection);
        return n;
    }

    @Override
    public Book queryBookById(Integer id) {
        String sql = "select * from books where id = ?";
        Connection connection = jdbcUtils.getConnection();
        Book book = queryForOne(Book.class, connection, sql, id);
//        jdbcUtils.releaseConnection(connection);
        return book;
    }

    @Override
    public List<Book> queryByPrice(Page page) {
        String sql = "select * from books where price between ? and ? limit ?, ?;";
        Connection connection = jdbcUtils.getConnection();
        List<Book> list = queryForList(Book.class, connection, sql, page.getMin(), page.getMax(), page.getCurrentPage() * page.getPageSize(), page.getPageSize());
//        jdbcUtils.releaseConnection(connection);
        return list;
    }

    @Override
    public List<Book> queryForPage(Page page) {
        String sql = null;
        Connection connection = jdbcUtils.getConnection();
        List<Book> list = null;
        if(page.getMin() == 0 && page.getMax() == 0){
            sql = "select * from books limit ?, ?;";
            list = queryForList(Book.class, connection, sql, page.getCurrentPage() * page.getPageSize(), page.getPageSize());
        }
        else{
            sql = "select * from books where price between ? and ? limit ?, ?;";
            list = queryForList(Book.class, connection, sql, page.getMin(), page.getMax(), page.getCurrentPage() * page.getPageSize(), page.getPageSize());
        }
//        jdbcUtils.releaseConnection(connection);
        return list;
    }

    @Override
    /**
     * 返回所有书的集合
     */
    public List<Book> queryAll() {
        String sql = "select * from books";
        Connection connection = jdbcUtils.getConnection();
        List<Book> list = queryForList(Book.class, connection, sql);
//        jdbcUtils.releaseConnection(connection);
        return list;
    }

    @Override
    /**
     * 返回有多少本书
     */

    public int getNumOfBooks(Integer min, Integer max) {
//        System.out.println("min:" + min);
//        System.out.println("max:" + max);
        String sql = null;
        long numOfBooks = 0;
        Connection connection = jdbcUtils.getConnection();
//        System.out.println("数据库连接：" + connection);
        if(min == 0 && max == 0){
//            System.out.println("查找全部");
            sql = "select count(*) from books";
            numOfBooks = (long)queryForValue(connection, sql);
        }
        else{
//            System.out.println("按价格查找");
            sql = "select count(*) from books where price between ? and ?;";
            numOfBooks = (long)queryForValue(connection, sql, min, max);
        }
//        System.out.println(numOfBooks);
//        jdbcUtils.releaseConnection(connection);
        return (int)numOfBooks;
    }
}
