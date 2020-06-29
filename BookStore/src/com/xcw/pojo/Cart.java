package com.xcw.pojo;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Cart {
    /*
    如果需要将购物车的数据存进数据库中实现持久化，那么需要一个购物车id来区分不同用户的购物车
    这里是用session实现的购物车，没有持久化的功能，而每个session都是绑定用户的，所以可以不用
    * */
//    private Integer id;
    private Integer NumOfBooks;
    private BigDecimal totalPrice;

    private Map<String, CartItem> books;

    //添加到购物车
    public void addItem(CartItem item){
        System.out.println(item);
        if(books.containsKey((item.getId() + ""))){
            CartItem book = books.get(item.getId() + "");
            book.setCount(book.getCount() + item.getCount());
        }
        else{
            books.put(item.getId() + "", item);
            NumOfBooks++;
        }
        totalPrice = totalPrice.add(item.getPrice().multiply(new BigDecimal(item.getCount())));

    }

    //删除
    public void deleteItem(Integer id){
        CartItem book = books.get(id + "");
        NumOfBooks--;
        totalPrice = totalPrice.subtract(book.getPrice().multiply(new BigDecimal(book.getCount())));
        books.remove(id + "");
    }

    public void clear(){
        NumOfBooks = 0;
        totalPrice = new BigDecimal(0);
        books.clear();
    }

    //修改
    public void updateNum(Integer id, Integer num){
        CartItem book = books.get(id + "");

        if(books != null){
            totalPrice = totalPrice.subtract(book.getPrice().multiply(new BigDecimal(book.getCount())));
            book.setPrice(new BigDecimal(num));
            totalPrice = totalPrice.add(new BigDecimal(book.getCount()).multiply(new BigDecimal(num)));
        }
    }



    private BigDecimal calculateTotalPrice(){
        BigDecimal total = new BigDecimal(0);
        Set<Map.Entry<String, CartItem>> set = books.entrySet();
        for(Map.Entry entry : set){
            CartItem item = (CartItem)entry.getValue();
            total.add(item.getPrice().multiply(new BigDecimal(item.getCount())));
        }
        return total;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "NumOfBooks=" + NumOfBooks +
                ", totalPrice=" + totalPrice +
                ", books=" + books +
                '}';
    }

    public Integer getNumOfBooks() {
        return NumOfBooks;
    }

    public void setNumOfBooks(Integer numOfBooks) {
        NumOfBooks = numOfBooks;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Map<String, CartItem> getBooks() {
        return books;
    }

    public void setBooks(Map<String, CartItem> books) {
        this.books = books;
    }

    public Cart(Map<String, CartItem> books) {
        this.NumOfBooks = books.size();
        this.books = books;
        this.totalPrice = calculateTotalPrice();
    }

    public Cart() {
        NumOfBooks = 0;
        totalPrice = new BigDecimal(0);
        this.books = new HashMap<>();
    }
}
