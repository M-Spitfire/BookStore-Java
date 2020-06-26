package com.xcw.Dao;

import com.xcw.bean.Customer;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

public interface CustomerDao {
    void insert(Connection connection, Customer customer);

    void deleteByID(Connection connection, int id);

    void update(Connection connection, Customer customer);

    Customer getCustomerByID(Connection connection, int id);

    List<Customer> getAllCustomer(Connection connection);

    long getCount(Connection connection);

    Date getMaxBirth(Connection connection);
}
