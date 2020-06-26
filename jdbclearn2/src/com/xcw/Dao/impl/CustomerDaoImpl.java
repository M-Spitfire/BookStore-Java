package com.xcw.Dao.impl;

import com.xcw.Dao.BaseDao;
import com.xcw.Dao.CustomerDao;
import com.xcw.bean.Customer;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

public class CustomerDaoImpl extends BaseDao implements CustomerDao {
    @Override
    public void insert(Connection connection, Customer customer) {
        String sql = "insert into customers(name,email,birth) values(?,?,?);";
        update(connection, sql, customer.getName(), customer.getEmail(),customer.getBirth());
    }

    @Override
    public void deleteByID(Connection connection, int id) {
        String sql = "delete from customers where id = ?;";
        update(connection, sql, id);
    }

    @Override
    public void update(Connection connection, Customer customer) {
        String sql = "update customers set name = ?, email = ?, birth = ? where id = ?'";
        update(connection, sql, customer.getName(), customer.getEmail(), customer.getBirth(), customer.getId());
    }

    @Override
    public Customer getCustomerByID(Connection connection, int id) {
        String sql = "select * from customers where id = ?;";
        return queryForList(Customer.class, connection, sql, id).get(0);
    }

    @Override
    public List<Customer> getAllCustomer(Connection connection) {
        String sql = "select * from customers;";
        return queryForList(Customer.class, connection, sql);
    }

    @Override
    public long getCount(Connection connection) {
        String sql = "select count(*) from customers;";
        return queryForValue(connection, sql);
    }

    @Override
    public Date getMaxBirth(Connection connection) {
        String sql = "select max(*) from customers;";
        return queryForValue(connection, sql);
    }
}
